package com.beauty.pay.wechatpay.service;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.beauty.common.po.BusinessOrderPO;
import com.beauty.common.po.CustomUserPO;
import com.beauty.pay.constant.SwiftpassConfig;
import com.beauty.pay.util.MD5;
import com.beauty.pay.util.SignUtils;
import com.beauty.pay.util.XmlUtils;
import com.ims.common.core.asset.IMSUtils;
import com.ims.common.core.id.IMSId;

/**
 * 
 * 类名:com.beauty.pay.wechatpay.service.AppPayService 描述:app支付业务逻辑 编写者:陈骑元
 * 创建时间:2017年6月3日 下午10:11:59 修改说明:
 */
@Service
public class AppPayService {

	private static Logger log = Logger.getLogger(AppPayService.class);

	/**
	 * 
	 * 简要说明： 编写者：陈骑元 创建时间：2017年6月3日 下午10:31:46
	 * 
	 * @param 说明
	 * @return 说明
	 */
	public Map<String, String> appPayOrder(String out_trade_no, Double order_money, String order_content, String openid,
			String ip, String callbackUrl) {
		log.info("微信支付请求开始...");
		Map<String, String> resultMap = new HashMap<String, String>();
		SortedMap<String, String> dataMap = new TreeMap<String, String>();
		dataMap.put("service", "pay.weixin.jspay");
		dataMap.put("version", SwiftpassConfig.version);
		dataMap.put("charset", SwiftpassConfig.charset);
		dataMap.put("sign_type", SwiftpassConfig.sign_type);
		dataMap.put("mch_id", SwiftpassConfig.mch_id);
		dataMap.put("notify_url", SwiftpassConfig.notify_url);
		dataMap.put("nonce_str", String.valueOf(new Date().getTime()));
		dataMap.put("out_trade_no", out_trade_no); // 商户订单号
		dataMap.put("body", order_content);
		dataMap.put("total_fee", order_money.intValue() + "");
		dataMap.put("mch_create_ip", ip);
		if (IMSUtils.isNotEmpty(callbackUrl)) {
			dataMap.put("callback_url", callbackUrl);
		}
		Map<String, String> params = SignUtils.paraFilter(dataMap);
		StringBuilder buf = new StringBuilder((params.size() + 1) * 10);
		SignUtils.buildPayParams(buf, params, false);
		String preStr = buf.toString();
		String sign = MD5.sign(preStr, "&key=" + SwiftpassConfig.key, SwiftpassConfig.charset);
		dataMap.put("sign", sign);
		String reqUrl = SwiftpassConfig.req_url;
		log.info("微信支付reqUrl：" + reqUrl);
		log.info("微信支付reqParams:" + XmlUtils.parseXML(dataMap));
		resultMap = httpPost(dataMap, reqUrl);

		return resultMap;

	}

	

	public static Map<String, String> httpPost(SortedMap<String, String> dataMap, String reqUrl) {
		CloseableHttpResponse response = null;
		CloseableHttpClient client = null;
		String res = null;
		Map<String, String> resultMap = new HashMap<String, String>();
		try {
			HttpPost httpPost = new HttpPost(reqUrl);
			StringEntity entityParams = new StringEntity(XmlUtils.parseXML(dataMap), SwiftpassConfig.charset);
			httpPost.setEntity(entityParams);
			httpPost.setHeader("Content-Type", "text/xml;charset=ISO-8859-1");
			client = HttpClients.createDefault();
			response = client.execute(httpPost);
			if (response != null && response.getEntity() != null) {
				resultMap = XmlUtils.toMap(EntityUtils.toByteArray(response.getEntity()), SwiftpassConfig.charset);
				res = XmlUtils.toXml(resultMap);
				log.info("微信支付结果：" + res);
				if (!SignUtils.checkParam(resultMap, SwiftpassConfig.key)) {
					res = "验证签名不通过";
				} else {
					if ("0".equals(resultMap.get("status")) && "0".equals(resultMap.get("result_code"))) {
						res = "ok";
					} else {
						String err_code = resultMap.get("err_code");
						String err_msg = resultMap.get("err_msg");
						res = err_msg;
						log.error("微信支付失败返回错误代码：" + err_code + "  错误代码描述：" + err_msg);
					}
				}
			} else {
				res = "操作失败";
			}
		} catch (Exception e) {
			log.error("操作失败，原因：", e);
			res = "系统异常";
		} finally {

			try {
				if (response != null) {
					response.close();
				}
				if (client != null) {
					client.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		if (!"ok".equals(res)) {
			resultMap.put("status", "200");
			resultMap.put("msg", "微信支付申请成功");

		} else {
			resultMap.put("status", "500");
			resultMap.put("msg", res);
		}

		return resultMap;
	}

}
