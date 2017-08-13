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
import com.ims.common.system.modules.po.UserPO;
import com.ims.common.core.asset.IMSFormater;
import com.beauty.common.po.CouponActivePO;
import com.beauty.common.po.CouponRecordPO;
import com.beauty.common.service.CouponActiveService;
import com.beauty.common.service.CouponRecordService;
import com.beauty.common.service.ShopCommonService;

/**
 * 
 * 类描述：<b>bis_coupon_active[bis_coupon_active]控制类</b>
 * 创建人：陈骑元
 * 邮箱：240823329@qq.com
 * 创建时间：2017-04-26 21:47:23
 * 修改人：
 * 修改时间：
 * 修改备注： 
 * @version 1.0
 */
@Controller
@RequestMapping(value = "system/couponActive")
public class CouponActiveController {
  
    @Autowired
	private  CouponActiveService couponActiveService;
    @Autowired
    private  CouponRecordService couponRecordService;
    @Autowired
    private  ShopCommonService shopCommonService;
  
	
	
	/**
	 * 页面初始化
	 *
	 * @return
	 */
	@RequestMapping(value = "init")
	public ModelAndView init() {
		return new ModelAndView("beauty/system/couponActive/couponActiveList.jsp");
	}
	
	/**
	 * 
	 * 分页查询bis_coupon_active信息
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "listCouponActive")
	public void listCouponActive(HttpServletRequest request, HttpServletResponse response) {
		Dto pDto = Dtos.newDto(request);
		pDto.setOrder(" a.create_time DESC ");
		List<Dto> couponActiveList =shopCommonService.listCouponActivePage(pDto);
		String outString = IMSJson.toGridJson(couponActiveList, pDto.getPageTotal());
		IMSCxt.write(response, outString);
	}
	/**
	 *查看美研券初始化页面信息
	 *
	 * @return
	 */
	@RequestMapping(value = "initCouponRecord")
	public ModelAndView initCouponRecord(String active_id) {
		ModelAndView modelAndView= new ModelAndView();
		modelAndView.addObject("active_id",active_id);
	    modelAndView.setViewName("beauty/system/couponActive/showCouponRecordList.jsp");
	    return modelAndView;
	}
	
	/**
	 * 
	 * 分页查询兑换记录信息
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "listCouponRecord")
	public void listCouponRecord(HttpServletRequest request, HttpServletResponse response) {
		Dto pDto = Dtos.newDto(request);
		pDto.setOrder(" exchange_time ASC ");
		List<CouponRecordPO> couponRecordList =couponRecordService.likePage(pDto);
		String outString = IMSJson.toGridJson(couponRecordList, pDto.getPageTotal());
		IMSCxt.write(response, outString);
	}
	/**
	 * 
	 * 初始化新增bis_coupon_active页面
	 * @return
	 * 
	 */
	@RequestMapping(value = "goAdd")
	public ModelAndView goAdd() {
		return new ModelAndView("beauty/system/couponActive/addCouponActive.jsp");
	}
	/**
	 * 保存bis_coupon_active信息
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "saveCouponActive")
	public void saveCouponActive(HttpServletRequest request, HttpServletResponse response) {
	    Dto inDto = Dtos.newDto(request);
		Dto outDto = couponActiveService.save(inDto);
		IMSCxt.write(response, IMSJson.toJson(outDto));
	}
	/**
	 * 查看bis_coupon_active详情页面
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "goShow")
	public ModelAndView goShow( String active_id) {
	   ModelAndView modelAndView= new ModelAndView();
	   CouponActivePO couponActivePO=couponActiveService.selectByKey(active_id);
	   modelAndView.addObject("couponActivePO",couponActivePO);
	   modelAndView.setViewName("showCouponActive.jsp");
	   return modelAndView;
	}
	/**
	 * 初始化修改bis_coupon_active页面
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "goModify")
	public ModelAndView goModify( String active_id) {
	   ModelAndView modelAndView= new ModelAndView();
	   CouponActivePO couponActivePO=couponActiveService.selectByKey(active_id);
	   modelAndView.addObject("couponActivePO",couponActivePO);
	   modelAndView.setViewName("modifyCouponActive.jsp");
	   return modelAndView;
	}
	/**
	 * 修改bis_coupon_active信息
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "updateCouponActive")
	public void updateCouponActive(HttpServletRequest request, HttpServletResponse response) {
		Dto inDto = Dtos.newDto(request);
		Dto outDto =couponActiveService.update(inDto);
		IMSCxt.write(response, IMSJson.toJson(outDto));
	}
	/**
	 * 删除bis_coupon_active信息
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "deleteCouponActive")
	public void deleteCouponActive( String active_id,HttpServletRequest request,HttpServletResponse response) {
		UserPO userPO = IMSCxt.getUserInfo(request.getSession());
		Dto outDto =couponActiveService.delete(active_id,userPO.getUser_id());
		IMSCxt.write(response, IMSJson.toJson(outDto));
	}
	/**
	 * 批量删除bis_coupon_active信息
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "batchDeleteCouponActive")
	public void batchDeleteCouponActive( String active_ids,HttpServletResponse response) {
	    List<String> active_idList=IMSFormater.separatStringToList(active_ids);
		Dto outDto =couponActiveService.batchDelete(active_idList);
		IMSCxt.write(response, IMSJson.toJson(outDto));
	}
	
}
