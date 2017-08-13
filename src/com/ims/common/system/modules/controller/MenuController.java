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
import com.ims.common.system.modules.po.MenuPO;
import com.ims.common.system.modules.po.TreeModel;
import com.ims.common.system.modules.service.MenuService;

/**
 * 
 * 类描述：<b>菜单配置[sys_menu控制类</b>
 * 创建人：陈骑元
 * 邮箱：240823329@qq.com
 * 创建时间：2016-10-07 16:06:01
 * 修改人：
 * 修改时间：
 * 修改备注： 
 * @version 1.0
 */
@Controller
@RequestMapping(value = "system/menu")
public class MenuController {
  
    @Autowired
	private  MenuService menuService;
	
	
	/**
	 * 页面初始化
	 *
	 * @return
	 */
	@RequestMapping(value = "init")
	public ModelAndView init() {
		return new ModelAndView("system/menu/menuList.jsp");
	}
	
	/**
	 * 
	 * 加载菜单树
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "loadMenuTree")
	public void loadMenuTree(HttpServletRequest request, HttpServletResponse response) {
		Dto pDto=Dtos.newDto(request);
		TreeModel treeModel=menuService.loadMenuTree(pDto);
		String outString ="["+IMSJson.toJson(treeModel)+"]";
		IMSCxt.write(response, outString);
	}
	/**
	 * 
	 * 加载一级菜单树
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "loadFirstMenuTree")
	public void loadFirstMenuTree(HttpServletRequest request, HttpServletResponse response) {
		Dto pDto=Dtos.newDto(request);
		pDto.put("parent_id", "0");
		TreeModel treeModel=menuService.loadFirstMenuTree(pDto);
		String outString ="["+IMSJson.toJson(treeModel)+"]";
		IMSCxt.write(response, outString);
	}
	/**
	 * 
	 * 分页查询菜单配置信息
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "listMenu")
	public void listMenu(HttpServletRequest request, HttpServletResponse response) {
		Dto pDto = Dtos.newDto(request);
		List<MenuPO> menuList =menuService.likePage(pDto);
		String outString = IMSJson.toGridJson(menuList, pDto.getPageTotal());
		IMSCxt.write(response, outString);
	}
	/**
	 * 
	 * 初始化新增菜单配置页面
	 * @return
	 * 
	 */
	@RequestMapping(value = "goAdd")
	public ModelAndView goAdd() {
		return new ModelAndView("system/menu/addMenu.jsp");
	}
	/**
	 * 保存菜单配置信息
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "saveMenu")
	public void saveMenu(HttpServletRequest request, HttpServletResponse response) {
	    Dto inDto = Dtos.newDto(request);
		Dto outDto = menuService.save(inDto);
		IMSCxt.write(response, IMSJson.toJson(outDto));
	}
	/**
	 * 查看菜单配置详情页面
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "goShow")
	public ModelAndView goShow( String menu_id) {
	   ModelAndView modelAndView= new ModelAndView();
	   MenuPO menuPO=menuService.selectByKey(menu_id);
	   modelAndView.addObject("menuPO",menuPO);
	   modelAndView.setViewName("showMenu.jsp");
	   return modelAndView;
	}
	/**
	 * 初始化修改菜单配置页面
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "goModify")
	public ModelAndView goModify( String menu_id) {
	   ModelAndView modelAndView= new ModelAndView();
	   MenuPO menuPO=menuService.selectByKey(menu_id);
	   modelAndView.addObject("menuPO",menuPO);
	   modelAndView.setViewName("system/menu/modifyMenu.jsp");
	   return modelAndView;
	}
	/**
	 * 修改菜单配置信息
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "updateMenu")
	public void updateMenu(HttpServletRequest request, HttpServletResponse response) {
		Dto inDto = Dtos.newDto(request);
		Dto outDto =menuService.update(inDto);
		IMSCxt.write(response, IMSJson.toJson(outDto));
	}
	/**
	 * 删除菜单配置信息
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "deleteMenu")
	public void deleteMenu( String menu_id,HttpServletResponse response) {
		Dto outDto =menuService.delete(menu_id);
		IMSCxt.write(response, IMSJson.toJson(outDto));
	}
	/**
	 * 批量删除菜单配置信息
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "batchDeleteMenu")
	public void batchDeleteMenu( String menu_ids,HttpServletResponse response) {
	    List<String> menu_idList=IMSFormater.separatStringToList(menu_ids);
		Dto outDto =menuService.batchDelete(menu_idList);
		IMSCxt.write(response, IMSJson.toJson(outDto));
	}

}
