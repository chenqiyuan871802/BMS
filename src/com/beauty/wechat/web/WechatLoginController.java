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
import com.beauty.common.po.CustomUserPO;
import com.beauty.common.service.CustomUserService;
import com.beauty.wechat.constant.WechatCons;
import com.beauty.wechat.model.Token;
import com.beauty.wechat.model.WechatUser;
import com.beauty.wechat.service.WechatCommonService;
import com.beauty.wechat.util.WechatCxt;
import com.ims.common.core.asset.IMSCons;
import com.ims.common.core.asset.IMSCxt;
import com.ims.common.core.asset.IMSJson;
import com.ims.common.core.asset.IMSUtils;
import com.ims.common.core.matatype.Dto;
import com.ims.common.core.matatype.Dtos;

/**
 * 
 * 类名:com.beauty.wechat.web.WechatLoginController
 * 描述:
 * 编写者:陈骑元
 * 创建时间:2017年5月19日 下午10:51:23
 * 修改说明:
 */
@Controller
@RequestMapping("wechat/login")
public class WechatLoginController {
	
	@Autowired
	private CustomUserService customUserService;
	@Autowired
	private WechatCommonService wechatCommonService;
	/**
	 *初始化登陆界面
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "initLogin")
	public  ModelAndView initLogin(HttpServletRequest request, HttpServletResponse response,HttpSession session) throws IOException {
		
		response.sendRedirect(WechatCxt.getOauth2Url("wechat/login/goWechatLogin.jhtml",WechatCons.GRANT_NO));
		return null;
	}
	/**
	 * 跳到微信登陆界面
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "goLogin")
	public  ModelAndView goLogin(String code,HttpServletRequest request, HttpServletResponse response,HttpSession session) throws IOException {
		ModelAndView modelAndView= new ModelAndView();
		 Token token=WechatCxt.getOauth2Token(code);
		
			if (IMSUtils.isNotEmpty(token)) {
				 String openid=token.getOpenid();
				Dto userDto = Dtos.newDto("openid", openid);
				userDto.put("is_del", IMSCons.IS.NO);
				CustomUserPO userPO = customUserService.selectOne(userDto);
				if (IMSUtils.isNotEmpty(userPO)) {
					String mobile=userPO.getMobile();
					if(IMSUtils.isNotEmpty(mobile)){  //未綁定微信手机号码
						session.setAttribute(BeautyCons.CUSTOM_USER_INFO_KEY, userPO); //存入Session
						session.setAttribute("accessTokenCode", code);
						if(IMSUtils.isNotEmpty(session.getAttribute("backRequestUri"))){
							try {
								response.sendRedirect(session.getAttribute("backRequestUri").toString());
							} catch (IOException e) {
								e.printStackTrace();
							} 
							return null;
						}
					}else{
						modelAndView.setViewName("beauty/wechat/login.jsp");
					}
					
				}else{
					
					session.setAttribute("accessTokenOpenid", openid); //存入Session openid[wechat]
					modelAndView.setViewName("beauty/wechat/login.jsp");
				}
			}else{
				modelAndView.setViewName("beauty/wechat/login.jsp");
			}
			
		modelAndView.addObject("token", token);
		return modelAndView;
	}
	
	/**
	 * 展示服务协议
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "showServerDeal")
	public  ModelAndView showServerDeal(HttpServletRequest request, HttpServletResponse response,HttpSession session) throws IOException {
		ModelAndView modelAndView= new ModelAndView();
	    modelAndView.setViewName("beauty/wechat/serverDeal.jsp");
		return modelAndView;
	}
	
	/**
	 * 跳到微信登陆页面
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "initWechatLogin")
	public  ModelAndView initWechatLogin(HttpServletRequest request, HttpServletResponse response,HttpSession session) throws IOException {
		
		response.sendRedirect(WechatCxt.getOauth2Url("wechat/login/goWechatLogin.jhtml",WechatCons.GRANT_YES));
		return null;
	}
	/**
	 * 跳到微信登陆页面
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "goWechatLogin")
	public  ModelAndView goWechatLogin(String code,HttpServletRequest request, HttpServletResponse response,HttpSession session) throws IOException {
		ModelAndView modelAndView= new ModelAndView();
		String rootPath=request.getSession().getServletContext().getRealPath("/");
		WechatUser wechatUser=WechatCxt.getOAuthUserInfo(code);
		CustomUserPO customUserPO=wechatCommonService.saveCustomUser(wechatUser, rootPath);
		modelAndView.addObject("customUserPO", customUserPO);
		modelAndView.setViewName("beauty/wechat/wechatLogin.jsp");
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
		String templateCode=IMSCxt.getParamValue(BeautyCons.CHECK_SMS_CODE);
		Dto outDto = wechatCommonService.sendCheckCode(mobile,templateCode);
		IMSCxt.write(response, IMSJson.toJson(outDto));
	}
	
	/**
	 * 登陆认证
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "doLogin")
	public void doLogin(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		Dto inDto = Dtos.newDto(request);
		Dto outDto=wechatCommonService.checkLogin(inDto);
		if(IMSCons.SUCCESS==outDto.getAppCode()){
			session.setAttribute(BeautyCons.CUSTOM_USER_INFO_KEY, outDto.get("customUserPO")); //存入Session
		}
		
		IMSCxt.write(response, IMSJson.toJson(outDto));
		
	}
	
	/**
	 * 跳到微信登陆页面
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "goAppLogin")
	public  ModelAndView goAppLogin(HttpServletRequest request, HttpServletResponse response,HttpSession session) throws IOException {
		ModelAndView modelAndView= new ModelAndView();
		modelAndView.setViewName("beauty/wechat/login.jsp");	
		return modelAndView;
	}
	

}
