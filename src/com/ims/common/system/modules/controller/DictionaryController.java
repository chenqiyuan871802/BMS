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
import com.ims.common.system.modules.po.DictionaryIndexPO;
import com.ims.common.system.modules.po.DictionaryPO;
import com.ims.common.system.modules.service.DictionaryIndexService;
import com.ims.common.system.modules.service.DictionaryService;

/**
 * 
 * 类描述：<b>数据字典[sys_dictionary控制类</b>
 * 创建人：陈骑元
 * 邮箱：240823329@qq.com
 * 创建时间：2016-10-02 22:31:28
 * 修改人：
 * 修改时间：
 * 修改备注： 
 * @version 1.0
 */
@Controller
@RequestMapping(value = "system/dictionary")
public class DictionaryController {
  
    @Autowired
	private  DictionaryService dictionaryService;  //字典明细表业务逻辑
    @Autowired
    private  DictionaryIndexService dictionaryIndexService;  //字典索引业务逻辑
	
	
	/**
	 * 页面初始化
	 *
	 * @return
	 */
	@RequestMapping(value = "init")
	public ModelAndView init() {
		return new ModelAndView("system/dictionary/dictionaryList.jsp");
	}
	
	/**
	 * 
	 * 查询数据字典索引表信息
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "listDictionaryIndex")
	public void listDictionaryIndex(HttpServletRequest request, HttpServletResponse response) {
		Dto pDto = Dtos.newDto(request); 
		pDto.setOrder("create_time DESC ");
		List<DictionaryIndexPO> dictionaryIndexList =dictionaryIndexService.like(pDto);
		String outString = IMSJson.toGridJson(dictionaryIndexList, dictionaryIndexList.size());
		IMSCxt.write(response, outString);
	}
	/**
	 * 
	 * 查询数据字典明细表信息
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "listDictionary")
	public void listDictionary(HttpServletRequest request, HttpServletResponse response) {
		Dto qDto = Dtos.newDto(request);
		qDto.setOrder(" sort_no ASC ");
		List<DictionaryPO> dictionaryList =dictionaryService.list(qDto);
		String outString = IMSJson.toGridJson(dictionaryList, qDto.getPageTotal());
		IMSCxt.write(response, outString);
	}
	/**
	 * 
	 * 初始化新增数据字典页面
	 * @return
	 * 
	 */
	@RequestMapping(value = "goAddDictionaryIndex")
	public ModelAndView goAddDictionaryIndex(String catalog_id) {
		ModelAndView modelAndView= new ModelAndView();
		modelAndView.addObject("catalog_id",catalog_id);
		modelAndView.setViewName("system/dictionary/addDictionaryIndex.jsp");
		return modelAndView;
	}
	/**
	 * 
	 * 初始化新增数据字典页面
	 * @return
	 * 
	 */
	@RequestMapping(value = "goAddDictionary")
	public ModelAndView goAddDictionary(String dic_index_id) {
		ModelAndView modelAndView= new ModelAndView();
		DictionaryIndexPO dictionaryIndexPO=dictionaryIndexService.selectByKey(dic_index_id);
		modelAndView.addObject("dictionaryIndexPO",dictionaryIndexPO);
		modelAndView.setViewName("system/dictionary/addDictionary.jsp");
		return modelAndView;
	}
	/**
	 * 保存数据字典信息
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "saveDictionaryIndex")
	public void saveDictionaryIndex(HttpServletRequest request, HttpServletResponse response) {
		Dto inDto = Dtos.newDto(request);
		Dto outDto = dictionaryIndexService.save(inDto);
		IMSCxt.write(response, IMSJson.toJson(outDto));
	}
	/**
	 * 保存数据字典明细表信息
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "saveDictionary")
	public void saveDictionary(HttpServletRequest request, HttpServletResponse response) {
	    Dto inDto = Dtos.newDto(request);
		Dto outDto = dictionaryService.save(inDto);
		IMSCxt.write(response, IMSJson.toJson(outDto));
	}
	/**
	 * 查看数据字典明细表详情页面
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "goShow")
	public ModelAndView goShow( String dic_id) {
	   ModelAndView modelAndView= new ModelAndView();
	   DictionaryPO dictionaryPO=dictionaryService.selectByKey(dic_id);
	   modelAndView.addObject("dictionaryPO",dictionaryPO);
	   modelAndView.setViewName("showDictionary.jsp");
	   return modelAndView;
	}
	/**
	 * 初始化修改数据字典页面
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "goModifyDictionaryIndex")
	public ModelAndView goModifyDictionaryIndex( String dic_index_id) {
		ModelAndView modelAndView= new ModelAndView();
		DictionaryIndexPO dictionaryIndexPO=dictionaryIndexService.selectByKey(dic_index_id);
		modelAndView.addObject("dictionaryIndexPO",dictionaryIndexPO);
		modelAndView.setViewName("system/dictionary/modifyDictionaryIndex.jsp");
		return modelAndView;
	}
	/**
	 * 初始化修改数据字典明细表页面
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "goModifyDictionary")
	public ModelAndView goModifyDictionary( String dic_id) {
	   ModelAndView modelAndView= new ModelAndView();
	   DictionaryPO dictionaryPO=dictionaryService.selectByKey(dic_id);
	   DictionaryIndexPO dictionaryIndexPO=dictionaryIndexService.selectByKey(dictionaryPO.getDic_index_id());
	   modelAndView.addObject("dictionaryIndexPO",dictionaryIndexPO);
	   modelAndView.addObject("dictionaryPO",dictionaryPO);
	   modelAndView.setViewName("system/dictionary/modifyDictionary.jsp");
	   return modelAndView;
	}
	/**
	 * 修改数据字典信息
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "updateDictionaryIndex")
	public void updateDictionaryIndex(HttpServletRequest request, HttpServletResponse response) {
		Dto inDto = Dtos.newDto(request);
		Dto outDto =dictionaryIndexService.update(inDto);
		IMSCxt.write(response, IMSJson.toJson(outDto));
	}
	/**
	 * 修改数据字典明细表信息
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "updateDictionary")
	public void updateDictionary(HttpServletRequest request, HttpServletResponse response) {
		Dto inDto = Dtos.newDto(request);
		Dto outDto =dictionaryService.update(inDto);
		IMSCxt.write(response, IMSJson.toJson(outDto));
	}
	/**
	 * 删除数据字典信息
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "deleteDictionaryIndex")
	public void deleteDictionaryIndex( String dic_index_id,HttpServletResponse response) {
		Dto outDto =dictionaryIndexService.delete(dic_index_id);
		IMSCxt.write(response, IMSJson.toJson(outDto));
	}
	/**
	 * 删除数据字典明细表信息
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "deleteDictionary")
	public void deleteDictionary( String dic_id,HttpServletResponse response) {
		Dto outDto =dictionaryService.delete(dic_id);
		IMSCxt.write(response, IMSJson.toJson(outDto));
	}
	/**
	 * 批量删除数据字典明细表信息
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "batchDeleteDictionary")
	public void batchDeleteDictionary( String dic_ids,HttpServletResponse response) {
	    List<String> dic_idList=IMSFormater.separatStringToList(dic_ids);
		Dto outDto =dictionaryService.batchDelete(dic_idList);
		IMSCxt.write(response, IMSJson.toJson(outDto));
	}
	
}
