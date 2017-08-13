package com.ims.common.system.modules.controller;



import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


import com.ims.common.core.asset.IMSCons;
import com.ims.common.core.asset.IMSJson;
import com.ims.common.core.asset.IMSCxt;
import com.ims.common.core.matatype.Dto;
import com.ims.common.core.matatype.Dtos;
import com.ims.common.system.modules.po.UserPO;
import com.ims.common.system.modules.po.CardMenuPO;
import com.ims.common.system.modules.service.SystemService;




/**
 * 
 * 类描述： 公用控制器
 * 创建人：陈骑元
 * 邮箱：240823329@qq.com
 * 创建时间：Oct 9, 2016 10:14:03 PM
 * 修改人：
 * 修改时间：
 * 修改备注： 
 * @version 1.0
 */
@Controller
@RequestMapping(value = "system/login")
public class LoginController {
	@Autowired
	private SystemService systemService;
	/**
	 * 跳到登陆界面
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "goLogin")
	public  ModelAndView goLogin(HttpServletRequest request, HttpServletResponse response,HttpSession session) {
		ModelAndView modelAndView= new ModelAndView();
		modelAndView.setViewName("login.jsp");
		return modelAndView;
	}
	/**
	 * 登陆认证
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "doLogin")
	public void doLogin(HttpServletRequest request, HttpServletResponse response,HttpSession session) {
		Dto inDto = Dtos.newDto(request);
		Dto outDto =systemService.checkLogin(inDto);
		if(IMSCons.SUCCESS==outDto.getAppCode()){
			session.setAttribute(IMSCons.USERINFOKEY, outDto.get("userPO")); //存入Session
		}
		
		IMSCxt.write(response, IMSJson.toJson(outDto));
	}
	/**
	 * 进入主页面
	 * @param request
	 * @param response
	 * @param session
	 */
	@RequestMapping(value = "goMain")
	public  ModelAndView goMain(HttpServletRequest request, HttpServletResponse response,HttpSession session) {
		 ModelAndView modelAndView= new ModelAndView();
		 UserPO userPO=IMSCxt.getUserInfo(session);
		 List<CardMenuPO> cardMenus=null;
		 //如果是超级用户可以获取所有菜单的
		 if(IMSCons.SUPER_ADMIN.equals(userPO.getAccount())){
			 cardMenus=systemService.getSuperCardMenu(userPO.getUser_id());
		 }else{
			 cardMenus=systemService.getGrantCardMenu(userPO.getUser_id());
			 
		 }
	
		 modelAndView.addObject("cardMenus", cardMenus);
		 modelAndView.addObject("userPO", userPO);
		 //界面风格
		 String style=userPO.getStyle();
		 if(IMSCons.STYLE_TOP_LAYOUT.equals(style)){
			 modelAndView.setViewName("system/main/topStyleMain.jsp");
		 }else{
			 modelAndView.setViewName("system/main/defaultMain.jsp");
		 }
		
		 return modelAndView;
	}
	/**
	 * 
	 * 简要说明：进入工作平台首页
	 * 编写者：陈骑元
	 * 创建时间：2017年1月18日 下午2:17:50
	 * @param 说明
	 * @return 说明
	 */
	@RequestMapping(value = "goMainIndex")
	public  ModelAndView goMainIndex(HttpServletRequest request, HttpServletResponse response,HttpSession session) {
		ModelAndView modelAndView= new ModelAndView();
		modelAndView.setViewName("system/main/mainIndex.jsp");
		return modelAndView;
	}
	/**注销并安全退出系统
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "loginout")
	public  ModelAndView loginout(HttpServletRequest request, HttpServletResponse response,HttpSession session) {
		session.invalidate(); //注销退出
		ModelAndView modelAndView= new ModelAndView();
		modelAndView.setViewName("login.jsp");
		return modelAndView;
	}
}
