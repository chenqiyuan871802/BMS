package com.beauty.system.web;

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
import com.ims.common.core.asset.IMSFormater;
import com.beauty.common.po.NurseTypePO;
import com.beauty.common.service.NurseTypeService;

/**
 * 
 * 类描述：<b>护理类型信息表[bis_nurse_type]控制类</b>
 * 创建人：陈骑元
 * 邮箱：240823329@qq.com
 * 创建时间：2017-04-17 12:04:13
 * 修改人：
 * 修改时间：
 * 修改备注： 
 * @version 1.0
 */
@Controller
@RequestMapping(value = "system/nurseType")
public class NurseTypeController {
  
    @Autowired
	private  NurseTypeService nurseTypeService;
	
	
	/**
	 * 页面初始化
	 *
	 * @return
	 */
	@RequestMapping(value = "init")
	public ModelAndView init() {
		return new ModelAndView("beauty/system/nurseType/nurseTypeList.jsp");
	}
	
	/**
	 * 
	 * 分页查询护理类型信息表信息
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "listNurseType")
	public void listNurseType(HttpServletRequest request, HttpServletResponse response) {
		Dto pDto = Dtos.newDto(request);
		pDto.setOrder(" sort_no ASC ");
		List<NurseTypePO> nurseTypeList =nurseTypeService.likePage(pDto);
		String outString = IMSJson.toGridJson(nurseTypeList, pDto.getPageTotal());
		IMSCxt.write(response, outString);
	}
	/**
	 * 
	 * 初始化新增护理类型信息表页面
	 * @return
	 * 
	 */
	@RequestMapping(value = "goAdd")
	public ModelAndView goAdd() {
		return new ModelAndView("beauty/system/nurseType/addNurseType.jsp");
	}
	/**
	 * 保存护理类型信息表信息
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "saveNurseType")
	public void saveNurseType(HttpServletRequest request, HttpServletResponse response) {
	    Dto inDto = Dtos.newDto(request);
		Dto outDto = nurseTypeService.save(inDto);
		IMSCxt.write(response, IMSJson.toJson(outDto));
	}
	/**
	 * 查看护理类型信息表详情页面
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "goShow")
	public ModelAndView goShow( String type_id) {
	   ModelAndView modelAndView= new ModelAndView();
	   NurseTypePO nurseTypePO=nurseTypeService.selectByKey(type_id);
	   modelAndView.addObject("nurseTypePO",nurseTypePO);
	   modelAndView.setViewName("showNurseType.jsp");
	   return modelAndView;
	}
	/**
	 * 初始化修改护理类型信息表页面
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "goModify")
	public ModelAndView goModify( String type_id) {
	   ModelAndView modelAndView= new ModelAndView();
	   NurseTypePO nurseTypePO=nurseTypeService.selectByKey(type_id);
	   modelAndView.addObject("nurseTypePO",nurseTypePO);
	   modelAndView.setViewName("beauty/system/nurseType/modifyNurseType.jsp");
	   return modelAndView;
	}
	/**
	 * 修改护理类型信息表信息
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "updateNurseType")
	public void updateNurseType(HttpServletRequest request, HttpServletResponse response) {
		Dto inDto = Dtos.newDto(request);
		Dto outDto =nurseTypeService.update(inDto);
		IMSCxt.write(response, IMSJson.toJson(outDto));
	}
	/**
	 * 删除护理类型信息表信息
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "deleteNurseType")
	public void deleteNurseType( String type_id,HttpServletResponse response) {
		Dto outDto =nurseTypeService.delete(type_id);
		IMSCxt.write(response, IMSJson.toJson(outDto));
	}
	/**
	 * 批量删除护理类型信息表信息
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "batchDeleteNurseType")
	public void batchDeleteNurseType( String type_ids,HttpServletResponse response) {
	    List<String> type_idList=IMSFormater.separatStringToList(type_ids);
		Dto outDto =nurseTypeService.batchDelete(type_idList);
		IMSCxt.write(response, IMSJson.toJson(outDto));
	}
	/**
	 * 
	 * 查询全部分类信息
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "queryNurseType")
	public void queryNurseType(HttpServletRequest request, HttpServletResponse response) {
		Dto pDto = Dtos.newDto(request);
		pDto.setOrder(" sort_no ASC ");
		List<NurseTypePO> nurseTypeList =nurseTypeService.list(pDto);
		String outString = IMSJson.toJson(nurseTypeList);
		IMSCxt.write(response, outString);
	}
}
