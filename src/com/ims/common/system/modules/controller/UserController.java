package com.ims.common.system.modules.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.ims.common.core.asset.IMSJson;
import com.ims.common.core.asset.IMSCxt;
import com.ims.common.core.matatype.Dto;
import com.ims.common.core.matatype.Dtos;
import com.ims.common.core.asset.IMSCons;
import com.ims.common.system.asset.DicCons;
import com.ims.common.system.modules.po.UserPO;
import com.ims.common.system.modules.service.UserService;

/**
 * 
 * 类描述：<b>用户基本信息表[sys_user控制类</b>
 * 创建人：陈骑元
 * 邮箱：240823329@qq.com
 * 创建时间：2016-12-18 08:25:38
 * 修改人：
 * 修改时间：
 * 修改备注： 
 * @version 1.0
 */
@Controller
@RequestMapping(value = "system/user")
public class UserController {
  
    @Autowired
	private  UserService userService;
	
	
	/**
	 * 页面初始化
	 *
	 * @return
	 */
	@RequestMapping(value = "init")
	public ModelAndView init() {
		
		return new ModelAndView("system/user/userList.jsp");
	}
	
	/**
	 * 
	 * 分页查询用户基本信息表信息
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "listUser")
	public void listUser(HttpServletRequest request, HttpServletResponse response) {
		Dto pDto = Dtos.newDto(request);
		pDto.put("is_del", IMSCons.IS.NO);
		//只查询普通的用户
		pDto.put("user_type", DicCons.USER_STATUS_NORMAL); 
		pDto.setOrder(" create_time ASC ");
		List<UserPO> userList =userService.queryPage(pDto);
		String outString = IMSJson.toGridJson(userList, pDto.getPageTotal());
		IMSCxt.write(response, outString);
	}
	/**
	 * 
	 * 初始化新增用户基本信息表页面
	 * @return
	 * 
	 */
	@RequestMapping(value = "goAdd")
	public ModelAndView goAdd(String dept_id) {
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.addObject("dept_id", dept_id);
		modelAndView.setViewName("system/user/addUser.jsp");;
		return modelAndView;
	}
	/**
	 * 保存用户基本信息表信息
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "saveUser")
	public void saveUser(HttpServletRequest request, HttpServletResponse response) {
	    Dto inDto = Dtos.newDto(request);
		Dto outDto = userService.save(inDto);
		IMSCxt.write(response, IMSJson.toJson(outDto));
	}
	/**
	 * 查看用户基本信息表详情页面
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "goShow")
	public ModelAndView goShow( String user_id) {
	   ModelAndView modelAndView= new ModelAndView();
	   UserPO userPO=userService.selectByKey(user_id);
	   modelAndView.addObject("userPO",userPO);
	   modelAndView.setViewName("showUser.jsp");
	   return modelAndView;
	}
	/**
	 * 初始化修改用户基本信息表页面
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "goModify")
	public ModelAndView goModify( String user_id) {
	   ModelAndView modelAndView= new ModelAndView();
	   UserPO userPO=userService.selectByKey(user_id);
	   modelAndView.addObject("userPO",userPO);
	   modelAndView.setViewName("system/user/modifyUser.jsp");
	   return modelAndView;
	}
	/**
	 * 修改用户基本信息表信息
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "updateUser")
	public void updateUser(HttpServletRequest request, HttpServletResponse response) {
		Dto inDto = Dtos.newDto(request);
		Dto outDto =userService.update(inDto);
		IMSCxt.write(response, IMSJson.toJson(outDto));
	}
	/**
	 * 删除用户基本信息表信息
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "deleteuser")
	public void deleteuser( String user_id,HttpServletResponse response) {
		Dto outDto =userService.delete(user_id);
		IMSCxt.write(response, IMSJson.toJson(outDto));
	}
	/**
	 * 批量删除用户基本信息表信息
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "batchDeleteUser")
	public void batchDeleteUser( HttpServletRequest request, HttpServletResponse response) {
		Dto inDto=Dtos.newDto(request);
		Dto outDto =userService.batchDelete(inDto);
		IMSCxt.write(response, IMSJson.toJson(outDto));
	}
	/**
	 * 跳转到密码重置修改界面
	 * @param rankid
	 * @return
	 */
	@RequestMapping(value = "goModifyPassword")
	public ModelAndView goModifyPassword(String user_id) {
		 ModelAndView modelAndView= new ModelAndView();
		 modelAndView.addObject("user_id",user_id);
		 modelAndView.setViewName("system/user/modifyPassword.jsp");
		 return modelAndView;
	}
	/**
	 * 
	 * 简要说明：用户密码重置
	 * 编写者：陈骑元
	 * 创建时间：2016年12月19日 下午7:56:02
	 * @param 说明
	 * @return 说明
	 */
	@RequestMapping(value="updatePassword")
	public void updatePassword(HttpServletRequest request, HttpServletResponse response){
		Dto inDto = Dtos.newDto(request);
		Dto outDto =userService.updatePassword(inDto);
		IMSCxt.write(response, IMSJson.toJson(outDto));
	}
	/**
	 * 跳转到移动用户界面
	 * @param rankid
	 * @return
	 */
	@RequestMapping(value = "goMoveUser")
	public ModelAndView goMoveUser(String user_ids) {
		 ModelAndView modelAndView= new ModelAndView();
		 modelAndView.addObject("user_ids",user_ids);
		 modelAndView.setViewName("system/user/moveUser.jsp");
		 return modelAndView;
	}
	/**
	 * 
	 * 简要说明：移动用户
	 * 编写者：陈骑元
	 * 创建时间：2016年12月22日 上午9:13:54
	 * @param 说明
	 * @return 说明
	 */
	@RequestMapping(value="moveUser")
	public void moveUser(HttpServletRequest request, HttpServletResponse response){
		Dto inDto = Dtos.newDto(request);
		Dto outDto =userService.moveUser(inDto);
		IMSCxt.write(response, IMSJson.toJson(outDto));
	}
	
}
