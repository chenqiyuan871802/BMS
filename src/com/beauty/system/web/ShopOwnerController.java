package com.beauty.system.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.beauty.common.constant.BeautyCons;
import com.beauty.common.po.ShopUserPO;
import com.beauty.common.service.ShopCommonService;
import com.beauty.common.service.ShopUserService;
import com.ims.common.core.asset.IMSCodec;
import com.ims.common.core.asset.IMSCons;
import com.ims.common.core.asset.IMSCxt;
import com.ims.common.core.asset.IMSFormater;
import com.ims.common.core.asset.IMSJson;
import com.ims.common.core.matatype.Dto;
import com.ims.common.core.matatype.Dtos;
import com.ims.common.system.modules.po.UserPO;
/**
 * 
 * 类名:com.beauty.system.web.ShopOwnerController
 * 描述:商铺店主业务逻辑控制
 * 编写者:陈骑元
 * 创建时间:2017年4月17日 下午11:40:21
 * 修改说明:
 */
@Controller
@RequestMapping(value = "system/shopOwner")
public class ShopOwnerController {
	    @Autowired
		private  ShopUserService shopUserService;
	    
	    @Autowired
		private  ShopCommonService shopCommonService;
		
		
		/**
		 * 页面初始化
		 *
		 * @return
		 */
		@RequestMapping(value = "init")
		public ModelAndView init() {
			return new ModelAndView("beauty/system/shopOwner/shopOwnerList.jsp");
		}
		
		/**
		 * 
		 * 分页查询店主列表信息
		 * @param request
		 * @param response
		 */
		@RequestMapping(value = "listShopOwner")
		public void listShopOwner(HttpServletRequest request, HttpServletResponse response) {
			Dto pDto = Dtos.newDto(request);
			pDto.put("user_type", BeautyCons.USER_TYPE_OWNER);//查询有效店主
			pDto.put("is_del", IMSCons.IS.NO); //查询有效信息
			pDto.setOrder("create_time");
			List<ShopUserPO> shopUserPOList =shopCommonService.listShopOwnerPage(pDto);
			String outString = IMSJson.toGridJson(shopUserPOList, pDto.getPageTotal());
			IMSCxt.write(response, outString);
		}
		/**
		 * 
		 * 简要说明：查询店铺店主信息
		 * 编写者：陈骑元
		 * 创建时间：2017年4月19日 上午12:50:21
		 * @param 说明
		 * @return 说明
		 */
		@RequestMapping(value = "queryShopOwner")
		public void queryShopOwner(HttpServletRequest request, HttpServletResponse response){
			Dto pDto = Dtos.newDto(request);
			pDto.put("user_type", BeautyCons.USER_TYPE_OWNER);//查询有效店主
			pDto.put("is_del", IMSCons.IS.NO); //查询有效信息
			List<ShopUserPO> shopUserPOList =shopUserService.list(pDto);
			String outString = IMSJson.toGridJson(shopUserPOList);
			IMSCxt.write(response, outString);
		}
		/**
		 * 
		 * 初始化新增店铺员工信息表页面
		 * @return
		 * 
		 */
		@RequestMapping(value = "goAdd")
		public ModelAndView goAdd() {
			return new ModelAndView("beauty/system/shopOwner/addShopOwner.jsp");
		}
		/**
		 * 保存店铺员工信息表信息
		 * @param response
		 * @param request
		 * @return
		 */
		@RequestMapping(value = "saveShopOwner")
		public void saveShopOwner(HttpServletRequest request, HttpServletResponse response) {
		    Dto inDto = Dtos.newDto(request);
			Dto outDto = shopUserService.saveShopOwner(inDto);
			IMSCxt.write(response, IMSJson.toJson(outDto));
		}
		/**
		 * 查看店铺员工信息表详情页面
		 * @param response
		 * @param request
		 * @return
		 */
		@RequestMapping(value = "goShow")
		public ModelAndView goShow( String shop_user_id) {
		   ModelAndView modelAndView= new ModelAndView();
		   ShopUserPO shopUserPO=shopUserService.selectByKey(shop_user_id);
		   modelAndView.addObject("shopUserPO",shopUserPO);
		   modelAndView.setViewName("showShopUser.jsp");
		   return modelAndView;
		}
		/**
		 * 初始化修改店铺员工信息表页面
		 * @param response
		 * @param request
		 * @return
		 */
		@RequestMapping(value = "goModify")
		public ModelAndView goModify( String shop_user_id) {
		   ModelAndView modelAndView= new ModelAndView();
		   ShopUserPO shopUserPO=shopUserService.selectByKey(shop_user_id);
		   String password=shopUserPO.getPassword();
		   String decryptPassword=IMSCodec.decrypt(password,IMSCons.PASSWORD_KEY);
		   shopUserPO.setPassword(decryptPassword);
		   modelAndView.addObject("shopUserPO",shopUserPO);
		   modelAndView.setViewName("beauty/system/shopOwner/modifyShopOwner.jsp");
		   return modelAndView;
		}
		/**
		 * 修改店铺店主信息表信息
		 * @param response
		 * @param request
		 * @return
		 */
		@RequestMapping(value = "updateShopOwner")
		public void updateShopOwner(HttpServletRequest request, HttpServletResponse response) {
			Dto inDto = Dtos.newDto(request);
			Dto outDto =shopUserService.updateShopOwner(inDto);
			IMSCxt.write(response, IMSJson.toJson(outDto));
		}
		/**
		 * 删除店铺员工信息表信息
		 * @param response
		 * @param request
		 * @return
		 */
		@RequestMapping(value = "deleteShopOwner")
		public void deleteShopOwner( String shop_user_id,HttpServletRequest request,HttpServletResponse response) {
			UserPO userPO = IMSCxt.getUserInfo(request.getSession());
			Dto outDto =shopUserService.deleteShopOwner(shop_user_id,userPO.getUser_id());
			IMSCxt.write(response, IMSJson.toJson(outDto));
		}
		/**
		 * 批量删除店铺员工信息表信息
		 * @param response
		 * @param request
		 * @return
		 */
		@RequestMapping(value = "batchDeleteShopUser")
		public void batchDeleteShopUser( String shop_user_ids,HttpServletResponse response) {
		    List<String> shop_user_idList=IMSFormater.separatStringToList(shop_user_ids);
			Dto outDto =shopUserService.batchDelete(shop_user_idList);
			IMSCxt.write(response, IMSJson.toJson(outDto));
		}

}
