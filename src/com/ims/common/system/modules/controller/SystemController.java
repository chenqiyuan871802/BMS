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
import com.ims.common.system.modules.po.TreeModel;
import com.ims.common.system.modules.service.SystemService;
import com.ims.common.system.modules.service.UserService;




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
@RequestMapping(value = "system")
public class SystemController {
	@Autowired
	private SystemService systemService;
	@Autowired
    private  UserService userService;
	
	
	/**
	 * 初始化授权菜单页面
	 *
	 * @return
	 */
	@RequestMapping(value = "initGrantMenu")
	public ModelAndView initGrantMenu(String role_id) {
		 ModelAndView modelAndView= new ModelAndView();
		 modelAndView.addObject("role_id",role_id);
		 modelAndView.setViewName("system/role/grantMenu.jsp");
		 return modelAndView;
		 
	}

	/**
	 * 
	 * 加载授权菜单树
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "loadGrantMenuTree")
	public void loadGrantMenuTree(HttpServletRequest request, HttpServletResponse response,HttpSession session) {
		Dto pDto=Dtos.newDto(request);
		UserPO userPO=IMSCxt.getUserInfo(session);
		TreeModel treeModel=systemService.loadGrantMenuTree(pDto,userPO);
		String outString ="["+IMSJson.toJson(treeModel)+"]";
		IMSCxt.write(response, outString);
	}
	
	/**
	 * 初始化授权用户页面
	 *
	 * @return
	 */
	@RequestMapping(value = "initGrantUser")
	public ModelAndView initGreantUser(String role_id) {
		ModelAndView modelAndView= new ModelAndView();
		modelAndView.addObject("role_id",role_id);
		modelAndView.setViewName("system/role/grantUser.jsp");
		return modelAndView;
		
	}
	/**
	 * 
	 * 查询待选的用户列表
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "listUser")
	public void listUser(HttpServletRequest request, HttpServletResponse response) {
		Dto pDto=Dtos.newDto(request);
		List<UserPO> userList=systemService.listUserPage(pDto);
		String outString = IMSJson.toGridJson(userList, pDto.getPageTotal(),IMSCons.DATE);
		IMSCxt.write(response, outString);
	}
	/**
	 * 
	 * 查询已经选择的用户
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "listSelectUser")
	public void listSelectUser(HttpServletRequest request, HttpServletResponse response) {
		Dto pDto=Dtos.newDto(request);
		List<UserPO> userList=systemService.listSelectUserPage(pDto);
		String outString = IMSJson.toGridJson(userList, pDto.getPageTotal(),IMSCons.DATE);
		IMSCxt.write(response, outString);
	}
	/**
	 * 保存角色授权菜单信息
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "saveRoleMenu")
	public void saveRoleMenu(HttpServletRequest request, HttpServletResponse response) {
		Dto inDto = Dtos.newDto(request);
		Dto outDto =systemService.saveRoleMenu(inDto);
		IMSCxt.write(response, IMSJson.toJson(outDto));
	}
	/**
	 * 保存用户授权角色信息
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "saveRoleUser")
	public void saveRoleUser(HttpServletRequest request, HttpServletResponse response) {
		Dto inDto = Dtos.newDto(request);
		Dto outDto =systemService.saveRoleUser(inDto);
		IMSCxt.write(response, IMSJson.toJson(outDto));
	}
	/**
	 * 撤销用户授权角色信息
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "deleteRoleUser")
	public void deleteRoleUser(HttpServletRequest request, HttpServletResponse response) {
		Dto inDto = Dtos.newDto(request);
		Dto outDto =systemService.deleteRoleUser(inDto);
		IMSCxt.write(response, IMSJson.toJson(outDto));
	}
	
	

	/**
	 * 更新当前用户密码
	 * @param request
	 * @param response
	 */
	@RequestMapping("updateUserPassword")
	public void updateUserPassword(HttpServletRequest request, HttpServletResponse response,HttpSession session){
		Dto inDto = Dtos.newDto(request);
		UserPO userPO=IMSCxt.getUserInfo(session);
		Dto outDto =systemService.updateUserPassword(inDto, userPO);
		IMSCxt.write(response, IMSJson.toJson(outDto));
	}
	/**
	 * 
	 * 简要说明：跳到用户修改密码界面
	 * 编写者：陈骑元
	 * 创建时间：2017年2月6日 上午11:03:35
	 * @param 说明
	 * @return 说明
	 */
	@RequestMapping("goModifyUserPassword")
	public ModelAndView goModifyUserPassword(HttpServletRequest request, HttpServletResponse response,HttpSession session){
		ModelAndView modelAndView= new ModelAndView();
		modelAndView.setViewName("system/main/modifyUserPassword.jsp");
		return modelAndView;
	}
	/**
	 * 
	 * 简要说明：初始化用户设置界面
	 * 编写者：陈骑元
	 * 创建时间：2017年2月6日 上午11:03:35
	 * @param 说明
	 * @return 说明
	 */
	@RequestMapping("goUserSet")
	public ModelAndView goUserSet(HttpServletRequest request, HttpServletResponse response,HttpSession session){
		ModelAndView modelAndView= new ModelAndView();
		UserPO userPO=IMSCxt.getUserInfo(session);
		modelAndView.addObject("userPO", userPO);
		modelAndView.setViewName("system/main/userSet.jsp");
		return modelAndView;
	}
	
	/**
	 * 
	 * 简要说明：更新个人设置信息
	 * 编写者：陈骑元
	 * 创建时间：2017年2月6日 下午3:41:07
	 * @param 说明
	 * @return 说明
	 */
	@RequestMapping("updateUserSet")
	public void updateUserSet(HttpServletRequest request, HttpServletResponse response,HttpSession session){
		Dto inDto = Dtos.newDto(request);
		Dto outDto =userService.update(inDto);
		//重新存储session
		String user_id=inDto.getString("user_id");
		UserPO userPO=userService.selectByKey(user_id);
		session.setAttribute(IMSCons.USERINFOKEY,userPO);
	    IMSCxt.write(response, IMSJson.toJson(outDto));
	}
	
}
