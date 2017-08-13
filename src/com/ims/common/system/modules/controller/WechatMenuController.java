package com.ims.common.system.modules.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.ims.common.core.asset.IMSJson;
import com.ims.common.core.asset.IMSUtils;
import com.beauty.wechat.util.WechatCxt;
import com.ims.common.core.asset.IMSCons;
import com.ims.common.core.asset.IMSCxt;
import com.ims.common.core.matatype.Dto;
import com.ims.common.core.matatype.Dtos;
import com.ims.common.core.asset.IMSFormater;
import com.ims.common.system.modules.po.WechatMenuPO;
import com.ims.common.system.modules.service.WechatMenuService;

import net.sf.json.JSONObject;

/**
 * 
 * 类描述：<b>微信菜单信息[wechat_menu]控制类</b>
 * 创建人：陈骑元
 * 邮箱：240823329@qq.com
 * 创建时间：2017-05-02 12:29:51
 * 修改人：
 * 修改时间：
 * 修改备注： 
 * @version 1.0
 */
@Controller
@RequestMapping(value = "system/wechatMenu")
public class WechatMenuController {
  
    @Autowired
	private  WechatMenuService wechatMenuService;
	
	
   
	/**
	 * 页面初始化
	 *
	 * @return
	 */
	@RequestMapping(value = "init")
	public ModelAndView init() {
		return new ModelAndView("system/wechatMenu/wechatMenuList.jsp");
	}
	
	/**
	 * 
	 * 分页查询微信菜单信息信息
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "listWechatMenu")
	public void listWechatMenu(HttpServletRequest request, HttpServletResponse response) {
		Dto pDto=Dtos.newDto();
		List<Dto> menuList=wechatMenuService.listWechatMenu(pDto);
		String outString = IMSJson.toGridJson(menuList,menuList.size());
		IMSCxt.write(response, outString);
	}
	/**
	 * 
	 * 初始化新增微信菜单信息页面
	 * @return
	 * 
	 */
	@RequestMapping(value = "goAddFirstMenu")
	public ModelAndView goAddFirstMenu() {
		return new ModelAndView("system/wechatMenu/addFirstMenu.jsp");
	}
	/**
	 * 
	 * 初始化新增微信菜单信息页面
	 * @return
	 * 
	 */
	@RequestMapping(value = "goAddSecondMenu")
	public ModelAndView goAddSecondMenu(String menu_id) {
		 ModelAndView modelAndView= new ModelAndView();
		 WechatMenuPO wechatMenuPO=wechatMenuService.selectByKey(menu_id);
		 modelAndView.addObject("wechatMenuPO",wechatMenuPO);
		 modelAndView.setViewName("system/wechatMenu/addSecondMenu.jsp");
		return  modelAndView;
	}
	/**
	 * 保存微信菜单信息信息
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "saveWechatMenu")
	public void saveWechatMenu(HttpServletRequest request, HttpServletResponse response) {
	    Dto inDto = Dtos.newDto(request);
		Dto outDto = wechatMenuService.save(inDto);
		IMSCxt.write(response, IMSJson.toJson(outDto));
	}
	/**
	 * 查看微信菜单信息详情页面
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "goShow")
	public ModelAndView goShow( String menu_id) {
	   ModelAndView modelAndView= new ModelAndView();
	   WechatMenuPO wechatMenuPO=wechatMenuService.selectByKey(menu_id);
	   modelAndView.addObject("wechatMenuPO",wechatMenuPO);
	   modelAndView.setViewName("showWechatMenu.jsp");
	   return modelAndView;
	}
	/**
	 * 初始化修改微信菜单信息页面
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "goModifyFirstMenu")
	public ModelAndView goModifyFirstMenu( String menu_id) {
	   ModelAndView modelAndView= new ModelAndView();
	   WechatMenuPO wechatMenuPO=wechatMenuService.selectByKey(menu_id);
	   modelAndView.addObject("wechatMenuPO",wechatMenuPO);
	   modelAndView.setViewName("system/wechatMenu/modifyFirstMenu.jsp");
	   return modelAndView;
	}
	/**
	 * 初始化修改微信菜单信息页面
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "goModifySecondMenu")
	public ModelAndView goModifySecondMenu( String menu_id) {
		ModelAndView modelAndView= new ModelAndView();
		WechatMenuPO wechatMenuPO=wechatMenuService.selectByKey(menu_id);
		WechatMenuPO parentMenu=wechatMenuService.selectByKey(wechatMenuPO.getParent_id());
		modelAndView.addObject("parentName", parentMenu.getMenu_name());
		modelAndView.addObject("wechatMenuPO",wechatMenuPO);
		modelAndView.setViewName("system/wechatMenu/modifySecondMenu.jsp");
		return modelAndView;
	}
	/**
	 * 修改微信菜单信息信息
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "updateWechatMenu")
	public void updateWechatMenu(HttpServletRequest request, HttpServletResponse response) {
		Dto inDto = Dtos.newDto(request);
		Dto outDto =wechatMenuService.update(inDto);
		IMSCxt.write(response, IMSJson.toJson(outDto));
	}
	/**
	 * 删除微信菜单信息信息
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "deleteWechatMenu")
	public void deleteWechatMenu( String menu_id,HttpServletResponse response) {
		Dto outDto =wechatMenuService.delete(menu_id);
		IMSCxt.write(response, IMSJson.toJson(outDto));
	}
	/**
	 * 批量删除微信菜单信息信息
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "batchDeleteWechatMenu")
	public void batchDeleteWechatMenu( String menu_ids,HttpServletResponse response) {
	    List<String> menu_idList=IMSFormater.separatStringToList(menu_ids);
		Dto outDto =wechatMenuService.batchDelete(menu_idList);
		IMSCxt.write(response, IMSJson.toJson(outDto));
	}
	/**
	 * 
	 * 简要说明：同步微信菜单
	 * 编写者：陈骑元
	 * 创建时间：2017年5月4日 上午1:09:52
	 * @param 说明
	 * @return 说明
	 */
	@RequestMapping(value = "syncWechatMenu")
	public void syncWechatMenu(HttpServletResponse response){
		Dto outDto=Dtos.newDto();
		//创建微信菜单
		Dto buttonDto=wechatMenuService.groupWechatMenu();
		if(IMSUtils.isEmpty(buttonDto)){
			  outDto.setAppMsg("没有菜单要同步到微信");
			  outDto.setAppCode(IMSCons.WARN);
			
		}else{
			String menuJson=JSONObject.fromObject(buttonDto).toString();
			System.out.println(menuJson);
			boolean result =WechatCxt.createMenu(menuJson);
			if(result){
				 outDto.setAppMsg("同步微信菜单成功");
				 outDto.setAppCode(IMSCons.SUCCESS);
			}else{
				 outDto.setAppMsg("同步微信菜单失败");
				 outDto.setAppCode(IMSCons.ERROR);
			}
		}
		IMSCxt.write(response, IMSJson.toJson(outDto));
		
	}
}
