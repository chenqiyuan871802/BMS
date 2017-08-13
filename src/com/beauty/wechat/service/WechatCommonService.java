package com.beauty.wechat.service;

import java.io.File;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beauty.asyncTask.SmsAsyncTask;
import com.beauty.asyncTask.WechatAsyncTask;
import com.beauty.common.constant.BeautyCons;
import com.beauty.common.po.CustomUserPO;
import com.beauty.common.service.CustomUserService;
import com.beauty.common.utils.IdUtil;
import com.beauty.wechat.model.WechatUser;
import com.ims.common.core.asset.FileOperation;
import com.ims.common.core.asset.IMSCons;
import com.ims.common.core.asset.IMSCxt;
import com.ims.common.core.asset.IMSUtils;
import com.ims.common.core.matatype.Dto;
import com.ims.common.core.matatype.Dtos;
import com.ims.common.core.support.redis.JedisUtil;

import redis.clients.jedis.Jedis;

/**
 * 
 * 类名:com.beauty.wechat.service.WechatCommonService
 * 描述:微信通用业务逻辑处理
 * 编写者:陈骑元
 * 创建时间:2017年5月20日 下午3:29:34
 * 修改说明:
 */
@Service
public class WechatCommonService {
	
	private static Log log = LogFactory.getLog(WechatAsyncTask.class);
	/**
	 * 消费者业务逻辑处理类
	 */
	@Autowired
	private CustomUserService customUserService;
	/**
	 * 短信发任务
	 */
	 @Autowired
	 private SmsAsyncTask smsAsyncTask;
	
	/**
	 * 
	 * 简要说明：保存消费者的微信信息 并返回相关信息
	 * 编写者：陈骑元
	 * 创建时间：2017年5月20日 下午3:33:24
	 * @param 说明
	 * @return 说明
	 */
	public CustomUserPO  saveCustomUser(WechatUser wechatUser,String rootPath){
		CustomUserPO customUserPO=null;
		if(IMSUtils.isNotEmpty(wechatUser)){
			Dto pDto=Dtos.newDto();
			pDto.put("openid", wechatUser.getOpenid());
			pDto.put("is_del", IMSCons.IS.NO); //查询之前是否关注过，如果关注过,测进行更新操作
			CustomUserPO customUserPOOld=customUserService.selectOne(pDto);
			boolean updateFlag=false;
			if(IMSUtils.isNotEmpty(customUserPOOld)){  //已经保存过微信相关的信息
				customUserPO=customUserPOOld;
				updateFlag=true;
			}else{
				customUserPO=new CustomUserPO();
				String custom_user_id=IdUtil.createCustomUserId();
				customUserPO.setCustom_user_id(custom_user_id);
			}
			String headImageUrl=wechatUser.getHeadimgurl();
			if(IMSUtils.isNotEmpty(headImageUrl)&&
					IMSUtils.isEmpty(customUserPO.getPhoto())){  //微信用户头像URL信息，如果不为空测进行头像下载
				String fileName=customUserPO.getCustom_user_id()+".png";
				String folderPath = rootPath+File.separator+BeautyCons.CUSTOM_IMAGE_URL;
				FileOperation.createFolder(folderPath);
				String saveFilePath=folderPath+File.separator+fileName;
				FileOperation.downloadUrlFile(headImageUrl,saveFilePath);//下载头像信息
				customUserPO.setPhoto(BeautyCons.CUSTOM_IMAGE_URL+fileName); //存储用户照片URL地址
				
			}
			 customUserPO.setOpenid(wechatUser.getOpenid());
			 customUserPO.setNikename(wechatUser.getNickname());
			 customUserPO.setSex(wechatUser.getSex()+"");
			 customUserPO.setRemark(wechatUser.getRemark());
			 customUserPO.setAddress(wechatUser.getProvince()+wechatUser.getCity());
			 customUserPO.setEnroll_mode(BeautyCons.ENROLL_MODE_APP);
		     customUserPO.setEnroll_time(IMSUtils.getDateTime());
			 customUserPO.setIs_del(IMSCons.IS.NO);
			 customUserPO.setWechat_status(BeautyCons.WECHAT_STATUS_OAUTH);
			 if(updateFlag){
				 customUserService.updateByKey(customUserPO);
			 }else{
				 customUserPO.setUsername(wechatUser.getNickname());
				 customUserService.insert(customUserPO);
			 }
			 log.info("网页授权保存微信用户信息成功:顾客的会员编号["+customUserPO.getCustom_user_id()+"],微信号标识["+customUserPO.getOpenid()+"]");
		}
		return customUserPO;
		
	}
	/**
	 * 
	 * 简要说明：发送验证码
	 * 编写者：陈骑元
	 * 创建时间：2017年5月21日 上午12:34:41
	 * @param 说明
	 * @return 说明
	 */
	public Dto sendCheckCode(String mobile,String templateCode){
		Dto outDto=Dtos.newDto();
		Jedis jedis=JedisUtil.getJedisClient();
		String checkCountKey=templateCode+BeautyCons.REDIS_CHECK_COUNT_KEY+mobile;
		if(jedis.exists(checkCountKey)){ //如果存在
			int maxNum=5;
			String oneHourNum=IMSCxt.getParamValue(BeautyCons.ONE_HOUR_NUM);
			if(IMSUtils.isNotEmpty(oneHourNum)){
				maxNum=Integer.parseInt(oneHourNum);
			}
			String countStr=jedis.get(checkCountKey);
			int count=Integer.parseInt(countStr);
			if(count>=maxNum){  //一小时内发送验证码次数不能超过5次
				 outDto.setAppCode(IMSCons.ERROR);
				 outDto.setAppMsg("获取验证码超过一小时最大限制次数"+maxNum+",请一小时后再验证登陆");
				 JedisUtil.close(jedis);
				 return outDto;
			}
		}
		smsAsyncTask.sendCheckCode(mobile,templateCode);
	    outDto.setAppCode(IMSCons.SUCCESS);
		outDto.setAppMsg("验证码发送成功,请注意查看手机");
		JedisUtil.close(jedis);
		return outDto;
		
	}
	
	/**
	 * 
	 * 简要说明：检验登陆
	 * 编写者：陈骑元
	 * 创建时间：2017年5月21日 上午2:10:30
	 * @param 说明
	 * @return 说明
	 */
	public  Dto checkLogin(Dto inDto){
		Dto outDto=Dtos.newDto();
		String mobile=inDto.getString("mobile");
		String checkCode=inDto.getString("checkCode");
		String openid=inDto.getString("openid");
		Jedis jedis=JedisUtil.getJedisClient();
		String templateCode=IMSCxt.getParamValue(BeautyCons.CHECK_SMS_CODE);
		String checkKey = templateCode+BeautyCons.REDIS_CHECK_CODE_KEY + mobile;
		String checkCodeCache=jedis.get(checkKey);//获取验证码
		JedisUtil.close(jedis);
		if(!checkCode.equals(checkCodeCache)){
			 outDto.setAppCode(IMSCons.ERROR);
			 outDto.setAppMsg("验证码不正确");
			 return outDto;
		}
		CustomUserPO  customUserPO=null;
		Dto userDto=null;
		if(IMSUtils.isNotEmpty(openid)){
		    userDto = Dtos.newDto("openid", openid);
			userDto.put("is_del", IMSCons.IS.NO);
			customUserPO = customUserService.selectOne(userDto);
			if(IMSUtils.isNotEmpty(customUserPO)){ //如果不等于空
				String mobileStr=customUserPO.getMobile();
				if(IMSUtils.isNotEmpty(mobileStr)){  //不为空
					if(!mobile.equals(mobileStr)){
						 outDto.setAppCode(IMSCons.ERROR);
						 outDto.setAppMsg("该手机号已经绑定其他微信号");
						 return outDto;
					}
				}else{
					customUserPO.setMobile(mobile); //如果未绑定则进行收集绑定
					customUserService.updateByKey(customUserPO);
				}
			}else{
				 userDto = Dtos.newDto("mobile", mobile);
				userDto.put("is_del", IMSCons.IS.NO); 
				customUserPO = customUserService.selectOne(userDto);
				if(IMSUtils.isNotEmpty(customUserPO)){
					String openidStr=customUserPO.getOpenid();
					if(IMSUtils.isNotEmpty(openidStr)){  //openid 不能为空
						if(!openidStr.equals(openid)){
							outDto.setAppCode(IMSCons.ERROR);
							 outDto.setAppMsg("该手机号已经绑定其他微信号");
							 return outDto;
						}
					}else{
						customUserPO.setOpenid(openid); //绑定微信号
						customUserService.updateByKey(customUserPO);
					}
				}else{  //怎进行消费者用户插入
					 customUserPO=new CustomUserPO();
					 String custom_user_id=IdUtil.createCustomUserId();
					  customUserPO.setCustom_user_id(custom_user_id);
					 customUserPO.setOpenid(openid);
					 customUserPO.setMobile(mobile);
					 customUserPO.setEnroll_mode(BeautyCons.ENROLL_MODE_APP);
				     customUserPO.setEnroll_time(IMSUtils.getDateTime());
					 customUserPO.setIs_del(IMSCons.IS.NO);
					 customUserPO.setWechat_status(BeautyCons.WECHAT_STATUS_OAUTH);
					 customUserService.insert(customUserPO);
				}
			}
		}else{
			    userDto = Dtos.newDto("mobile", mobile);
				userDto.put("is_del", IMSCons.IS.NO); 
			    customUserPO = customUserService.selectOne(userDto);
			    if(IMSUtils.isEmpty(customUserPO)){
			    	outDto.setAppCode(IMSCons.ERROR);
					outDto.setAppMsg("该手机号还没绑定微信号");
					return outDto;
			    }
		}
		outDto.setAppCode(IMSCons.SUCCESS);
		outDto.setAppMsg("登陆成功");
		outDto.put("customUserPO", customUserPO);
		return outDto;
	}

}
