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
import com.ims.common.system.modules.po.TreeModel;
import com.ims.common.core.asset.IMSFormater;
import com.beauty.common.po.ShopPostPO;
import com.beauty.common.service.ShopCommonService;
import com.beauty.common.service.ShopPostService;

/**
 * 
 * 类描述：<b>店铺职位信息表[bis_shop_post]控制类</b>
 * 创建人：陈骑元
 * 邮箱：240823329@qq.com
 * 创建时间：2017-04-17 12:10:12
 * 修改人：
 * 修改时间：
 * 修改备注： 
 * @version 1.0
 */
@Controller
@RequestMapping(value = "system/shopPost")
public class ShopPostController {
  
    @Autowired
	private  ShopPostService shopPostService;
    @Autowired
    private  ShopCommonService shopCommonService;
	
	
	/**
	 * 页面初始化
	 *
	 * @return
	 */
	@RequestMapping(value = "init")
	public ModelAndView init() {
		return new ModelAndView("beauty/system/shopPost/shopPostList.jsp");
	}
	
	/**
	 * 
	 * 分页查询店铺职位信息表信息
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "listShopPost")
	public void listShopPost(HttpServletRequest request, HttpServletResponse response) {
		Dto pDto = Dtos.newDto(request);
		pDto.setOrder("create_time ASC");
		List<ShopPostPO> shopPostList =shopPostService.likePage(pDto);
		String outString = IMSJson.toGridJson(shopPostList, pDto.getPageTotal());
		IMSCxt.write(response, outString);
	}
	/**
	 * 
	 * 简要说明：查询全部职位信息
	 * 编写者：陈骑元
	 * 创建时间：2017年4月19日 下午10:00:40
	 * @param 说明
	 * @return 说明
	 */
	@RequestMapping(value = "queryShopPost")
	public void queryShopPost(HttpServletRequest request, HttpServletResponse response) {
		Dto pDto = Dtos.newDto(request);
		pDto.setOrder("create_time ASC");
		List<ShopPostPO> shopPostList =shopPostService.like(pDto);
		String outString = IMSJson.toJson(shopPostList);
		IMSCxt.write(response, outString);
	}
	/**
	 * 
	 * 加载授权菜单树
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "loadGrantMenuTree")
	public void loadGrantMenuTree(String post_id,HttpServletRequest request, HttpServletResponse response) {
		TreeModel treeModel=shopCommonService.loadGrantMenuTree(post_id);
		String outString ="["+IMSJson.toJson(treeModel)+"]";
		IMSCxt.write(response, outString);
	}
	/**
	 * 
	 * 初始化新增店铺职位信息表页面
	 * @return
	 * 
	 */
	@RequestMapping(value = "goAdd")
	public ModelAndView goAdd() {
		return new ModelAndView("beauty/system/shopPost/addShopPost.jsp");
	}
	/**
	 * 保存店铺职位信息表信息
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "saveShopPost")
	public void saveShopPost(HttpServletRequest request, HttpServletResponse response) {
	    Dto inDto = Dtos.newDto(request);
		Dto outDto = shopPostService.save(inDto);
		IMSCxt.write(response, IMSJson.toJson(outDto));
	}
	/**
	 * 查看店铺职位信息表详情页面
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "goShow")
	public ModelAndView goShow( String post_id) {
	   ModelAndView modelAndView= new ModelAndView();
	   ShopPostPO shopPostPO=shopPostService.selectByKey(post_id);
	   modelAndView.addObject("shopPostPO",shopPostPO);
	   modelAndView.setViewName("showShopPost.jsp");
	   return modelAndView;
	}
	/**
	 * 初始化修改店铺职位信息表页面
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "goModify")
	public ModelAndView goModify( String post_id) {
	   ModelAndView modelAndView= new ModelAndView();
	   ShopPostPO shopPostPO=shopPostService.selectByKey(post_id);
	   modelAndView.addObject("shopPostPO",shopPostPO);
	   modelAndView.setViewName("beauty/system/shopPost/modifyShopPost.jsp");
	   return modelAndView;
	}
	/**
	 * 修改店铺职位信息表信息
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "updateShopPost")
	public void updateShopPost(HttpServletRequest request, HttpServletResponse response) {
		Dto inDto = Dtos.newDto(request);
		Dto outDto =shopPostService.update(inDto);
		IMSCxt.write(response, IMSJson.toJson(outDto));
	}
	/**
	 * 删除店铺职位信息表信息
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "deleteShopPost")
	public void deleteShopPost( String post_id,HttpServletResponse response) {
		Dto outDto =shopPostService.delete(post_id);
		IMSCxt.write(response, IMSJson.toJson(outDto));
	}
	/**
	 * 批量删除店铺职位信息表信息
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "batchDeleteShopPost")
	public void batchDeleteShopPost( String post_ids,HttpServletResponse response) {
	    List<String> post_idList=IMSFormater.separatStringToList(post_ids);
		Dto outDto =shopPostService.batchDelete(post_idList);
		IMSCxt.write(response, IMSJson.toJson(outDto));
	}
	
}
