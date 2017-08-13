package com.beauty.asyncTask;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.beauty.common.constant.BeautyCons;
import com.beauty.common.mapper.SmsRecordMapper;
import com.beauty.common.po.SmsRecordPO;
import com.beauty.common.service.SmsRecordService;
import com.ims.common.core.asset.IMSCons;
import com.ims.common.core.asset.IMSCxt;
import com.ims.common.core.asset.IMSJson;
import com.ims.common.core.asset.IMSUtils;
import com.ims.common.core.id.IMSId;
import com.ims.common.core.matatype.Dto;
import com.ims.common.core.matatype.Dtos;
import com.ims.common.core.matatype.impl.HashDto;
import com.ims.common.core.support.redis.JedisUtil;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;

import redis.clients.jedis.Jedis;

/**
 * 
 * 类名:com.beauty.asyncTask.SmsAsyncTask 描述:短信异步任务发送 编写者:陈骑元 创建时间:2017年5月12日
 * 上午1:42:43 修改说明:
 */
@Component
public class SmsAsyncTask {
	private static Log log = LogFactory.getLog(SmsAsyncTask.class);

	@Autowired
	private SmsRecordMapper smsRecordMapper;

	/**
	 * 
	 * 简要说明：批量发送多条短信 编写者：陈骑元 创建时间：2017年5月13日 上午1:16:49
	 * 
	 * @param 说明
	 * @return 说明
	 */
	@Async
	public void sendMoreSms(List<SmsRecordPO> smsRecordList) {
		for (SmsRecordPO smsRecordPO : smsRecordList) {
			sendSms(smsRecordPO);
		}
	}

	/**
	 * 
	 * 简要说明：发送单条短信 编写者：陈骑元 创建时间：2017年5月13日 上午12:45:58
	 * 
	 * @param 说明
	 *            smsRecord 短信实体不能为空
	 * @return 说明
	 */
	@Async
	public void sendSms(SmsRecordPO smsRecord) {
		if (IMSUtils.isNotEmpty(smsRecord)) {
			String templateKey = IMSCxt.getParamValue(BeautyCons.TEMPLATE_KEY); // 模板的键
			String mobile = smsRecord.getMobile();
			if (IMSUtils.checkMobile(mobile)) {
				String templateCode = IMSCxt.getParamValue(BeautyCons.SMS_TEMPLATE_CODE);// 模板
				String content = smsRecord.getContent();
				Dto jsonDto = Dtos.newDto(templateKey, content);
				String templateJson = IMSJson.toJson(jsonDto);
				String returnMsg = sendSms(mobile, templateCode, templateJson);
				SmsRecordPO smsRecordUpdate = new SmsRecordPO();
				smsRecordUpdate.setRecord_id(smsRecord.getRecord_id());
				smsRecordUpdate.setSend_time(IMSUtils.getDateTime());
				if ("success".equals(returnMsg)) { // 短信发送成功
					smsRecordUpdate.setStatus(IMSCons.SMS_STATUS_SUCCESS);
					log.info("阿里大于发送信息[" + content + "]到手机[" + mobile + "]成功");
				} else {
					smsRecordUpdate.setStatus(IMSCons.SMS_STATUS_FAILURE);
					smsRecordUpdate.setFailure_cause(returnMsg);
					log.error("阿里大于发送信息[" + content + "]到手机[" + mobile + "]失败:" + returnMsg);
				}
				smsRecordMapper.updateByKey(smsRecordUpdate);
			} else {
				log.error("阿里大于短信接口发送失败：手机号码不合法");
			}

		} else {
			log.error("阿里大于短信接口发送失败：短信号码为空");
		}
	}

	/**
	 * 
	 * 简要说明：发送登陆验证 编写者：陈骑元 创建时间：2017年5月20日 下午11:37:08
	 * 
	 * @param 说明
	 * @return 说明
	 */
	@Async
	public void sendCheckCode(String mobile ,String templateCode) {
		String check_code_num = IMSCxt.getParamValue(BeautyCons.CHECK_CODE_NUM); // 验证码位数
		String check_code_vaild = IMSCxt.getParamValue(BeautyCons.CHECK_CODE_VAILD); // 验证码有效时间
		String check_code_key = IMSCxt.getParamValue(BeautyCons.CHECK_CODE_KEY); // 验证码模板内容键
		
		int checkCodeLen = 6; // 默认6位
		int vaildTime = 10; // 有效时间默认十分钟
		if (IMSUtils.isNotEmpty(check_code_num)) {
			checkCodeLen = Integer.parseInt(check_code_num);
		}
		if (IMSUtils.isNotEmpty(check_code_vaild)) {
			vaildTime = Integer.parseInt(check_code_vaild);
		}
		String checkCode = IMSUtils.createRandomCode(checkCodeLen, IMSCons.RANDOM_TYPE.NUMBER);
		//String content = checkCode + "," + vaildTime + "分钟内有效";
		String content = checkCode ;
		Dto jsonDto = Dtos.newDto(check_code_key, content);
		String templateJson = IMSJson.toJson(jsonDto);
		String returnMsg = sendSms(mobile, templateCode, templateJson);
		SmsRecordPO smsRecordPO = new SmsRecordPO();
		smsRecordPO.setRecord_id(IMSId.uuid());
		smsRecordPO.setMobile(mobile);
		smsRecordPO.setContent(checkCode);
		smsRecordPO.setSend_time(IMSUtils.getDateTime());
		smsRecordPO.setCreate_time(IMSUtils.getDateTime());
		smsRecordPO.setSms_type(IMSCons.SMS_TYPE_CHECK);
		if ("success".equals(returnMsg)) { // 短信发送成功
			smsRecordPO.setStatus(IMSCons.SMS_STATUS_SUCCESS);
			Jedis jedis = JedisUtil.getJedisClient();
			String checkKey =templateCode+ BeautyCons.REDIS_CHECK_CODE_KEY + mobile;
			String checkCountKey = templateCode+BeautyCons.REDIS_CHECK_COUNT_KEY + mobile;
			jedis.set(checkKey, checkCode);
			jedis.expire(checkKey, vaildTime * 10);
			if (jedis.exists(checkCountKey)) { // 1小时记录验证码发送次数
				String countStr = jedis.get(checkCountKey);
				int count = Integer.parseInt(countStr) + 1;
				jedis.set(checkCountKey, count + "");
			} else {
				jedis.set(checkCountKey, "1");
				jedis.expire(checkKey, 3600);
			}
			JedisUtil.close(jedis);
			log.info("阿里大于发送登陆验证码[" + content + "]到手机[" + mobile + "]成功");
		} else {
			smsRecordPO.setStatus(IMSCons.SMS_STATUS_FAILURE);
			smsRecordPO.setFailure_cause(returnMsg);
			log.error("阿里大于发送登陆验证码[" + content + "]到手机[" + mobile + "]失败:" + returnMsg);
		}
		smsRecordMapper.insert(smsRecordPO);
	}

	/**
	 * 
	 * 简要说明：短信发送接口 编写者：陈骑元 创建时间：2017年5月12日 上午1:52:33
	 * 
	 * @param 说明
	 * @return 说明
	 */
	@SuppressWarnings("unchecked")
	public String sendSms(String mobile, String templateCode, String templateJson) {
		if (IMSUtils.isEmpty(mobile)) {
			log.error("阿里大于短信接口发送失败：手机号码为空");
			return "手机号码为空";

		}
		if (IMSUtils.isEmpty(templateJson)) {
			log.error("阿里大于短信接口发送失败：短信模板为空");
			return "短信模板内容为空";

		}
		String url = IMSCxt.getParamValue(BeautyCons.SMS_URL); // 短信网关
		String appKey = IMSCxt.getParamValue(BeautyCons.SMS_APP_KEY);// 短信应用ID
		String appSrcret = IMSCxt.getParamValue(BeautyCons.SMS_APP_SRCRET);// 短信加密秘钥
		String signe = IMSCxt.getParamValue(BeautyCons.SMS_SIGNE); // 短信签名
		TaobaoClient client = new DefaultTaobaoClient(url, appKey, appSrcret);
		AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
		req.setSmsType("normal");
		req.setSmsFreeSignName(signe);
		req.setSmsParamString(templateJson);
		req.setRecNum(mobile);
		req.setSmsTemplateCode(templateCode);
		try {
			AlibabaAliqinFcSmsNumSendResponse rsp = client.execute(req);
			String repStr = rsp.getBody();
			log.info("阿里大于短信发送接口返回信息：" + repStr);
			if (repStr.indexOf("alibaba_aliqin_fc_sms_num_send_response") > -1) { // 说明发送成功

				return "success";
			} else {
				Dto dataDto = IMSJson.fromJson(repStr, HashDto.class);
				Map<String, String> map = (Map<String, String>) dataDto.get("error_response");
				String errorCodeAndMsg = map.get("sub_code") + "[" + map.get("sub_msg") + "]";
				return errorCodeAndMsg;
			}

		} catch (ApiException e) {
			log.error("阿里大于短信接口发送异常：" + e);
		}
		return "阿里大于短信接口发送异常";
	}

}
