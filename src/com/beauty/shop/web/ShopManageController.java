package com.beauty.shop.web;

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
import com.beauty.common.po.NurseBagPO;
import com.beauty.common.po.NurseProjectPO;
import com.beauty.common.po.NurseTypePO;
import com.beauty.common.po.ProjectRecordPO;
import com.beauty.common.po.ShopPO;
import com.beauty.common.po.ShopPostPO;
import com.beauty.common.po.ShopUserPO;
import com.beauty.common.po.ShopWorkPO;
import com.beauty.common.service.CustomUserService;
import com.beauty.common.service.NurseBagService;
import com.beauty.common.service.NurseProjectService;
import com.beauty.common.service.NurseTypeService;
import com.beauty.common.service.OrderManageService;
import com.beauty.common.service.ProjectRecordService;
import com.beauty.common.service.ShopCommonService;
import com.beauty.common.service.ShopManageService;
import com.beauty.common.service.ShopPostService;
import com.beauty.common.service.ShopService;
import com.beauty.common.service.ShopUserService;
import com.beauty.common.service.ShopWorkService;
import com.ims.common.core.asset.IMSCons;
import com.ims.common.core.asset.IMSCxt;
import com.ims.common.core.asset.IMSJson;
import com.ims.common.core.asset.IMSUtils;
import com.ims.common.core.matatype.Dto;
import com.ims.common.core.matatype.Dtos;

/**
 * 
 * 类名:com.beauty.shop.web.ShopManageController
 * 描述:商家管理业务实体控制类
 * 编写者:陈骑元
 * 创建时间:2017年5月7日 下午11:06:20
 * 修改说明:
 */
@Controller
@RequestMapping("shop/shopManage")
public class ShopManageController {
	
	@Autowired
	private ShopManageService shopManageSerive;
	@Autowired
	private ShopCommonService shopCommonService;
	@Autowired
	private  ShopPostService shopPostService;
	@Autowired
	private  ShopUserService shopUserService;
	@Autowired
	private  NurseProjectService nurseProjectService;
	@Autowired
	private  NurseBagService nurseBagService;
	@Autowired
	private  NurseTypeService nurseTypeService;
	@Autowired
	private CustomUserService customUserService;
	@Autowired
	private OrderManageService orderManageService;
	@Autowired
	private ShopService shopService;
	@Autowired
    private ShopWorkService shopWorkService;
	/**
	 * 调到我的门店
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "goMyShop")
	public  ModelAndView goMyShop(HttpServletRequest request, HttpServletResponse response,HttpSession session) {
		ShopUserPO shopUserPO=IMSCxt.getShopUserInfo(session);
		Dto pDto=Dtos.newDto();
		pDto.put("shop_id",shopUserPO.getShop_id());
		pDto.put("query_date", IMSUtils.getDateStr()); //查询今天的
		Dto cashSumDto=shopManageSerive.queryShopCashSum(pDto);
		int  today_total_order=shopManageSerive.queryShopOrderCount(pDto);
		double today_total_money=0;
		if(IMSUtils.isNotEmpty(cashSumDto)){
			today_total_money=cashSumDto.getDouble("in_money");
		}
		ModelAndView modelAndView= new ModelAndView();
		modelAndView.addObject("today_total_money", today_total_money);
		modelAndView.addObject("today_total_order", today_total_order);
		modelAndView.setViewName("beauty/shop/main/myShop.jsp");
		return modelAndView;
	}
	
	
	/**
	 * 页面初始化
	 *
	 * @return
	 */
	@RequestMapping(value = "initShopUser")
	public ModelAndView initShopUser() {
		return new ModelAndView("beauty/shop/shopUser/shopUserList.jsp");
	}
	
	/**
	 * 
	 * 分页查询店铺员工信息表信息
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "listShopUser")
	public void listShopUser(HttpServletRequest request, HttpServletResponse response,HttpSession session) {
		ShopUserPO shopUserPO=IMSCxt.getShopUserInfo(session);
		Dto pDto = Dtos.newDto(request);
		pDto.put("status", BeautyCons.SHOP_USER_STATUS_VALID);
		pDto.put("shop_id",shopUserPO.getShop_id());
		List<Dto> shopUserList =shopCommonService.listShopUserPage(pDto);
		String outString = IMSJson.toGridJson(shopUserList, pDto.getPageTotal(),IMSCons.DATE);
		IMSCxt.write(response, outString);
	}
	
	/**
	 * 初始化修改店铺员工信息表页面
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "showShopUserDetail")
	public ModelAndView showShopUserDetail( String shop_user_id) {
	   ModelAndView modelAndView= new ModelAndView();
	   ShopUserPO shopUserPO=shopUserService.selectByKey(shop_user_id);
	   Dto pDto=Dtos.newDto("post_code", shopUserPO.getPost_code());
	   ShopPostPO shopPostPO=shopPostService.selectOne(pDto);
	   modelAndView.addObject("shopUserPO",shopUserPO);
	   modelAndView.addObject("shopPostPO", shopPostPO);
	   modelAndView.setViewName("beauty/shop/shopUser/showShopUserDetail.jsp");
	   return modelAndView;
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
	 * 简要说明：初始化项目
	 * 编写者：陈骑元
	 * 创建时间：2017年5月28日 上午11:17:23
	 * @param 说明
	 * @return 说明
	 */
	@RequestMapping(value = "init")
	public ModelAndView init(HttpServletRequest request, HttpServletResponse response){
		Dto pDto=Dtos.newDto(); //统计礼包项目个数
		pDto.put("is_del", IMSCons.IS.NO);
		pDto.put("status", BeautyCons.SHOW_STATUS_ON); //在线售价的
		int bagCount=nurseBagService.rows(pDto);
		int projectCount=nurseProjectService.rows(pDto);
		ModelAndView modelAndView= new ModelAndView();
		modelAndView.addObject("bagCount", bagCount);
		modelAndView.addObject("projectCount", projectCount);
		 modelAndView.setViewName("beauty/shop/project/init.jsp");
		 return modelAndView;
		
	}
	/**
	 * 
	 * 简要说明：初始化礼包项目
	 * 编写者：陈骑元
	 * 创建时间：2017年5月28日 上午11:17:23
	 * @param 说明
	 * @return 说明
	 */
	@RequestMapping(value = "initBag")
	public ModelAndView initBag(HttpServletRequest request, HttpServletResponse response){
		ModelAndView modelAndView= new ModelAndView();
		modelAndView.setViewName("beauty/shop/project/bagList.jsp");
		return modelAndView;
		
	}
	/**
	 * 
	 * 简要说明：初始化礼包项目
	 * 编写者：陈骑元
	 * 创建时间：2017年5月28日 上午11:17:23
	 * @param 说明
	 * @return 说明
	 */
	@RequestMapping(value = "listBag")
	public void listBag(HttpServletRequest request, HttpServletResponse response){
		Dto pDto = Dtos.newDto(request);
		pDto.setOrder("  a.sort_no DESC ");
		pDto.put("status",BeautyCons.SHOW_STATUS_ON);
		List<Dto> nurseBagList =shopCommonService.listNurseBagPage(pDto);
		String outString = IMSJson.toGridJson(nurseBagList, pDto.getPageTotal(),IMSCons.DATE);
		IMSCxt.write(response, outString);
		
	}
	/**
	 * 
	 * 简要说明：初始化护理项目
	 * 编写者：陈骑元
	 * 创建时间：2017年5月28日 上午11:17:23
	 * @param 说明
	 * @return 说明
	 */
	@RequestMapping(value = "initProject")
	public ModelAndView initProject(HttpServletRequest request, HttpServletResponse response){
		ModelAndView modelAndView= new ModelAndView();
		modelAndView.setViewName("beauty/shop/project/projectList.jsp");
		return modelAndView;
		
	}
	/**
	 * 
	 * 简要说明：初始化礼包项目
	 * 编写者：陈骑元
	 * 创建时间：2017年5月28日 上午11:17:23
	 * @param 说明
	 * @return 说明
	 */
	@RequestMapping(value = "listProject")
	public void listProject(HttpServletRequest request, HttpServletResponse response){
		Dto pDto = Dtos.newDto(request);
		pDto.setOrder(" b.sort_no ASC ,a.sort_no ASC  ");
		pDto.put("status",BeautyCons.SHOW_STATUS_ON);
		List<Dto> nurseProjectList =shopCommonService.listNurseProjectPage(pDto);
		String outString = IMSJson.toGridJson(nurseProjectList, pDto.getPageTotal());
		IMSCxt.write(response, outString);
		
	}
	/**
	 * 
	 * 简要说明：查看项目详情
	 * 编写者：陈骑元
	 * 创建时间：2017年5月28日 上午11:17:23
	 * @param 说明
	 * @return 说明
	 */
	@RequestMapping(value = "showBagDetail")
	public ModelAndView showBagDetail(String bag_id,HttpServletRequest request, HttpServletResponse response){
		ModelAndView modelAndView= new ModelAndView();
		NurseBagPO nurseBagPO=nurseBagService.selectByKey(bag_id);
		List<Dto>  bagProjectList=shopCommonService.listBagProject(bag_id);
		modelAndView.addObject("nurseBagPO", nurseBagPO);
		modelAndView.addObject("bagProjectList", bagProjectList);
		modelAndView.setViewName("beauty/shop/project/showBagDetail.jsp");
		return modelAndView;
		
	}
	/**
	 * 
	 * 简要说明：查看项目详情
	 * 编写者：陈骑元
	 * 创建时间：2017年5月28日 上午11:17:23
	 * @param 说明
	 * @return 说明
	 */
	@RequestMapping(value = "showProjectDetail")
	public ModelAndView showProjectDetail(String project_id,HttpServletRequest request, HttpServletResponse response){
		ModelAndView modelAndView= new ModelAndView();
		NurseProjectPO nurseProjectPO=nurseProjectService.selectByKey(project_id);
		modelAndView.addObject("nurseProjectPO", nurseProjectPO);
		modelAndView.setViewName("beauty/shop/project/showProjectDetail.jsp");
		return modelAndView;
		
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
	/**
	 * 
	 * 简要说明：初始顾客消费者
	 * 编写者：陈骑元
	 * 创建时间：2017年5月28日 上午11:17:23
	 * @param 说明
	 * @return 说明
	 */
	@RequestMapping(value = "initCustomUser")
	public ModelAndView initCustomUser(HttpServletRequest request, HttpServletResponse response){
		ModelAndView modelAndView= new ModelAndView();
		modelAndView.setViewName("beauty/shop/customUser/shopCustomUserList.jsp");
		return modelAndView;
		
	}
	/**
	 * 
	 * 简要说明：查询店铺的过客信息
	 * 编写者：陈骑元
	 * 创建时间：2017年5月29日 下午4:29:39
	 * @param 说明
	 * @return 说明
	 */
	@RequestMapping(value = "listShopCustomUser")
	public void listShopCustomUser(HttpServletRequest request, HttpServletResponse response,
			HttpSession session){
		ShopUserPO shopUserPO=IMSCxt.getShopUserInfo(session);
		Dto pDto = Dtos.newDto(request);
		pDto.put("shop_id", shopUserPO.getShop_id());
		String recent_type=pDto.getString("recent_type");
		if(IMSUtils.isNotEmpty(recent_type)){
			int num=Integer.parseInt(recent_type);
			int dayNum=num*30;
			pDto.put("dayNum", dayNum);
		}
		pDto.setOrder(" custom_user_id DESC ");
		List<Dto> customUserList =shopCommonService.listShopCustomUserPage(pDto);
		String outString = IMSJson.toGridJson(customUserList, pDto.getPageTotal(),IMSCons.DATETIMEMIN);
		IMSCxt.write(response, outString);
		
	}
	
	/**
	 * 
	 * 简要说明：初始化消费记录
	 * 编写者：陈骑元
	 * 创建时间：2017年5月25日 上午12:00:23
	 * @param 说明
	 * @return 说明
	 */
	@RequestMapping(value="showCustomUserDetail")
	public ModelAndView showCustomUserDetail(String custom_user_id,HttpServletRequest request, HttpServletResponse response){
		ModelAndView modelAndView= new ModelAndView();
		CustomUserPO customUserPO=customUserService.selectByKey(custom_user_id);
		Dto pDto=Dtos.newDto();
		pDto.put("custom_user_id", custom_user_id);
		pDto.setOrder(" a.create_time DESC ");
		List<Dto> bagList=orderManageService.listMyBagDto(pDto);
		for(Dto bagDto:bagList){
			 String record_id=bagDto.getString("record_id");
			 Dto dto=Dtos.newDto();
			 dto.put("bag_record_id", record_id);
			 List<Dto> projectList=orderManageService.listMyProject(dto);
			 bagDto.put("projectList", projectList);
		}
		modelAndView.addObject("bagList", bagList);
		modelAndView.addObject("customUserPO", customUserPO);
		modelAndView.setViewName("beauty/shop/customUser/showCustomUserDetail.jsp");
		return modelAndView;
	}
	/**
	 * 
	 * 简要说明：初始化店铺设定
	 * 编写者：陈骑元
	 * 创建时间：2017年5月28日 上午11:17:23
	 * @param 说明
	 * @return 说明
	 */
	@RequestMapping(value = "initSet")
	public ModelAndView initSet(HttpServletRequest request, HttpServletResponse response){
		ModelAndView modelAndView= new ModelAndView();

		modelAndView.setViewName("beauty/shop/shopSet/initSet.jsp");
		return modelAndView;
		
	}
	/**
	 * 
	 * 简要说明：初始化店铺设定
	 * 编写者：陈骑元
	 * 创建时间：2017年5月28日 上午11:17:23
	 * @param 说明
	 * @return 说明
	 */
	@RequestMapping(value = "showShopDetail")
	public ModelAndView showShopDetail(HttpServletRequest request, HttpServletResponse response,
			HttpSession session){
		ModelAndView modelAndView= new ModelAndView();
		ShopUserPO shopUserPO=IMSCxt.getShopUserInfo(session);
		ShopPO shopPO=shopService.selectByKey(shopUserPO.getShop_id());
		ShopUserPO shopOwner=shopUserService.selectByKey(shopPO.getOwner_id());
		Dto countDto=Dtos.newDto();
		countDto.put("owner_id", shopPO.getOwner_id());
		countDto.put("is_del", IMSCons.IS.NO);
		countDto.put("show_status", BeautyCons.STATUS_YES);
		int count=shopService.rows(countDto);
		modelAndView.addObject("count", count);
		modelAndView.addObject("shopPO", shopPO);
		modelAndView.addObject("shopOwner",shopOwner);
		modelAndView.setViewName("beauty/shop/shopSet/showShopDetail.jsp");
		return modelAndView;
		
	}
	
	/**
	 * 
	 * 简要说明：初始化店铺设定
	 * 编写者：陈骑元
	 * 创建时间：2017年5月28日 上午11:17:23
	 * @param 说明
	 * @return 说明
	 */
	@RequestMapping(value = "shopSet")
	public ModelAndView shopSet(HttpServletRequest request, HttpServletResponse response,HttpSession session){
		ModelAndView modelAndView= new ModelAndView();
		ShopUserPO shopUserPO=IMSCxt.getShopUserInfo(session);
		String shop_id=shopUserPO.getShop_id();
		Dto workDto=Dtos.newDto();
		workDto.put("shop_id", shop_id);
		ShopWorkPO shopWorkPO=shopWorkService.selectOne(workDto);
		if(IMSUtils.isNotEmpty(shopWorkPO)){
			modelAndView.addObject("shopWorkPO", shopWorkPO);
			modelAndView.addObject("show_status", "1");
		}else{
			modelAndView.addObject("show_status", "0");
		}
	
		modelAndView.setViewName("beauty/shop/shopSet/shopSet.jsp");
		return modelAndView;
		
	}
	
	/**
	 * 
	 * 简要说明：执行订单 ，修改订单状态，同时执行退款操作
	 * 编写者：陈骑元
	 * 创建时间：2017年5月29日 上午12:24:09
	 * @param 说明
	 * @return 说明
	 */
	@RequestMapping(value="saveShopSet")
	public void saveShopSet(HttpServletRequest request, HttpServletResponse response,HttpSession session){
		Dto inDto=Dtos.newDto(request);
		ShopUserPO shopUserPO=IMSCxt.getShopUserInfo(session);
		Dto outDto=shopWorkService.save(inDto,shopUserPO);
		String outString = IMSJson.toJson(outDto);
		IMSCxt.write(response, outString);
	}
	/**
	 * 
	 * 简要说明：执行订单 ，修改订单状态，同时执行退款操作
	 * 编写者：陈骑元
	 * 创建时间：2017年5月29日 上午12:24:09
	 * @param 说明
	 * @return 说明
	 */
	@RequestMapping(value="relieveShopSet")
	public void relieveShopSet(HttpServletRequest request, HttpServletResponse response,HttpSession session){
		Dto inDto=Dtos.newDto(request);
		Dto outDto=shopWorkService.delete(inDto);
		String outString = IMSJson.toJson(outDto);
		IMSCxt.write(response, outString);
	}
	
	/**
	 * 
	 * 简要说明：执行订单 ，修改订单状态，同时执行退款操作
	 * 编写者：陈骑元
	 * 创建时间：2017年5月29日 上午12:24:09
	 * @param 说明
	 * @return 说明
	 */
	@RequestMapping(value="resetShopSet")
	public void resetShopSet(HttpServletRequest request, HttpServletResponse response,HttpSession session){
		Dto inDto=Dtos.newDto(request);
		Dto outDto=Dtos.newDto(request);
		 String whether_set=inDto.getString("whether_set");
		   String work_id=inDto.getString("work_id"); 
		   ShopWorkPO shopWorkPO=shopWorkService.selectByKey(work_id);
		   if(whether_set.equals(IMSCons.IS.YES)){
			  String work_password=inDto.getString("work_password");
			  if(!shopWorkPO.getWork_password().equals(work_password)){
				   outDto.setAppCode(IMSCons.WARN);
				   outDto.setAppMsg("重置失败，输入本机密码不正确。");
				
			  }else{
				  outDto.put("work_sn", shopWorkPO.getWork_sn());
				  outDto.setAppCode(IMSCons.SUCCESS);
				   outDto.setAppMsg("重置工作机成功。");
			  }
		   }else{
			   outDto.put("work_sn", shopWorkPO.getWork_sn());
			   outDto.setAppCode(IMSCons.SUCCESS);
			   outDto.setAppMsg("重置工作机成功。");
		   }
		String outString = IMSJson.toJson(outDto);
		IMSCxt.write(response, outString);
	}
	
}
