package com.beauty.wechat.util;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.beauty.common.constant.BeautyCons;
import com.beauty.wechat.constant.WechatCons;
import com.beauty.wechat.model.Token;
import com.beauty.wechat.model.WechatUser;
import com.ims.common.core.asset.IMSCons;
import com.ims.common.core.asset.IMSCxt;
import com.ims.common.core.asset.IMSJson;
import com.ims.common.core.asset.IMSPropertiesUtil;
import com.ims.common.core.asset.IMSUtils;
import com.ims.common.core.matatype.Dto;
import com.ims.common.core.matatype.Dtos;
import com.ims.common.core.support.redis.JedisUtil;

import redis.clients.jedis.Jedis;

/**
 * 
 * 类名:com.beauty.wechat.util.WechatCxt 描述:微信上下文接口 编写者:陈骑元 创建时间:2017年4月30日
 * 上午12:24:50 修改说明:
 */
public class WechatCxt {

	private static Log log = LogFactory.getLog(WechatCxt.class);

	/**
	 * 
	 * 简要说明：获取用户accessToken信息 编写者：陈骑元 创建时间：2017年4月30日 上午12:25:58
	 * 
	 * @param 说明
	 * @return 说明
	 */
	public static String getAccessToken() {
		// 获取公众号唯一凭证
		String appid = IMSPropertiesUtil.getString("wechat.appID");
		// 获取公众号户唯一凭证密钥
		String secret = IMSPropertiesUtil.getString("wechat.appsecret");
		String accessTokenKey = WechatCons.ACCESS_TOKEN_KEY;
		String accessToken = "";
		if (JedisUtil.isLive()) {
			Jedis jedis = JedisUtil.getJedisClient();
			if (jedis.exists(accessTokenKey)) { // 如果redis中存在accessToken的键，则还没失效直接取出来
				accessToken = jedis.get(accessTokenKey);
			} else {
				Token token = WechatReqUtil.getToken(appid, secret);
				if (IMSUtils.isNotEmpty(token)) {
					accessToken = token.getAccessToken();
					jedis.set(accessTokenKey, accessToken);
					jedis.expire(accessTokenKey, token.getExpiresIn());
				}
			}
			JedisUtil.close(jedis);
		} else {
			Token token = WechatReqUtil.getToken(appid, secret);
			if (IMSUtils.isNotEmpty(token)) {
				accessToken = token.getAccessToken();

			}
		}
		return accessToken;
	}

	/**
	 * 
	 * 简要说明：获取用户基本信息 编写者：陈骑元 创建时间：2017年4月30日 上午9:58:16
	 * 
	 * @param 说明
	 * @return 说明
	 */
	public static WechatUser getUserInfo(String openid) {
		String accessToken = getAccessToken();
		if (IMSUtils.isEmpty(accessToken)) {
			log.error("获取微信用户基本信息出错：因为无法获取到微信的token信息，请检查相关配置是否正确");
			return null;
		}
		if (IMSUtils.isEmpty(openid)) {
			log.error("获取微信用户基本信息出：用户公众标识openid参数为空");
			return null;
		}
		WechatUser wechatUser = WechatReqUtil.getUserInfo(accessToken, openid);
		return wechatUser;
	}

	/**
	 * 
	 * 简要说明： 编写者：陈骑元 创建时间：2017年5月20日 上午1:17:47
	 * 
	 * @param 说明
	 *            redirect_uri跳转的URL地址
	 * @return 说明
	 */
	public static String getOauth2Url(String redirect_uri,String grant_type) {
		// 获取公众号唯一凭证
		String appid = IMSPropertiesUtil.getString("wechat.appID");
		String request_url = IMSCxt.getParamValue(BeautyCons.REQUEST_URL_KEY); // 系统请求的地址
		redirect_uri = request_url + "/" + redirect_uri;
		String scope = "snsapi_base";
		if(WechatCons.GRANT_YES.equals(grant_type)){
			scope = "snsapi_userinfo";
		}
       String oauth2Url=WechatReqUtil.getOauth2Url(appid, redirect_uri, scope);
       log.info("oauth2Url授权地址:"+oauth2Url);
		return oauth2Url;
	}
	/**
	 * 
	 * 简要说明： 编写者：陈骑元 创建时间：2017年5月20日 上午1:17:47
	 * 
	 * @param 说明
	 *            redirect_uri跳转的URL地址
	 * @return 说明
	 */
	public static Token getOauth2Token(String code) {
		// 获取公众号唯一凭证
		String appid = IMSPropertiesUtil.getString("wechat.appID");
		// 获取公众号户唯一凭证密钥
		String secret = IMSPropertiesUtil.getString("wechat.appsecret");
		
		return WechatReqUtil.getOauth2Token(appid, secret, code);
	}

	/**
	 * 
	 * 简要说明：获取网页授权用户信息
	 * 编写者：陈骑元
	 * 创建时间：2017年5月20日 下午3:14:37
	 * @param 说明
	 * @return 说明
	 */
	public static  WechatUser getOAuthUserInfo(String code) {
	   Token token=getOauth2Token(code);
		
	   return WechatReqUtil.getOAuthUserInfo(token.getAccessToken(), token.getOpenid());
	}
	
	
	/**
	 * 
	 * 简要说明：
	 * 编写者：陈骑元
	 * 创建时间：2017年6月25日 下午9:43:44
	 * @param 说明
	 * @return 说明
	 */
	public static Dto getJsApiConfig(String url){
		Dto outDto=Dtos.newDto();
		String ticket=getTicket();
		if(IMSUtils.isNotEmpty(ticket)){  //获取到JS签名权限
		   Dto signDto=SignUtil.jsApiSign(ticket, url);
		   if(IMSUtils.isNotEmpty(signDto)){
			   String appid = IMSPropertiesUtil.getString("wechat.appID");
			   outDto.put("appid",appid);
			   outDto.putAll(signDto);
			   outDto.setAppCode(IMSCons.SUCCESS);
			   outDto.setAppMsg("获取JS权限签名成功。");
		   }else{
			  outDto.setAppCode(IMSCons.ERROR);
		      outDto.setAppMsg("获取JS权限签名失败。");
		   }
		}else{
			 outDto.setAppCode(IMSCons.ERROR);
		     outDto.setAppMsg("获取JS权限签名失败。");
		     
		}
		return outDto;
	}
	
	/**
	 * 
	 * 简要说明：获取网页授权JS权限
	 * 编写者：陈骑元
	 * 创建时间：2017年5月20日 下午3:14:37
	 * @param 说明
	 * @return 说明
	 */
	public static  String  getTicket() {
		String ticket="";
		if (JedisUtil.isLive()) {
			String jsapiTicketKey=WechatCons.TICKET_KEY;
			Jedis jedis = JedisUtil.getJedisClient();
			if (jedis.exists(jsapiTicketKey)) { // 如果redis中存在jsapi_ticket的键，则还没失效直接取出来
				ticket = jedis.get(jsapiTicketKey);
			} else {
				  ticket=getJsApiTicket();
				if (IMSUtils.isNotEmpty(ticket)) {
					jedis.set(jsapiTicketKey, ticket);
					jedis.expire(jsapiTicketKey,7100);
				}
			}
			JedisUtil.close(jedis);
		} else {
			ticket=getJsApiTicket();
		}
		return ticket;
	}
	/**
	 * 
	 * 简要说明：获取JS权限签名
	 * 编写者：陈骑元
	 * 创建时间：2017年6月25日 下午9:38:27
	 * @param 说明
	 * @return 说明
	 */
	public static String getJsApiTicket(){
		String accessToken = getAccessToken();
		if (IMSUtils.isEmpty(accessToken)) {
			log.error("获取JS权限出错：因为无法获取到微信的token信息，请检查相关配置是否正确");
			return "";
		}
		
		return WechatReqUtil.getTicket(accessToken);
	}
	/**
	 * 
	 * 简要说明：多媒体文件下载
	 * 编写者：陈骑元
	 * 创建时间：2017年7月20日 上午12:52:27
	 * @param 说明
	 * @return 说明
	 */
	public static String  downloadMedia(String media_id,String filePath,String fileId){
		String accessToken = getAccessToken();
		if (IMSUtils.isEmpty(accessToken)) {
			log.error("多媒体文件下载出错：因为无法获取到微信的token信息，请检查相关配置是否正确");
			return null;
		}
		return WechatReqUtil.downloadMedia(accessToken, media_id, filePath, fileId);
	}
	/**
	 * 
	 * 简要说明：创建微信菜单接口 编写者：陈骑元 创建时间：2017年4月30日 上午9:58:16
	 * 
	 * @param 说明
	 * @return 说明
	 */
	public static boolean createMenu(String menuJson) {
		String accessToken = getAccessToken();
		if (IMSUtils.isEmpty(accessToken)) {
			log.error("创建微信菜单接口出错：因为无法获取到微信的token信息，请检查相关配置是否正确");
			return false;
		}
		if (IMSUtils.isEmpty(menuJson)) {
			log.error("获取微信用户基本信息出：用户公众标识openid参数为空");
			return false;
		}
		boolean result = WechatReqUtil.createMenu(accessToken, menuJson);
		return result;

	}
	/**
	 * 
	 * 简要说明：发送文本信息
	 * 编写者：陈骑元
	 * 创建时间：2017年6月19日 下午9:51:52
	 * @param 说明
	 * @return 说明
	 */
	public static boolean  sendTextMessage(List<String> openids,String content) {
		String accessToken = getAccessToken();
		if (IMSUtils.isEmpty(accessToken)) {
			log.error("群发文本消息接口出错：因为无法获取到微信的token信息，请检查相关配置是否正确");
			return false;
		}
		Dto messageDto=Dtos.newDto();
		Dto textDto=Dtos.newDto();
		messageDto.put("touser", openids);
		textDto.put("content", content);
		messageDto.put("text",textDto);
		messageDto.put("msgtype", "text");
	   String jsonString=IMSJson.toJson(messageDto);
	   System.out.println(jsonString);
		return WechatReqUtil.sendTextMessage(accessToken, jsonString);
		
	}
	/**
	 * 
	 * 简要说明：发送模板消息
	 * 编写者：陈骑元
	 * 创建时间：2017年6月19日 下午9:51:52
	 * @param 说明
	 * @return 说明
	 */
	public static boolean  sendTemplateMessage(String templateMessageJson) {
		String accessToken = getAccessToken();
		if (IMSUtils.isEmpty(accessToken)) {
			log.error("发送模板消息接口出错：因为无法获取到微信的token信息，请检查相关配置是否正确");
			return false;
		}
		if (IMSUtils.isEmpty(templateMessageJson)) {
			log.error("发送模板消息接口出错：模板消息为空");
			return false;
		}
		boolean result = WechatReqUtil.sendTemplateMessage(accessToken, templateMessageJson);
		return result;
		
	}
	/**
	 * 
	 * 简要说明：发送支付完成订单微信通知
	 * 编写者：陈骑元
	 * 创建时间：2017年6月20日 上午12:06:33
	 * @param 说明
	 * @return 说明
	 */
    public static boolean sendPayOrderMessage(String openid,String order_id,String order_content,String money_content ){
    	//发送预约通知消息
		Dto templateDto=Dtos.newDto();
		String template_id=IMSCxt.getParamValue(BeautyCons.PAY_NODIFY_KEY);
		String url=IMSCxt.getParamValue(BeautyCons.REQUEST_URL_KEY)+"/wechat/home/goFeedback.jhtml";
		templateDto.put("touser",openid);
		templateDto.put("template_id", template_id);
		templateDto.put("url", url);
		Dto dataDto=Dtos.newDto();
		Dto productTypeDto=Dtos.newDto();  //消息头
		productTypeDto.put("value", "消费项目");
		dataDto.put("productType", productTypeDto);
		Dto nameDto=Dtos.newDto();  //消息头
		nameDto.put("value", order_content);
		dataDto.put("name", nameDto);
		Dto keyOneDto=Dtos.newDto();//消息结尾
		keyOneDto.put("value", "金额");
		Dto keyTwoDto=Dtos.newDto();//消息结尾
		keyTwoDto.put("value", money_content);
		keyTwoDto.put("color", "#000000");
		dataDto.put("account", keyTwoDto);
		dataDto.put("accountType", keyOneDto);
		Dto timeDto=Dtos.newDto();//消息结尾
		timeDto.put("value", IMSUtils.getDateStr("yyyy年MM月dd日 HH:mm"));
		timeDto.put("color", "#000000");
		dataDto.put("time", timeDto);
		Dto remarkDto=Dtos.newDto();//消息结尾
		remarkDto.put("value", "服务已经完成了哦~如果有任何问题可以戳这里告诉我们呢！");
		remarkDto.put("color", "#000000");
		dataDto.put("remark", remarkDto);
		templateDto.put("data", dataDto);
		String templateMessageJson=IMSJson.toJson(templateDto);
	    return sendTemplateMessage(templateMessageJson);
    }
    /**
     * 
     * 简要说明：发送项目支付订单
     * 编写者：陈骑元
     * 创建时间：2017年6月20日 上午12:06:33
     * @param 说明
     * @return 说明
     */
    public static boolean sendProjectPayOrderMessage(String openid,String order_id,String order_content,String bag_content ){
    	//发送预约通知消息
    	Dto templateDto=Dtos.newDto();
    	String template_id=IMSCxt.getParamValue(BeautyCons.PAY_NODIFY_KEY);
    	String url=IMSCxt.getParamValue(BeautyCons.REQUEST_URL_KEY)+"/wechat/home/goFeedback.jhtml";
    	templateDto.put("touser",openid);
    	templateDto.put("template_id", template_id);
    	templateDto.put("url", url);
    	Dto dataDto=Dtos.newDto();
    	Dto productTypeDto=Dtos.newDto();  //消息头
    	productTypeDto.put("value", "礼包支付");
    	dataDto.put("productType", productTypeDto);
    	Dto nameDto=Dtos.newDto();  //消息头
    	nameDto.put("value", bag_content);
    	dataDto.put("name", nameDto);
    	Dto keyOneDto=Dtos.newDto();//消息结尾
    	keyOneDto.put("value", "项目");
    	Dto keyTwoDto=Dtos.newDto();//消息结尾
    	keyTwoDto.put("value", order_content);
    	keyTwoDto.put("color", "#000000");
    	dataDto.put("account", keyTwoDto);
    	dataDto.put("accountType", keyOneDto);
    	Dto timeDto=Dtos.newDto();//消息结尾
    	timeDto.put("value", IMSUtils.getDateStr("yyyy年MM月dd日 HH:mm"));
    	timeDto.put("color", "#000000");
    	dataDto.put("time", timeDto);
    	Dto remarkDto=Dtos.newDto();//消息结尾
    	remarkDto.put("value", "服务已经完成了哦~如果有任何问题可以戳这里告诉我们呢！");
    	remarkDto.put("color", "#000000");
    	dataDto.put("remark", remarkDto);
    	templateDto.put("data", dataDto);
    	String templateMessageJson=IMSJson.toJson(templateDto);
    	return sendTemplateMessage(templateMessageJson);
    }
    
    /**
     * 
     * 简要说明：发送支付完成订单微信通知
     * 编写者：陈骑元
     * 创建时间：2017年6月20日 上午12:06:33
     * @param 说明
     * @return 说明
     */
    public static boolean sendSubscribeOrderMessage(String openid,String order_id,String order_content,String subscribe_time ){
    	//发送预约通知消息
		Dto templateDto=Dtos.newDto();
		String template_id=IMSCxt.getParamValue(BeautyCons.SUBSCRIBE_MESSAGE_KEY);
		String url=IMSCxt.getParamValue(BeautyCons.REQUEST_URL_KEY)+"/wechat/order/showNurseOrderDetail.jhtml?order_id="+order_id;
		templateDto.put("touser", openid);
		templateDto.put("template_id", template_id);
		templateDto.put("url", url);
		Dto dataDto=Dtos.newDto();
		Dto firstDto=Dtos.newDto();  //消息头
		firstDto.put("value", "您的订单已预约成功。");
		firstDto.put("color", "#000000");
		dataDto.put("first", firstDto);
		Dto keyOneDto=Dtos.newDto();//消息结尾
		keyOneDto.put("value", order_content);
		keyOneDto.put("color", "#000000");
		Dto keyTwoDto=Dtos.newDto();//消息结尾
	
		keyTwoDto.put("value", subscribe_time);
		keyTwoDto.put("color", "#000000");
		dataDto.put("keyword1", keyOneDto);
		dataDto.put("keyword2", keyTwoDto);
		Dto remarkDto=Dtos.newDto();//消息结尾
		remarkDto.put("value", "我们在这里等你哟~");
		remarkDto.put("color", "#000000");
		dataDto.put("remark", remarkDto);
		templateDto.put("data", dataDto);
		String templateMessageJson=IMSJson.toJson(templateDto);
	
    	return 	sendTemplateMessage(templateMessageJson);
    }
    
    
	/**
	 * 
	 * 简要说明： 编写者：陈骑元 创建时间：2017年4月30日 上午1:11:38
	 * 
	 * @param 说明
	 * @return 说明
	 */
	public static void main(String[] args) {
		System.out.println(getAccessToken());
	}

}
