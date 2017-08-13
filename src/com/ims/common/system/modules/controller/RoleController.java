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
import com.ims.common.core.asset.IMSFormater;
import com.ims.common.system.modules.po.RolePO;
import com.ims.common.system.modules.service.RoleService;

/**
 * 
 * 类描述：<b>角色表[sys_role控制类</b>
 * 创建人：陈骑元
 * 邮箱：240823329@qq.com
 * 创建时间：2016-12-31 09:17:02
 * 修改人：
 * 修改时间：
 * 修改备注： 
 * @version 1.0
 */
@Controller
@RequestMapping(value = "system/role")
public class RoleController {
  
    @Autowired
	private  RoleService roleService;
	
	
	/**
	 * 页面初始化
	 *
	 * @return
	 */
	@RequestMapping(value = "init")
	public ModelAndView init() {
		return new ModelAndView("system/role/roleList.jsp");
	}
	
	/**
	 * 
	 * 分页查询角色表信息
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "listRole")
	public void listRole(HttpServletRequest request, HttpServletResponse response) {
		Dto pDto = Dtos.newDto(request);
		//只查询当前用户的角色
		pDto.put("create_user_id", pDto.getString(IMSCons.LOGIN_USER_ID));
		List<RolePO> roleList =roleService.likePage(pDto);
		String outString = IMSJson.toGridJson(roleList, pDto.getPageTotal());
		IMSCxt.write(response, outString);
	}
	/**
	 * 
	 * 初始化新增角色表页面
	 * @return
	 * 
	 */
	@RequestMapping(value = "goAdd")
	public ModelAndView goAdd() {
		return new ModelAndView("system/role/addRole.jsp");
	}
	/**
	 * 保存角色表信息
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "saveRole")
	public void saveRole(HttpServletRequest request, HttpServletResponse response) {
	    Dto inDto = Dtos.newDto(request);
		Dto outDto = roleService.save(inDto);
		IMSCxt.write(response, IMSJson.toJson(outDto));
	}
	/**
	 * 查看角色表详情页面
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "goShow")
	public ModelAndView goShow( String role_id) {
	   ModelAndView modelAndView= new ModelAndView();
	   RolePO rolePO=roleService.selectByKey(role_id);
	   modelAndView.addObject("rolePO",rolePO);
	   modelAndView.setViewName("showRole.jsp");
	   return modelAndView;
	}
	/**
	 * 初始化修改角色表页面
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "goModify")
	public ModelAndView goModify( String role_id) {
	   ModelAndView modelAndView= new ModelAndView();
	   RolePO rolePO=roleService.selectByKey(role_id);
	   modelAndView.addObject("rolePO",rolePO);
	   modelAndView.setViewName("system/role/modifyRole.jsp");
	   return modelAndView;
	}
	/**
	 * 修改角色表信息
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "updateRole")
	public void updateRole(HttpServletRequest request, HttpServletResponse response) {
		Dto inDto = Dtos.newDto(request);
		Dto outDto =roleService.update(inDto);
		IMSCxt.write(response, IMSJson.toJson(outDto));
	}
	/**
	 * 删除角色表信息
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "deleteRole")
	public void deleteRole( String role_id,HttpServletResponse response) {
		Dto outDto =roleService.delete(role_id);
		IMSCxt.write(response, IMSJson.toJson(outDto));
	}
	/**
	 * 批量删除角色表信息
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "batchDeleteRole")
	public void batchDeleteRole( String role_ids,HttpServletResponse response) {
	    List<String> role_idList=IMSFormater.separatStringToList(role_ids);
		Dto outDto =roleService.batchDelete(role_idList);
		IMSCxt.write(response, IMSJson.toJson(outDto));
	}
	
}
