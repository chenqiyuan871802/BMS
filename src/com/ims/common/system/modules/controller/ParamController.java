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
import com.ims.common.core.asset.IMSFormater;
import com.ims.common.system.modules.po.ParamPO;
import com.ims.common.system.modules.service.ParamService;

/**
 * 
 * 类描述：<b>键值参数[sys_param控制类</b>
 * 创建人：陈骑元
 * 邮箱：240823329@qq.com
 * 创建时间：2016-09-25 10:21:40
 * 修改人：
 * 修改时间：
 * 修改备注： 
 * @version 1.0
 */
@Controller
@RequestMapping(value = "system/param")
public class ParamController {
  
    @Autowired
	private  ParamService paramService;
	
	
	/**
	 * 页面初始化
	 *
	 * @return
	 */
	@RequestMapping(value = "init")
	public ModelAndView init() {
		return new ModelAndView("system/param/paramList.jsp");
	}
	
	/**
	 * 
	 * 分页查询键值参数信息
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "listParam")
	public void listParam(HttpServletRequest request, HttpServletResponse response) {
		Dto pDto = Dtos.newDto(request);
		pDto.setOrder(" create_time DESC ");
		List<ParamPO> paramList =paramService.likePage(pDto);
		String outString = IMSJson.toGridJson(paramList, pDto.getPageTotal());
		IMSCxt.write(response, outString);
	}
	/**
	 * 
	 * 初始化新增键值参数页面
	 * @return
	 * 
	 */
	@RequestMapping(value = "goAdd")
	public ModelAndView goAdd(String catalog_id) {
		 ModelAndView modelAndView= new ModelAndView();
		 modelAndView.addObject("catalog_id",catalog_id);
		 modelAndView.setViewName("system/param/addParam.jsp");
		 return modelAndView;
	}
	/**
	 * 保存键值参数信息
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "saveParam")
	public void saveParam(HttpServletRequest request, HttpServletResponse response) {
	    Dto inDto = Dtos.newDto(request);
		Dto outDto = paramService.save(inDto);
		IMSCxt.write(response, IMSJson.toJson(outDto));
	}
	/**
	 * 初始化修改键值参数页面
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "goModify")
	public ModelAndView goModify( String param_id) {
	   ModelAndView modelAndView= new ModelAndView();
	   ParamPO paramPO=paramService.selectByKey(param_id);
	   modelAndView.addObject("paramPO",paramPO);
	   modelAndView.setViewName("system/param/modifyParam.jsp");
	   return modelAndView;
	}
	/**
	 * 修改键值参数信息
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "updateParam")
	public void updateParam(HttpServletRequest request, HttpServletResponse response) {
		Dto inDto = Dtos.newDto(request);
		Dto outDto =paramService.update(inDto);
		IMSCxt.write(response, IMSJson.toJson(outDto));
	}
	/**
	 * 删除键值参数信息
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "deleteParam")
	public void deleteParam( String param_id,HttpServletResponse response) {
		Dto outDto =paramService.delete(param_id);
		IMSCxt.write(response, IMSJson.toJson(outDto));
	}
	/**
	 * 批量删除键值参数信息
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "batchDeleteParam")
	public void batchDeleteParam( String param_ids,HttpServletResponse response) {
	    List<String> param_idList=IMSFormater.separatStringToList(param_ids);
		Dto outDto =paramService.batchDelete(param_idList);
		IMSCxt.write(response, IMSJson.toJson(outDto));
	}
	
}
