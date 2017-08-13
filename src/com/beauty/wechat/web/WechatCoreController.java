package com.beauty.wechat.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.beauty.wechat.service.WechatService;
import com.beauty.wechat.util.SignUtil;
import com.ims.common.core.asset.IMSCxt;
import com.ims.common.core.asset.IMSPropertiesUtil;

/**
 * 
 * 类名:com.beauty.wechat.WechatCoreController
 * 描述:微信核心处理类
 * 编写者:陈骑元
 * 创建时间:2017年4月29日 上午9:54:35
 * 修改说明:
 */
@Controller
@RequestMapping(value = "wechat/wechatCode")
public class WechatCoreController {
	
	@Autowired
	private WechatService wechatService;
	/**
	 * 
	 * 简要说明：微信登陆调用验证接口
	 * 编写者：陈骑元
	 * 创建时间：2017年4月29日 上午10:10:07
	 * @param 说明
	 * @return 说明
	 */
	@RequestMapping(value="subscribe", method = RequestMethod.GET)
	public void subscribeGet(@RequestParam(value = "signature") String signature,
			@RequestParam(value = "timestamp") String timestamp,
			@RequestParam(value = "nonce") String nonce,
			@RequestParam(value = "echostr") String echostr,HttpServletRequest request,HttpServletResponse response) {
		//获取token
		String token= IMSPropertiesUtil.getString("wechat.token");
		//微信验证登陆
		if(SignUtil.checkSignature(token,signature,
				timestamp, nonce)){
			
			IMSCxt.writeRaw(response,echostr);
			
		}
		
	}
	/**
	 * 
	 * 简要说明：微信信息回调接口
	 * 编写者：陈骑元
	 * 创建时间：2017年4月29日 上午10:35:21
	 * @param 说明
	 * @return 说明
	 */
	@RequestMapping(value = "subscribe", method = RequestMethod.POST)
	public void subscribePost(HttpServletResponse response,HttpServletRequest request)  {
		String respMessage = wechatService.coreService(request);
		IMSCxt.writeRaw(response,respMessage);
	}

	

}
