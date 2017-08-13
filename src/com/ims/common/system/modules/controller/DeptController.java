package com.ims.common.system.modules.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ims.common.core.asset.IMSCons;
import com.ims.common.core.asset.IMSJson;
import com.ims.common.core.dao.plugin.DBType;
import com.ims.common.core.asset.IMSCxt;
import com.ims.common.core.matatype.Dto;
import com.ims.common.core.matatype.Dtos;
import com.ims.common.core.asset.IMSFormater;
import com.ims.common.system.modules.po.DeptPO;
import com.ims.common.system.modules.po.TreeModel;
import com.ims.common.system.modules.po.UserPO;
import com.ims.common.system.modules.service.DeptService;

/**
 * 
 * 类描述：<b>组织机构[sys_dept控制类</b>
 * 创建人：陈骑元
 * 邮箱：240823329@qq.com
 * 创建时间：2016-12-10 10:00:30
 * 修改人：
 * 修改时间：
 * 修改备注： 
 * @version 1.0
 */
@Controller
@RequestMapping(value = "system/dept")
public class DeptController {
  
    @Autowired
	private  DeptService deptService;
	
	
	/**
	 * 页面初始化
	 *
	 * @return
	 */
	@RequestMapping(value = "init")
	public ModelAndView init() {
		return new ModelAndView("system/dept/deptList.jsp");
	}
	
	/**
	 * 
	 * 加载组织机构树
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "loadDeptTree")
	public void loadDeptTree(HttpServletRequest request, HttpServletResponse response) {
		Dto pDto=Dtos.newDto(request);
		TreeModel treeModel=deptService.loadDeptTree(pDto);
		String outString ="["+IMSJson.toJson(treeModel)+"]";
		IMSCxt.write(response, outString);
	}
	
	/**
	 * 
	 * 分页查询组织机构信息
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "listDept")
	public void listDept(HttpServletRequest request, HttpServletResponse response) {
		Dto pDto = Dtos.newDto(request);
		pDto.put("is_del", IMSCons.IS.NO);
		//sqlserver 的长度函数不一样
		if(StringUtils.equalsIgnoreCase(IMSCxt.getDbType(), DBType.SQLSERVER)){
			pDto.setOrder("LEN(cascade_id) ASC,sort_no ASC ");
		}else{
			pDto.setOrder("LENGTH(cascade_id) ASC,sort_no ASC ");
		}
	
		List<DeptPO> deptList =deptService.likePage(pDto);
		String outString = IMSJson.toGridJson(deptList, pDto.getPageTotal());
		IMSCxt.write(response, outString);
	}
	/**
	 * 
	 * 初始化新增组织机构页面
	 * @return
	 * 
	 */
	@RequestMapping(value = "goAdd")
	public ModelAndView goAdd(String parent_id) {
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.addObject("parent_id", parent_id);
		modelAndView.setViewName("system/dept/addDept.jsp");
		return modelAndView;
	}
	/**
	 * 保存组织机构信息
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "saveDept")
	public void saveDept(HttpServletRequest request, HttpServletResponse response) {
	    Dto inDto = Dtos.newDto(request);
		Dto outDto = deptService.save(inDto);
		IMSCxt.write(response, IMSJson.toJson(outDto));
	}
	/**
	 * 查看组织机构详情页面
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "goShow")
	public ModelAndView goShow( String dept_id) {
	   ModelAndView modelAndView= new ModelAndView();
	   DeptPO deptPO=deptService.selectByKey(dept_id);
	   modelAndView.addObject("deptPO",deptPO);
	   modelAndView.setViewName("showDept.jsp");
	   return modelAndView;
	}
	/**
	 * 初始化修改组织机构页面
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "goModify")
	public ModelAndView goModify( String dept_id) {
	   ModelAndView modelAndView= new ModelAndView();
	   DeptPO deptPO=deptService.selectByKey(dept_id);
	   modelAndView.addObject("deptPO",deptPO);
	   modelAndView.setViewName("system/dept/modifyDept.jsp");
	   return modelAndView;
	}
	/**
	 * 修改组织机构信息
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "updateDept")
	public void updateDept(HttpServletRequest request, HttpServletResponse response) {
		Dto inDto = Dtos.newDto(request);
		Dto outDto =deptService.update(inDto);
		IMSCxt.write(response, IMSJson.toJson(outDto));
	}
	/**
	 * 初始化移动机构界面
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "goMove")
	public ModelAndView goMove( String dept_id) {
	   ModelAndView modelAndView= new ModelAndView();
	   DeptPO deptPO=deptService.selectByKey(dept_id);
	   modelAndView.addObject("deptPO",deptPO);
	   modelAndView.setViewName("system/dept/moveDept.jsp");
	   return modelAndView;
	}
	/**
	 * 删除组织机构信息
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "moveDept")
	public void moveDept(HttpServletRequest request, HttpServletResponse response) {
		Dto inDto = Dtos.newDto(request);
		Dto outDto =deptService.moveDept(inDto);
		IMSCxt.write(response, IMSJson.toJson(outDto));
	}
	/**
	 * 删除组织机构信息
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "deleteDept")
	public void deleteDept( String dept_id,HttpServletRequest request,HttpServletResponse response) {
		//获取当前登陆用户
		UserPO loginUserPO=IMSCxt.getUserInfo(request.getSession());
		Dto outDto =deptService.delete(dept_id,loginUserPO.getUser_id());
		IMSCxt.write(response, IMSJson.toJson(outDto));
	}
	/**
	 * 批量删除组织机构信息
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "batchDeleteDept")
	public void batchDeleteDept( String dept_ids,HttpServletResponse response) {
	    List<String> dept_idList=IMSFormater.separatStringToList(dept_ids);
		Dto outDto =deptService.batchDelete(dept_idList);
		IMSCxt.write(response, IMSJson.toJson(outDto));
	}
	
}
