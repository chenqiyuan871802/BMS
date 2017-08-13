package com.beauty.pay.util;

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

import com.beauty.pay.constant.SwiftpassConfig;
import com.beauty.pay.util.MD5;
import com.beauty.pay.util.SignUtils;
import com.beauty.pay.util.XmlUtils;
import com.ims.common.core.asset.IMSCons;
import com.ims.common.core.asset.IMSCxt;
import com.ims.common.core.asset.IMSUtils;

/**
 * 
 * 类名:com.beauty.pay.util.AppPayUtil 描述:微信公众号支付工具类 编写者:陈骑元 创建时间:2017年6月4日
 * 上午10:32:29 修改说明:
 */
public class PayUtil {

	private static Logger log = Logger.getLogger(PayUtil.class);
	/**
	 * 
	 * 简要说明：PC端微信扫码支付
	 * 编写者：陈骑元
	 * 创建时间：2017年6月5日 下午11:07:02
	 * @param 说明
	 * @return 说明
	 */
	public static Map<String,String> wechatScanCodePay(String out_trade_no, Double order_money, String order_content ){
		log.info("微信扫码支付请求开始...............................");
		Map<String, String> resultMap = new HashMap<String, String>();
		SortedMap<String, String> dataMap = new TreeMap<String, String>();
		dataMap.put("service", "pay.weixin.native");
		dataMap.put("version", "2.0");
		dataMap.put("charset", SwiftpassConfig.charset);
		dataMap.put("sign_type", SwiftpassConfig.sign_type);
		dataMap.put("mch_id", SwiftpassConfig.mch_id);
		dataMap.put("notify_url", SwiftpassConfig.notify_url);
        dataMap.put("nonce_str", String.valueOf(new Date().getTime()));
        dataMap.put("out_trade_no", out_trade_no); // 商户订单号
		dataMap.put("body", order_content);
		String wechat_pay = IMSCxt.getParamValue("wechat_pay");
		if ("on".equals(wechat_pay)) {
			Double total = order_money * 100;
			dataMap.put("total_fee", total.intValue() + "");
			
		}else{
			dataMap.put("total_fee",  "1");
		}
		
		String ip = IMSCxt.getParamValue("order_ip");
		dataMap.put("mch_create_ip", ip);
        Map<String,String> params = SignUtils.paraFilter(dataMap);
        StringBuilder buf = new StringBuilder((params.size() +1) * 10);
        SignUtils.buildPayParams(buf,params,false);
        String preStr = buf.toString();
        String sign = MD5.sign(preStr, "&key=" + SwiftpassConfig.key, "utf-8");
        dataMap.put("sign", sign);
        String reqUrl = SwiftpassConfig.req_url;
        log.info("微信扫码支付请求reqUrl：" + reqUrl);
        log.info("微信扫码支付请求参数reqParams:" + XmlUtils.parseXML(dataMap));
        resultMap = httpPost(dataMap, reqUrl);
        resultMap.put("out_trade_no", out_trade_no);
		return resultMap;
	}
	/**
	 * 
	 * 简要说明：使用微信小额支付，也就是扫码抢扫码支付
	 * 编写者：陈骑元
	 * 创建时间：2017年6月5日 下午11:07:02
	 * @param 说明
	 * @return 说明
	 */
	public static Map<String,String> unifiedPay(String out_trade_no, String auth_code,Double order_money, String order_content ){
		log.info("小额支付扫码支付请求开始...............................");
		Map<String, String> resultMap = new HashMap<String, String>();
		SortedMap<String, String> dataMap = new TreeMap<String, String>();
		dataMap.put("service", "unified.trade.micropay");
		dataMap.put("version", "2.0");
		dataMap.put("charset", SwiftpassConfig.charset);
		dataMap.put("sign_type", SwiftpassConfig.sign_type);
		dataMap.put("mch_id", SwiftpassConfig.mch_id);
		dataMap.put("notify_url", SwiftpassConfig.notify_url);
		dataMap.put("nonce_str", String.valueOf(new Date().getTime()));
		dataMap.put("out_trade_no", out_trade_no); // 商户订单号
		dataMap.put("auth_code", auth_code);
		dataMap.put("body", order_content);
		String wechat_pay = IMSCxt.getParamValue("wechat_pay");
		if ("on".equals(wechat_pay)) {
			Double total = order_money * 100;
			dataMap.put("total_fee", total.intValue() + "");
			
		}else{
			dataMap.put("total_fee",  "1");
		}
		
		String ip = IMSCxt.getParamValue("order_ip");
		dataMap.put("mch_create_ip", ip);
		Map<String,String> params = SignUtils.paraFilter(dataMap);
		StringBuilder buf = new StringBuilder((params.size() +1) * 10);
		SignUtils.buildPayParams(buf,params,false);
		String preStr = buf.toString();
		String sign = MD5.sign(preStr, "&key=" + SwiftpassConfig.key, "utf-8");
		dataMap.put("sign", sign);
		String reqUrl = SwiftpassConfig.req_url;
		log.info("小额扫码支付请求reqUrl：" + reqUrl);
		log.info("小额扫码支付请求参数reqParams:" + XmlUtils.parseXML(dataMap));
		resultMap = httpPost(dataMap, reqUrl);
		return resultMap;
	}
	/**
	 * 
	 * 简要说明：PC端微信扫码支付
	 * 编写者：陈骑元
	 * 创建时间：2017年6月5日 下午11:07:02
	 * @param 说明
	 * @return 说明
	 */
	public static Map<String,String> alipayScanCodePay(String out_trade_no, Double order_money, String order_content ){
		log.info("支付宝扫码支付请求开始...............................");
		Map<String, String> resultMap = new HashMap<String, String>();
		SortedMap<String, String> dataMap = new TreeMap<String, String>();
		dataMap.put("service", "pay.alipay.nativev2");
		dataMap.put("version", "2.0");
		dataMap.put("charset", SwiftpassConfig.charset);
		dataMap.put("sign_type", SwiftpassConfig.sign_type);
		dataMap.put("mch_id", SwiftpassConfig.mch_id);
		dataMap.put("notify_url", SwiftpassConfig.notify_url);
		dataMap.put("nonce_str", String.valueOf(new Date().getTime()));
		dataMap.put("out_trade_no", out_trade_no); // 商户订单号
		dataMap.put("body", order_content);
		String wechat_pay = IMSCxt.getParamValue("wechat_pay");
		if ("on".equals(wechat_pay)) {
			Double total = order_money * 100;
			dataMap.put("total_fee", total.intValue() + "");
			
		}else{
			dataMap.put("total_fee",  "1");
		}
		
		String ip = IMSCxt.getParamValue("order_ip");
		dataMap.put("mch_create_ip", ip);
		Map<String,String> params = SignUtils.paraFilter(dataMap);
		StringBuilder buf = new StringBuilder((params.size() +1) * 10);
		SignUtils.buildPayParams(buf,params,false);
		String preStr = buf.toString();
		String sign = MD5.sign(preStr, "&key=" + SwiftpassConfig.key, "utf-8");
		dataMap.put("sign", sign);
		String reqUrl = SwiftpassConfig.req_url;
		log.info("支付宝扫码支付请求reqUrl：" + reqUrl);
		log.info("支付宝扫码支付请求参数reqParams:" + XmlUtils.parseXML(dataMap));
		resultMap = httpPost(dataMap, reqUrl);
		resultMap.put("out_trade_no", out_trade_no);
		return resultMap;
	}

	/**
	 * 
	 * 简要说明： 编写者：陈骑元 创建时间：2017年6月3日 下午10:31:46
	 * 
	 * @param 说明
	 * @return 说明
	 */
	public static Map<String, String> appPayOrder(String out_trade_no, Double order_money, String order_content,
			String openid, String callbackUrl) {
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
		String wechat_pay = IMSCxt.getParamValue("wechat_pay");
		if ("on".equals(wechat_pay)) {
			Double total = order_money * 100;
			dataMap.put("total_fee", total.intValue() + "");
			
		}else{
			dataMap.put("total_fee",  "1");
		}
		if ("on".equals(wechat_pay)) {
			dataMap.put("sub_openid", openid);
		}
		String ip = IMSCxt.getParamValue("order_ip");
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
		resultMap.put("out_trade_no", out_trade_no);
		return resultMap;

	}

	/**
	 * 
	 * 简要说明： 处理APP支付通知信息 编写者：陈骑元 创建时间：2017年6月4日 下午1:26:52
	 * 
	 * @param 说明
	 * @return 说明
	 */
	public static Map<String, String> handleAppNodify(HttpServletRequest request) {
		log.info("微信支付APP通知信息.......................");
		Map<String, String> resultMap = new HashMap<String, String>();
		String resString = XmlUtils.parseRequst(request);
		log.info("通知内容：" + resString);
		String respString = IMSCons.REQUEST_ERROR;
		try {
			if (resString != null && !"".equals(resString)) {
			    resultMap = XmlUtils.toMap(resString.getBytes(), SwiftpassConfig.charset);
				String res = XmlUtils.toXml(resultMap);
				log.info("通知内容：" + res);
				if (resultMap.containsKey("sign")) {
					if (!SignUtils.checkParam(resultMap, SwiftpassConfig.key)) {
						log.error("支付通知处理数据失败：验证签名不通过");
						
					} else {
						String status = resultMap.get("status");
						if (status != null && "0".equals(status)) {
							String result_code = resultMap.get("result_code");
							if (result_code != null && "0".equals(result_code)) {
								respString=IMSCons.REQUEST_SUCCESS;
							} 
						}
					}
				}
			} 
		} catch (Exception e) {
			 log.error("微信支付通知处理失败，原因：",e);
		}
		 resultMap.put("handleStatus", respString);
		return resultMap;

	}
	/**
	 * 
	 * 简要说明：微信支付订单退款 编写者：陈骑元 创建时间：2017年6月4日 下午9:32:05
	 * 
	 * @param 说明
	 * @return 说明
	 */
	public static Map<String, String> appRefundOrder(String out_trade_no, Double refund_money,String out_refund_no) {
		log.info("微信退款请求开始...");
		Map<String, String> resultMap = new HashMap<String, String>();
		SortedMap<String, String> dataMap = new TreeMap<String, String>();
		dataMap.put("service", "trade.single.refund");
		dataMap.put("version", SwiftpassConfig.version);
		dataMap.put("charset", SwiftpassConfig.charset);
		dataMap.put("sign_type", SwiftpassConfig.sign_type);
		dataMap.put("out_trade_no", out_trade_no);
		Double total = refund_money * 100;
		dataMap.put("total_fee", total.intValue() + "");
		dataMap.put("refund_fee", total.intValue() + "");
		dataMap.put("out_refund_no", out_refund_no);
		String key = SwiftpassConfig.key; 
		String reqUrl = SwiftpassConfig.req_url;
		dataMap.put("mch_id", SwiftpassConfig.mch_id);
		dataMap.put("op_user_id", SwiftpassConfig.mch_id);
		dataMap.put("nonce_str", String.valueOf(new Date().getTime()));
		Map<String, String> params = SignUtils.paraFilter(dataMap);
		StringBuilder buf = new StringBuilder((params.size() + 1) * 10);
		SignUtils.buildPayParams(buf, params, false);
		String preStr = buf.toString();
		String sign = MD5.sign(preStr, "&key=" + key, SwiftpassConfig.charset);
		dataMap.put("sign", sign);
		log.info("微信退款请求reqUrl:" + reqUrl);
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
				log.info("支付结果：" + res);
				if (resultMap.containsKey("sign")&&!SignUtils.checkParam(resultMap, SwiftpassConfig.key)) {
					res = "验证签名不通过,原因:"+resultMap.get("message");
					log.error("验证签名不通过,原因:"+resultMap.get("message"));
				} else {
					if ("0".equals(resultMap.get("status")) && "0".equals(resultMap.get("result_code"))) {
						res = "ok";
					} else {
						String message=resultMap.get("message");
						if(IMSUtils.isNotEmpty(message)){
						    res="没有关注当前服务号";
							log.error("支付失败返回错误代码："+message);
						}else{
							String err_code = resultMap.get("err_code");
							String err_msg = resultMap.get("err_msg");
							res = err_msg;
							log.error("支付失败返回错误代码：" + err_code + "  错误代码描述：" + err_msg);
						}
						
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
		if ("ok".equals(res)) {
			resultMap.put("status", "0");
			resultMap.put("msg", "支付申请成功");

		} else {
			resultMap.put("status", "500");
			resultMap.put("msg", res);
		}

		return resultMap;
	}

}
