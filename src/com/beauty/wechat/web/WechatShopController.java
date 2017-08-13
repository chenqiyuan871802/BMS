package com.beauty.wechat.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.beauty.common.constant.BeautyCons;
import com.beauty.common.po.CustomUserPO;
import com.beauty.common.po.NurseProjectPO;
import com.beauty.common.po.ShopPO;
import com.beauty.common.service.NurseProjectService;
import com.beauty.common.service.ShopCommonService;
import com.beauty.common.service.ShopService;
import com.ims.common.core.asset.IMSCons;
import com.ims.common.core.asset.IMSCxt;
import com.ims.common.core.asset.IMSJson;
import com.ims.common.core.matatype.Dto;
import com.ims.common.core.matatype.Dtos;

/**
 * 
 * 类名:com.beauty.wechat.web.WechatProjectController
 * 描述:微信店铺相关处理
 * 编写者:陈骑元
 * 创建时间:2017年5月19日 下午11:11:52
 * 修改说明:
 */
@Controller
@RequestMapping("wechat/shop")
public class WechatShopController {
	
	@Autowired
	private ShopService shopService;
	@Autowired
	private ShopCommonService shopCommonService;
	
	/**
	 * 跳到找店铺页面
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "goFindShop")
	public  ModelAndView goFindShop(String returnType,String project_id,HttpServletRequest request, HttpServletResponse response,HttpSession session) throws IOException {
		ModelAndView modelAndView= new ModelAndView();
		if("1".equals(returnType)){
			modelAndView.addObject("project_id", project_id);
			modelAndView.setViewName("beauty/wechat/project/selectShop.jsp");
		}else{
			modelAndView.setViewName("beauty/wechat/shop/findShop.jsp");
		}
	    
		return modelAndView;
	}
	
	/**
	 * 
	 * 简要说明：搜索商店
	 * 编写者：陈骑元
	 * 创建时间：2017年5月21日 上午10:57:42
	 * @param 说明
	 * @return 说明
	 */
	@RequestMapping(value = "searchShop")
	public void searchShop(HttpServletRequest request, HttpServletResponse response){
		Dto pDto =Dtos.newDto(request);
		pDto.put("show_status",BeautyCons.STATUS_YES);
		pDto.setOrder(" bis_shop.sort_no ASC ");
		List<Dto> shopList =shopCommonService.listShopPage(pDto);
		String outString = IMSJson.toGridJson(shopList, pDto.getPageTotal(),IMSCons.DATETIMEMIN);
		IMSCxt.write(response, outString);
	}
	
	
	
	/**
	 * 
	 * 简要说明：查看店铺详情
	 * 编写者：陈骑元
	 * 创建时间：2017年5月21日 下午1:57:30
	 * @param 说明
	 * @return 说明
	 */
	@RequestMapping("showShopDetail")
	public ModelAndView showShopDetail(String shop_id,HttpServletRequest request,
			HttpServletResponse response,HttpSession session){
		ModelAndView modelAndView=new ModelAndView();
		CustomUserPO customUserPO=IMSCxt.getCustomUserInfo(session);
		Dto countDto=Dtos.newDto();
		countDto.put("shop_id", shop_id);
		countDto.put("custom_user_id", customUserPO.getCustom_user_id());
		int count=shopCommonService.queryShopCollectCount(countDto);
		modelAndView.addObject("count", count);
		Dto pDto=Dtos.newDto();
		pDto.put("status", BeautyCons.SHOW_STATUS_ON);
		pDto.setOrder(" a.sort_no ASC ");
		List<NurseProjectPO> projectList=shopCommonService.listNurseProject(pDto);
		modelAndView.addObject("projectList", projectList);
		ShopPO shopPO=shopService.selectByKey(shop_id);
		modelAndView.addObject("shopPO", shopPO);
		modelAndView.setViewName("beauty/wechat/shop/showShopDetail.jsp");
		return modelAndView;
	}
	/**
	 * 
	 * 简要说明：查看我的收藏
	 * 编写者：陈骑元
	 * 创建时间：2017年6月22日 下午10:11:54
	 * @param 说明
	 * @return 说明
	 */
	@RequestMapping("listShopCollect")
    public ModelAndView listShopCollect(HttpServletRequest request, 
			HttpServletResponse response,HttpSession session){
    	CustomUserPO customUserPO=IMSCxt.getCustomUserInfo(session);
    	ModelAndView modelAndView=new ModelAndView();
    	List<Dto> shopList=shopCommonService.listShopCollect(customUserPO.getCustom_user_id());
    	if(shopList.size()>0){
    		modelAndView.addObject("show_data", "1");
    	}else{
    		modelAndView.addObject("show_data", "0");
    	}
    	modelAndView.addObject("shopList", shopList);
    	modelAndView.setViewName("beauty/wechat/my/myShopCollect.jsp");
		return modelAndView;
    	
    }
	/**
	 * 
	 * 简要说明：店铺收藏
	 * 编写者：陈骑元
	 * 创建时间：2017年6月22日 下午10:02:38
	 * @param 说明
	 * @return 说明
	 */
	@RequestMapping("saveShopCollect")
	public void saveShopCollect(String shop_id,HttpServletRequest request, 
			HttpServletResponse response,HttpSession session){
		CustomUserPO customUserPO=IMSCxt.getCustomUserInfo(session);
		Dto outDto=shopCommonService.saveShopCollect(shop_id, customUserPO.getCustom_user_id());
		String outString=IMSJson.toJson(outDto);
	    IMSCxt.write(response, outString);
	}

}
