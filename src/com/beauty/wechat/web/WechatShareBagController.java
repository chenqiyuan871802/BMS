package com.beauty.wechat.web;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.beauty.common.constant.BeautyCons;
import com.beauty.common.service.OrderManageService;
import com.beauty.wechat.constant.WechatCons;
import com.beauty.wechat.model.Token;
import com.beauty.wechat.service.WechatCommonService;
import com.beauty.wechat.util.WechatCxt;
import com.ims.common.core.asset.IMSCxt;
import com.ims.common.core.asset.IMSJson;
import com.ims.common.core.matatype.Dto;
import com.ims.common.core.matatype.Dtos;

@Controller
@RequestMapping("wechat/shareBag")
public class WechatShareBagController {
	
	@Autowired
	private WechatCommonService wechatCommonService;
	@Autowired
	private OrderManageService orderManageService;
	/**
	 * 跳到微信登陆页面
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "initShareBag")
	public  ModelAndView initShareBag(String record_id,HttpServletRequest request, HttpServletResponse response,HttpSession session) throws IOException {
		response.sendRedirect(WechatCxt.getOauth2Url("wechat/shareBag/shareBag.jhtml?record_id="+record_id,WechatCons.GRANT_NO));
		return null;
	}
	/**
	 * 
	 * 简要说明：分享礼包领取页面
	 * 编写者：陈骑元
	 * 创建时间：2017年6月25日 下午9:19:01
	 * @param 说明
	 * @return 说明
	 */
	@RequestMapping(value = "shareBag")
	public ModelAndView shareBag(String record_id,String code,HttpServletRequest request, HttpServletResponse response){
		 ModelAndView modelAndView=new ModelAndView();
		 Token token=WechatCxt.getOauth2Token(code);
		 Dto outDto = orderManageService.saveAndCheckBag(record_id, token.getOpenid());
		 String returnFlag=outDto.getString("returnFlag");
		 if("1".equals(returnFlag)){
			 modelAndView.setViewName("beauty/wechat/bag/shareBagLogin.jsp");
		 }else{
			 modelAndView.setViewName("beauty/wechat/bag/shareBagSuccess.jsp");
		 }
		 modelAndView.addObject("dataDto", outDto);
		
		 return modelAndView;
		
	}
	
	/**
	 * 
	 * 简要说明：发送手机验证码
	 * 编写者：陈骑元
	 * 创建时间：2017年5月21日 上午12:52:42
	 * @param 说明
	 * @return 说明
	 */
	@RequestMapping(value = "saveCheckShareUser")
	public  void saveCheckShareUser(HttpServletRequest request, HttpServletResponse response){
		Dto inDto=Dtos.newDto(request);
		Dto outDto = orderManageService.saveCheckShareUser(inDto);
		IMSCxt.write(response, IMSJson.toJson(outDto));
	}
	/**
	 * 
	 * 简要说明：发送手机验证码
	 * 编写者：陈骑元
	 * 创建时间：2017年5月21日 上午12:52:42
	 * @param 说明
	 * @return 说明
	 */
	@RequestMapping(value = "saveShareBag")
	public   ModelAndView saveShareBag(String custom_user_id,String mobile,String record_id,HttpServletRequest request, HttpServletResponse response){
		 ModelAndView modelAndView=new ModelAndView();
		 Dto dataDto=orderManageService.saveShareBag(custom_user_id, mobile, record_id);
		 modelAndView.setViewName("beauty/wechat/bag/shareBagSuccess.jsp");
		 modelAndView.addObject("dataDto", dataDto);
		 return modelAndView;
	}
	/**
	 * 
	 * 简要说明：发送手机验证码
	 * 编写者：陈骑元
	 * 创建时间：2017年5月21日 上午12:52:42
	 * @param 说明
	 * @return 说明
	 */
	@RequestMapping(value = "sendCheckCode")
	public  void sendCheckCode(String mobile,HttpServletRequest request, HttpServletResponse response){
		String templateCode=IMSCxt.getParamValue(BeautyCons.BAG_CHECK_SMS_CODE);
		Dto outDto = wechatCommonService.sendCheckCode(mobile,templateCode);
		IMSCxt.write(response, IMSJson.toJson(outDto));
	}

}
