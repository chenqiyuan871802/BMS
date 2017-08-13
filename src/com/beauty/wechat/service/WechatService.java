package com.beauty.wechat.service;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beauty.asyncTask.WechatAsyncTask;
import com.beauty.common.constant.BeautyCons;
import com.beauty.wechat.constant.WechatCons;
import com.beauty.wechat.model.TextMessageResp;
import com.beauty.wechat.util.MessageUtil;
import com.ims.common.core.asset.IMSCxt;
import com.ims.common.core.matatype.Dto;
/**
 * 
 * 类名:com.beauty.wechat.service.WechatService
 * 描述:微信核心业务处理类
 * 编写者:陈骑元
 * 创建时间:2017年4月29日 上午10:55:53
 * 修改说明:
 */
@Service("wechatService")
public class WechatService {
	//异步处理
	@Autowired
	private WechatAsyncTask wechatAsyncTask;
	
	private static Log log = LogFactory.getLog(WechatService.class);
	public String coreService(HttpServletRequest request) {
		String respMessage = null;
		try {
			String rootPath=request.getSession().getServletContext()
					.getRealPath("/");
			// 默认返回的文本消息内容
			String respContent = "请求处理异常，请稍候尝试！";
			// xml请求解析
			Dto  requestDto = MessageUtil.parseXml(request);
			// 发送方帐号（open_id）
			String fromUserName = requestDto.getString("FromUserName");
			// 公众帐号
			String toUserName = requestDto.getString("ToUserName");
			// 消息类型
			String msgType = requestDto.getString("MsgType");
			String msgId = requestDto.getString("MsgId");
			//消息内容
			String content = requestDto.getString("Content");
			log.info("------------微信客户端发送请求---------------------   |   fromUserName:"+fromUserName+"   |   ToUserName:"+toUserName+"   |   msgType:"+msgType+"   |   msgId:"+msgId+"   |   content:"+content);
			//根据微信ID,获取配置的全局的数据权限ID
			log.info("-toUserName--------"+toUserName);
			 if(!msgType.equals(WechatCons.REQ_MESSAGE_TYPE_EVENT)){
				 respMessage=this.sendDefaultMsg(requestDto);
			 }
			//【微信触发类型】文本消息
			if (msgType.equals(WechatCons.REQ_MESSAGE_TYPE_TEXT)) {
				log.info("------------微信客户端发送请求------------------【微信触发类型】文本消息---");
				
			}
			//【微信触发类型】图片消息
			else if (msgType.equals(WechatCons.REQ_MESSAGE_TYPE_IMAGE)) {
				respContent = "您发送的是图片消息！";
			}
			//【微信触发类型】地理位置消息
			else if (msgType.equals(WechatCons.REQ_MESSAGE_TYPE_LOCATION)) {
				respContent = "您发送的是地理位置消息！";
			}
			//【微信触发类型】链接消息
			else if (msgType.equals(WechatCons.REQ_MESSAGE_TYPE_LINK)) {
				respContent = "您发送的是链接消息！";
			}
			//【微信触发类型】音频消息
			else if (msgType.equals(WechatCons.REQ_MESSAGE_TYPE_VOICE)) {
				respContent = "您发送的是音频消息！";
			}
			//【微信触发类型】事件推送
			else if (msgType.equals(WechatCons.REQ_MESSAGE_TYPE_EVENT)) {
				log.info("------------微信客户端发送请求------------------【微信触发类型】事件推送---");
				// 事件类型
				String eventType = requestDto.getString("Event");
				
				if (eventType.equals(WechatCons.EVENT_TYPE_SUBSCRIBE)) {// 订阅
					wechatAsyncTask.asyncSubscribeEvent(requestDto, rootPath);
					respMessage=this.sendWelcomMsg(requestDto); //发送欢迎消息
				}else if (eventType.equals(WechatCons.EVENT_TYPE_UNSUBSCRIBE)) {// 取消订阅
					wechatAsyncTask.asyncUnSubscribeEvent(requestDto);
				}else if (eventType.equals(WechatCons.EVENT_TYPE_CLICK)) {// 自定义菜单点击事件
					
				}else if (eventType.equals(WechatCons.EVENT_TYPE_LOCATION)) {//上报地理位置信息
					requestDto.println();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return respMessage;
	}
	/**
	 * 
	 * 简要说明：返回欢迎消息
	 * 编写者：陈骑元
	 * 创建时间：2017年5月2日 上午12:20:26
	 * @param 说明
	 * @return 说明
	 */
	public   String sendWelcomMsg(Dto inDto){
		// 发送方帐号（open_id）
		String fromUserName = inDto.getString("FromUserName");
		// 公众帐号
		String toUserName = inDto.getString("ToUserName"); 
		TextMessageResp textMessage = new TextMessageResp();
		textMessage.setToUserName(fromUserName);
		textMessage.setFromUserName(toUserName);
		textMessage.setCreateTime(new Date().getTime());
		textMessage.setMsgType(WechatCons.RESP_MESSAGE_TYPE_TEXT);
		textMessage.setContent(IMSCxt.getParamValue(BeautyCons.WELCOM_MSG_KEY));
		String respMessage= MessageUtil.textMessageToXml(textMessage);
		return respMessage;
	}
	/**
	 * 
	 * 简要说明：返回欢迎消息
	 * 编写者：陈骑元
	 * 创建时间：2017年5月2日 上午12:20:26
	 * @param 说明
	 * @return 说明
	 */
	public   String sendDefaultMsg(Dto inDto){
		// 发送方帐号（open_id）
		String fromUserName = inDto.getString("FromUserName");
		// 公众帐号
		String toUserName = inDto.getString("ToUserName"); 
		TextMessageResp textMessage = new TextMessageResp();
		textMessage.setToUserName(fromUserName);
		textMessage.setFromUserName(toUserName);
		textMessage.setCreateTime(new Date().getTime());
		textMessage.setMsgType(WechatCons.RESP_MESSAGE_TYPE_TEXT);
		textMessage.setContent(IMSCxt.getParamValue(BeautyCons.RESPONSE_MSG_KEY));
		String respMessage= MessageUtil.textMessageToXml(textMessage);
		return respMessage;
	}
	
	
}
