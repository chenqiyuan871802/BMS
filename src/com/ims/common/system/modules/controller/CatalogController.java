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
import com.ims.common.system.modules.po.CatalogPO;
import com.ims.common.system.modules.po.TreeModel;
import com.ims.common.system.modules.service.CatalogService;

/**
 * 
 * 类描述：<b>分类科目[sys_catalog控制类</b>
 * 创建人：陈骑元
 * 邮箱：240823329@qq.com
 * 创建时间：2016-09-25 09:25:54
 * 修改人：
 * 修改时间：
 * 修改备注： 
 * @version 1.0
 */
@Controller
@RequestMapping(value = "system/catalog")
public class CatalogController {
  
    @Autowired
	private  CatalogService catalogService;
	
	
	/**
	 * 页面初始化
	 *
	 * @return
	 */
	@RequestMapping(value = "init")
	public ModelAndView init() {
		return new ModelAndView("system/catalog/catalogList.jsp");
	}
	
	/**
	 * 
	 * 分页查询分类科目信息
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "listCatalog")
	public void listCatalog(HttpServletRequest request, HttpServletResponse response) {
		Dto pDto=Dtos.newDto();
		List<Dto> catalogList=catalogService.listCatalog(pDto);
		String outString = IMSJson.toGridJson(catalogList,catalogList.size());
		IMSCxt.write(response, outString);
	}
	/**
	 * 
	 * 初始化新增科目初始页面
	 * @return
	 * 
	 */
	@RequestMapping(value = "goAddSubject")
	public ModelAndView goAddSubject() {
		return new ModelAndView("system/catalog/addSubject.jsp");
	}
	/**
	 * 
	 * 初始化新增分类页面
	 * @return
	 * 
	 */
	@RequestMapping(value = "goAddCatalog")
	public ModelAndView goAddCatalog(String catalog_id) {
		 ModelAndView modelAndView= new ModelAndView();
		 CatalogPO catalogPO=catalogService.selectByKey(catalog_id);
		 modelAndView.addObject("catalogPO",catalogPO);
		 modelAndView.setViewName("system/catalog/addCatalog.jsp");
		 return modelAndView;
	}
	
	/**
	 * 保存分类科目信息
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "saveCatalog")
	public void saveCatalog(HttpServletRequest request, HttpServletResponse response) {
	    Dto inDto = Dtos.newDto(request);
		Dto outDto = catalogService.save(inDto);
		IMSCxt.write(response, IMSJson.toJson(outDto));
	}
	/**
	 * 初始化修改分类科目页面
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "goModify")
	public ModelAndView goModify( String catalog_id) {
	   ModelAndView modelAndView= new ModelAndView();
	   CatalogPO catalogPO=catalogService.selectByKey(catalog_id);
	   modelAndView.addObject("catalogPO",catalogPO);
	   modelAndView.setViewName("system/catalog/modifyCatalog.jsp");
	   return modelAndView;
	}
	/**
	 * 修改分类科目信息
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "updateCatalog")
	public void updateCatalog(HttpServletRequest request, HttpServletResponse response) {
		Dto inDto = Dtos.newDto(request);
		Dto outDto =catalogService.update(inDto);
		IMSCxt.write(response, IMSJson.toJson(outDto));
	}
	/**
	 * 删除分类科目信息
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "deleteCatalog")
	public void deleteCatalog( String catalog_id,HttpServletResponse response) {
		Dto outDto =catalogService.delete(catalog_id);
		IMSCxt.write(response, IMSJson.toJson(outDto));
	}
	/**
	 * 批量删除分类科目信息
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "batchDeleteCatalog")
	public void batchDeleteCatalog( String catalog_ids,HttpServletResponse response) {
	    List<String> catalog_idList=IMSFormater.separatStringToList(catalog_ids);
		Dto outDto =catalogService.batchDelete(catalog_idList);
		IMSCxt.write(response, IMSJson.toJson(outDto));
	}
	/**
	 * 加载分类科目树
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "loadCatalogTree")
	public void loadCatalogTree(HttpServletRequest request, HttpServletResponse response){
		Dto pDto=Dtos.newDto(request);
		List<TreeModel> treeModelList=catalogService.loadCatalogTree(pDto);
		String outString =IMSJson.toJson(treeModelList);
		IMSCxt.write(response, outString);
	}
}
