package com.beauty.wechat.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.beauty.wechat.constant.WechatCons;
import com.beauty.wechat.model.Token;
import com.beauty.wechat.model.WechatUser;
import com.beauty.wechat.service.WechatService;
import com.ims.common.core.asset.HttpRequestProxy;
import com.ims.common.core.asset.IMSJson;
import com.ims.common.core.asset.IMSPropertiesUtil;
import com.ims.common.core.asset.IMSUtils;
import com.ims.common.core.matatype.Dto;
import com.ims.common.core.matatype.Dtos;
import com.ims.common.core.matatype.impl.HashDto;

import sun.util.logging.resources.logging;

/**
 * 
 * 类名:com.beauty.wechat.util.WechatRequestUtil
 * 描述:微信请求处理工具类
 * 编写者:陈骑元
 * 创建时间:2017年4月29日 下午3:41:35
 * 修改说明:
 */
public class WechatReqUtil {
	
	private static Log log = LogFactory.getLog(WechatReqUtil.class);
	/**
	 * 
	 * 简要说明：获取微信的用户Token信息
	 * 编写者：陈骑元
	 * 创建时间：2017年4月29日 下午4:55:20
	 * @param 说明
	 * @return 说明
	 */
	public static Token getToken(String appid,String secret){
		Dto paramDto=Dtos.newDto();
		paramDto.put("grant_type", "client_credential");
		paramDto.put("appid", appid);
		paramDto.put("secret", secret);
		String returnMsg=HttpRequestProxy.doGet(WechatCons.ACCESS_TOKEN_URL,paramDto);
		log.info("获取微信token返回信息："+returnMsg);
	    Dto returnDto=IMSJson.fromJson(returnMsg, HashDto.class);
	    String errcode=returnDto.getString(WechatCons.RETURN_ERROR_INFO_CODE);
	    if(IMSUtils.isNotEmpty(errcode)){
	    	log.error("获取微信access_token接口出错,错误信息："+returnMsg);
	    	return null;
	    }
	    Token  token=new Token();
	    token.setAccessToken(returnDto.getString("access_token"));
	    token.setExpiresIn(returnDto.getInteger("expires_in"));
		return token;
	}
	/**
	 * 
	 * 简要说明：获取微信用户基本信息
	 * 编写者：陈骑元
	 * 创建时间：2017年4月29日 下午5:30:38
	 * @param 说明 access_token 调用接口凭证 openid普通用户的标识，对当前公众号唯一
	 * @return 说明
	 */
	public static WechatUser getUserInfo(String access_token,String openid){
		Dto paramDto=Dtos.newDto();
		paramDto.put("access_token", access_token);
		paramDto.put("openid", openid);
		String returnMsg=HttpRequestProxy.doGet(WechatCons.USER_INFO_URL,paramDto);
		log.info("获取微信用户基本信息接口返回信息："+returnMsg);
		Dto returnDto=IMSJson.fromJson(returnMsg, HashDto.class);
		String errcode=returnDto.getString(WechatCons.RETURN_ERROR_INFO_CODE);
		if(IMSUtils.isNotEmpty(errcode)){
			log.error("获取微信用户基本信息接口出错,错误信息："+returnMsg);
			return null;
		}
		
		WechatUser  wechatUser=new WechatUser();
	    IMSUtils.copyProperties(returnDto,wechatUser); 
		return wechatUser;
	}
	/**
	 * 
	 * 简要说明：获取网页授权的用户信息
	 * 编写者：陈骑元
	 * 创建时间：2017年5月20日 下午3:04:10
	 * @param 说明  网页授权access_token 用户openid;
	 * @return 说明
	 */
	public static WechatUser getOAuthUserInfo(String access_token,String openid){
		Dto paramDto=Dtos.newDto();
		paramDto.put("access_token", access_token);
		paramDto.put("openid", openid);
		paramDto.put("lang", "zh_CN");
		String returnMsg=HttpRequestProxy.doGet(WechatCons.OAUTH2__URER_URL,paramDto);
		log.info("网页授权获取微信用户基本信息接口返回信息："+returnMsg);
		Dto returnDto=IMSJson.fromJson(returnMsg, HashDto.class);
		String errcode=returnDto.getString(WechatCons.RETURN_ERROR_INFO_CODE);
		if(IMSUtils.isNotEmpty(errcode)){
			log.error("网页授权获取微信用户基本信息接口返回信息："+returnMsg);
			return null;
		}
		
		WechatUser  wechatUser=new WechatUser();
		IMSUtils.copyProperties(returnDto,wechatUser); 
		return wechatUser;
	}
	
	/**
	 * 
	 * 简要说明：创建微信菜单
	 * 编写者：陈骑元
	 * 创建时间：2017年4月29日 下午5:30:38
	 * @param 说明 access_token 调用接口凭证 menuJson 菜单Json数据
	 * @return 说明
	 */
	public static boolean  sendTemplateMessage(String access_token,String  templateMessageJson){
		Dto paramDto=Dtos.newDto();
		paramDto.put("access_token", access_token);
		String returnMsg=HttpRequestProxy.doJsonPost(WechatCons.SEND_TEMPLATE_MESSAGE_URL, paramDto,templateMessageJson);
		log.info("发送模板消息接口返回信息："+returnMsg);
		Dto returnDto=IMSJson.fromJson(returnMsg, HashDto.class);
		String errcode=returnDto.getString(WechatCons.RETURN_ERROR_INFO_CODE);
		if(WechatCons.RETURN_OK.equals(errcode)){
			log.info("发送模板消息执行成功");
			return true;
		}else{
			log.error("发送模板消息执行失败,错误信息："+returnMsg);
			return false;
		}
		
	}
	/**
	 * 
	 * 简要说明：创建微信菜单
	 * 编写者：陈骑元
	 * 创建时间：2017年4月29日 下午5:30:38
	 * @param 说明 access_token 调用接口凭证 menuJson 菜单Json数据
	 * @return 说明
	 */
	public static boolean  createMenu(String access_token,String menuJson){
		Dto paramDto=Dtos.newDto();
		paramDto.put("access_token", access_token);
		String returnMsg=HttpRequestProxy.doJsonPost(WechatCons.CREATE_MENU_URL, paramDto,menuJson);
		log.info("创建菜单接口返回信息："+returnMsg);
		Dto returnDto=IMSJson.fromJson(returnMsg, HashDto.class);
		String errcode=returnDto.getString(WechatCons.RETURN_ERROR_INFO_CODE);
		if(WechatCons.RETURN_OK.equals(errcode)){
			log.info("创建微信菜单接口执行成功");
			return true;
		}else{
			log.error("创建微信菜单接口执行失败,错误信息："+returnMsg);
			return false;
		}
		
	}
	
	/**
	 * 
	 * 简要说明：用户同意授权，获取code 发起URL地址
	 * 编写者：陈骑元
	 * 创建时间：2017年5月20日 上午1:08:28
	 * @param 说明 appid 公众号的唯一标识  redirect_uri授权后重定向的回调链接地址 scope 应用授权作用域
	 * @return 说明
	 */
	public static String getOauth2Url(String appid,String redirect_uri,String scope){
		String oauth2Url=WechatCons.OAUTH2_AUTHORIZE_URL+"?appid="+appid+"&redirect_uri="+
	                     redirect_uri+"&response_type=code&scope="+scope+"&state=1#wechat_redirect";
	    return oauth2Url;
	}
	/**
	 * 
	 * 简要说明：获取js的权限签名算法
	 * 编写者：陈骑元
	 * 创建时间：2017年6月25日 下午9:23:01
	 * @param 说明
	 * @return 说明
	 */
	public static String getTicket(String access_token){
		Dto paramDto=Dtos.newDto();
		paramDto.put("access_token", access_token);
		String returnMsg=HttpRequestProxy.doGet(WechatCons.JSAPI_TICKET_URL, paramDto);
		log.info("获取JS权限返回信息："+returnMsg);
		Dto returnDto=IMSJson.fromJson(returnMsg, HashDto.class);
		String errcode=returnDto.getString(WechatCons.RETURN_ERROR_INFO_CODE);
		if(WechatCons.RETURN_OK.equals(errcode)){
			String ticket=returnDto.getString("ticket");
			log.info("获取JS权限接口成功：ticke="+ticket);
			return ticket;
		}else{
			log.error("获取JS权限接口成功,错误信息："+returnMsg);
			
			return "";
		}		
	
	}
    /**
     * 
     * 简要说明：通过code换取网页网页授权accessToken
     * 编写者：陈骑元
     * 创建时间：2017年5月20日 上午10:46:40
     * @param 说明
     * @return 说明
     */
	public static Token getOauth2Token(String appid,String secret,String code){
		Dto paramDto=Dtos.newDto();
		paramDto.put("appid", appid);
		paramDto.put("secret", secret);
		paramDto.put("code", code);
		paramDto.put("grant_type", "authorization_code");
		String returnMsg=HttpRequestProxy.doGet(WechatCons.OAUTH2__TOKEN_URL,paramDto);
		log.info("获取微信网页授权接口返回信息："+returnMsg);
		Dto returnDto=IMSJson.fromJson(returnMsg, HashDto.class);
		String errcode=returnDto.getString(WechatCons.RETURN_ERROR_INFO_CODE);
		if(IMSUtils.isNotEmpty(errcode)){
			log.error("获取微信网页授权接口返回信息口出错,错误信息："+returnMsg);
			return null;
		}
		Token  token=new Token();
	    token.setAccessToken(returnDto.getString("access_token"));
	    token.setExpiresIn(returnDto.getInteger("expires_in"));
	    token.setRefreshToken(returnDto.getString("refresh_token"));
	    token.setOpenid(returnDto.getString("openid"));
	    token.setRefreshToken(returnDto.getString("scope"));
		return token;
	}
	/**
	 * 
	 * 简要说明：通过code换取网页网页授权accessToken
	 * 编写者：陈骑元
	 * 创建时间：2017年5月20日 上午10:46:40
	 * @param 说明
	 * @return 说明
	 */
	public static boolean sendTextMessage(String accessToken,String jsonString){
		Dto paramDto=Dtos.newDto();
		paramDto.put("access_token", accessToken);
		String returnMsg=HttpRequestProxy.doJsonPost(WechatCons.SEND_MESSAGE_URL, paramDto, jsonString);
		log.info("发送文本信息接口返回信息："+returnMsg);
		Dto returnDto=IMSJson.fromJson(returnMsg, HashDto.class);
		String errcode=returnDto.getString(WechatCons.RETURN_ERROR_INFO_CODE);
		if(!"0".equals(errcode)){
			log.error("文本消息群发失败！errcode=" +returnDto.getString("errcode") + ",errmsg = " + returnDto.getString("errmsg"));
			return false;
		}
		
		return true;
	}
	/**
	 * 
	 * 简要说明：多媒体下载接口
	 * 编写者：陈骑元
	 * 创建时间：2017年7月20日 上午12:26:00
	 * @param 说明  access_token 接口调用凭证  media_id 多媒体编号
	 * @return 说明 返回文件名信息
	 */
	public static String  downloadMedia(String access_token,String media_id,String filePath,String fileName){
		Dto paramDto=Dtos.newDto();
		paramDto.put("access_token", access_token);
		paramDto.put("media_id", media_id);
		String returnMsg=HttpRequestProxy.downMadGet(WechatCons.DOWNLOADIMG_URL,paramDto, filePath, fileName);
		log.info("获取微信多媒体文件下载返回信息："+returnMsg);
		if(IMSUtils.isNotEmpty(returnMsg)){
			Dto returnDto=IMSJson.fromJson(returnMsg, HashDto.class);
			String errcode=returnDto.getString(WechatCons.RETURN_ERROR_INFO_CODE);
			if(IMSUtils.isNotEmpty(errcode)){
				log.error("获取微信网页授权接口返回信息口出错,错误信息："+returnMsg);
				return null;
			}
			log.info("获取微信多媒体文件下载成功");
			return returnDto.getString("fileName");
		}else{
			log.error("获取微信多媒体文件下载失败");
			return null;
		}
		
	}
	public static void main(String[] args){
		
		WechatUser  wechatUser=getUserInfo("qBbAz4fPWUjDFb8ULIvA5CUTgdIuBeszCbXubPr_IDTvmJO9DJpB2-Ncym2yum5zH0rq1Eh54mn8YYU6S__O_LNWAQlKnAIiPREkcq-McdYLTLbAHATBT","oT6rnsiiSWnbx32vLkyn7XKzc5eA");
		System.out.println(wechatUser.getHeadimgurl());
	}

}
