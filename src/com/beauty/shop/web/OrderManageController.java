package com.beauty.shop.web;
/**
 * 
 * 类名:com.beauty.shop.web.OrderManageController
 * 描述:订单管理业务相关实体控制
 * 编写者:陈骑元
 * 创建时间:2017年5月7日 下午11:08:13
 * 修改说明:
 */

import java.io.File;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.beauty.common.constant.BeautyCons;
import com.beauty.common.po.BusinessOrderPO;
import com.beauty.common.po.CustomUserPO;
import com.beauty.common.po.NurseProjectPO;
import com.beauty.common.po.NurseTypePO;
import com.beauty.common.po.OrderDepositPO;
import com.beauty.common.po.OrderPayPO;
import com.beauty.common.po.ShopPO;
import com.beauty.common.po.ShopUserPO;
import com.beauty.common.service.BusinessOrderService;
import com.beauty.common.service.CustomUserService;
import com.beauty.common.service.NurseProjectService;
import com.beauty.common.service.NurseTypeService;
import com.beauty.common.service.OrderDepositService;
import com.beauty.common.service.OrderManageService;
import com.beauty.common.service.OrderPayService;
import com.beauty.common.service.ShopCommonService;
import com.beauty.common.service.ShopService;
import com.beauty.common.service.ShopUserService;
import com.beauty.common.utils.ExcelUtil;
import com.ims.common.core.asset.FileOperation;
import com.ims.common.core.asset.IMSCons;
import com.ims.common.core.asset.IMSCxt;
import com.ims.common.core.asset.IMSJson;
import com.ims.common.core.asset.IMSUtils;
import com.ims.common.core.id.IMSId;
import com.ims.common.core.matatype.Dto;
import com.ims.common.core.matatype.Dtos;
import com.ims.common.system.asset.DicCons;
import com.ims.common.system.modules.po.UserPO;

@Controller
@RequestMapping("shop/orderManage")
public class OrderManageController{
	

	@Autowired
	private OrderManageService orderManageService;
	//定金信息
	@Autowired
	private OrderDepositService orderDepositService;
	@Autowired
	private NurseProjectService nurseProjectService;  //护理项目
	 @Autowired
	private  NurseTypeService nurseTypeService;
	/**
	 * 
	 */
	@Autowired
	private ShopCommonService shopCommonService;
	/**
	 * 订单内容
	 */
	@Autowired
	private BusinessOrderService businessOrderService;
	/**
	 * 店铺员工业务逻辑操作类
	 */
	@Autowired
	private ShopUserService shopUserService;
	
	@Autowired
	private CustomUserService customUserService;
	
	@Autowired
	private OrderPayService orderPayService;
	@Autowired
	private ShopService shopService;
	
	/**
	 * 
	 * 简要说明：营业记录初始化
	 * 编写者：陈骑元
	 * 创建时间：2017年5月8日 上午1:47:25
	 * @param 说明
	 * @return 说明
	 */
	@RequestMapping(value = "init")
	public  ModelAndView init(HttpServletRequest request, HttpServletResponse response,HttpSession session) {
		ModelAndView modelAndView= new ModelAndView();
		modelAndView.setViewName("beauty/shop/businessOrder/init.jsp");
		return modelAndView;
	}
	
	/**
	 * 
	 * 简要说明：营业记录初始化
	 * 编写者：陈骑元
	 * 创建时间：2017年5月8日 上午1:47:25
	 * @param 说明
	 * @return 说明
	 */
	@RequestMapping(value = "initRecord")
	public  ModelAndView initRecord(String order_type,HttpServletRequest request, HttpServletResponse response,HttpSession session) {
		ModelAndView modelAndView= new ModelAndView();
		modelAndView.addObject("order_type", order_type);
		if(BeautyCons.ORDER_TYPE_PROJECT.equals(order_type)){
			
			modelAndView.setViewName("beauty/shop/businessOrder/expendRecordList.jsp");
		}else if(BeautyCons.ORDER_TYPE_BEAUTY.equals(order_type)){
			modelAndView.setViewName("beauty/shop/businessOrder/beautyRecordList.jsp");
		}else{
			modelAndView.setViewName("beauty/shop/businessOrder/businessRecordList.jsp");
		}
		
		return modelAndView;
	}
   
	/**
	 * 
	 * 简要说明：分页查询营业记录
	 * 编写者：陈骑元
	 * 创建时间：2017年5月8日 上午1:47:25
	 * @param 说明
	 * @return 说明
	 */
	@RequestMapping(value = "listBusinessRecord")
	public  void listBusinessRecord(HttpServletRequest request, HttpServletResponse response,HttpSession session) {
		ShopUserPO shopUserPO=IMSCxt.getShopUserInfo(session);
		Dto pDto = Dtos.newDto(request);
		pDto.put("shop_id", shopUserPO.getShop_id());
		pDto.setOrder(" a.pay_time DESC ");
		List<Dto> businessRecordList=orderManageService.listBusinessRecordPage(pDto);
		String outString = IMSJson.toGridJson(businessRecordList, pDto.getPageTotal(),IMSCons.DATETIMEMIN);
		IMSCxt.write(response, outString);
	}
	 /**
     * 
     * 简要说明：初始化预约的订单
     * 编写者：陈骑元
     * 创建时间：2017年5月10日 上午1:18:46
     * @param 说明
     * @return 说明
     */
	@RequestMapping(value = "initSubscribeOrder")
	public  ModelAndView initSubscribeOrder(HttpServletRequest request, HttpServletResponse response,HttpSession session) {
		ModelAndView modelAndView= new ModelAndView();
		modelAndView.addObject("query_date", IMSUtils.getDateStr()); //查询当天预约订单
	    modelAndView.setViewName("beauty/shop/businessOrder/subscribeOrderList.jsp");
		return modelAndView;
	}
	/**
	 * 
	 * 简要说明：分页查询营业记录
	 * 编写者：陈骑元
	 * 创建时间：2017年5月8日 上午1:47:25
	 * @param 说明
	 * @return 说明
	 */
	@RequestMapping(value = "listSubscribeOrder")
	public  void listSubscribeOrder(HttpServletRequest request, HttpServletResponse response,HttpSession session) {
		ShopUserPO shopUserPO=IMSCxt.getShopUserInfo(session);
		Dto pDto = Dtos.newDto(request);
		pDto.put("shop_id", shopUserPO.getShop_id());
		pDto.setOrder(" a.subscribe_time ASC ");
		List<Dto> orderList=orderManageService.listSubscribeOrderPage(pDto);
		String outString = IMSJson.toGridJson(orderList, pDto.getPageTotal(),IMSCons.DATETIMEMIN);
		IMSCxt.write(response, outString);
	}
	/**
	 * 
	 * 简要说明:查看订单详情信息
	 * 编写者：陈骑元
	 * 创建时间：2017年5月8日 上午1:47:25
	 * @param 说明
	 * @return 说明
	 */
	@RequestMapping(value = "showOrderDetail")
	public  ModelAndView showOrderDetail(String order_id,HttpServletRequest request, HttpServletResponse response,HttpSession session) {
		ModelAndView modelAndView= new ModelAndView();
		Dto orderDto=orderManageService.queryOrderDetail(order_id);
		modelAndView.addObject("orderDto", orderDto);
		String order_type=orderDto.getString("order_type");
		String order_status=orderDto.getString("order_status");
        if(BeautyCons.ORDER_TYPE_PROJECT.equals(order_type)){
        	String project_id=orderDto.getString("project_id");
        	Dto pDto=Dtos.newDto("order_id", order_id);
        	OrderDepositPO orderDepositPO=orderDepositService.selectOne(pDto);
        	NurseProjectPO nurseProjectPO=nurseProjectService.selectByKey(project_id);
        	if(BeautyCons.ORDER_STATUS_SUBSCRIBE.equals(order_status)){ //已预约
        		modelAndView.setViewName("beauty/shop/businessOrder/showOrderSubscribeDetail.jsp");
        	}else if(BeautyCons.ORDER_STATUS_SERVER.equals(order_status)){
        		modelAndView.setViewName("beauty/shop/businessOrder/showOrderServerDetail.jsp");
        	}else if(BeautyCons.ORDER_STATUS_PAY.equals(order_status)){
        		modelAndView.setViewName("beauty/shop/businessOrder/showOrderPayDetail.jsp");
        	}else{
        		modelAndView.setViewName("beauty/shop/businessOrder/showOrderCompleteDetail.jsp");
        	}
			modelAndView.addObject("orderDepositPO", orderDepositPO);
			modelAndView.addObject("nurseProjectPO", nurseProjectPO);
		}else if(BeautyCons.ORDER_TYPE_BEAUTY.equals(order_type)){
			modelAndView.setViewName("beauty/shop/businessOrder/showBeautyOrderDetail.jsp");
		}else{
			modelAndView.setViewName("beauty/shop/businessOrder/bagOrderDetail.jsp");
		}
		
		return modelAndView;
	}
	
	/**
	 * 
	 * 简要说明:查看订单详情信息
	 * 编写者：陈骑元
	 * 创建时间：2017年5月8日 上午1:47:25
	 * @param 说明
	 * @return 说明
	 */
	@RequestMapping(value = "goModifyOrder")
	public  ModelAndView goModifyOrder(String order_id,String operateWay,HttpServletRequest request, HttpServletResponse response,HttpSession session) {
		ModelAndView modelAndView= new ModelAndView();
		Dto orderDto=orderManageService.queryOrderDetail(order_id);
		modelAndView.addObject("orderDto", orderDto);
		modelAndView.addObject("operateWay", operateWay);
		String order_type=orderDto.getString("order_type");
		if(BeautyCons.ORDER_TYPE_PROJECT.equals(order_type)){
			modelAndView.setViewName("beauty/shop/businessOrder/modifyExpendOrder.jsp");
		}else if(BeautyCons.ORDER_TYPE_BEAUTY.equals(order_type)){
			modelAndView.setViewName("beauty/shop/businessOrder/modifyBeautyOrder.jsp");
		}else{
			modelAndView.setViewName("beauty/shop/businessOrder/bagOrderDetail.jsp");
		}
		
		return modelAndView;
	}
	/**
	 * 
	 * 简要说明：查询店铺员工信息
	 * 编写者：陈骑元
	 * 创建时间：2017年5月23日 下午11:15:18
	 * @param 说明
	 * @return 说明
	 */
	@RequestMapping(value="queryShopUser")
	public void queryShopUser(HttpServletRequest request, HttpServletResponse response,HttpSession session){
		ShopUserPO shopUserPO=IMSCxt.getShopUserInfo(session);
		String shop_id=shopUserPO.getShop_id(); //员工店铺编号
		Dto pDto = Dtos.newDto(request);
		pDto.put("shop_id", shop_id);
		pDto.put("user_type", BeautyCons.USER_TYPE_STAFF);//查询有效店主
		pDto.put("is_del", IMSCons.IS.NO); //查询有效信息
		pDto.put("status", BeautyCons.STATUS_YES);//启用的
		List<ShopUserPO> shopUserPOList =shopUserService.list(pDto);
		String outString = IMSJson.toJson(shopUserPOList);
		IMSCxt.write(response, outString);
	}
	
	/**
	 * 
	 * 简要说明：修改订单信息
	 * 编写者：陈骑元
	 * 创建时间：2017年5月23日 下午11:15:18
	 * @param 说明
	 * @return 说明
	 */
	@RequestMapping(value="modifyOrder")
	public void modifyOrder(HttpServletRequest request, HttpServletResponse response,HttpSession session){
		Dto inDto=Dtos.newDto(request);
        Dto outDto=orderManageService.modifyOrder(inDto);
		String outString =IMSJson.toJson( outDto);
		IMSCxt.write(response, outString);
	}
	/**
	 * 
	 * 简要说明：开始订单
	 * 编写者：陈骑元
	 * 创建时间：2017年5月24日 下午11:21:51
	 * @param 说明
	 * @return 说明
	 */
	@RequestMapping(value="goStartOrder")
	public ModelAndView goStartOrder(String order_id,HttpServletRequest request, HttpServletResponse response,HttpSession session){
		ModelAndView modelAndView= new ModelAndView();
		Dto orderDto=orderManageService.queryOrderDetail(order_id);
		String project_id=orderDto.getString("project_id");
    	Dto pDto=Dtos.newDto("order_id", order_id);
    	OrderDepositPO orderDepositPO=orderDepositService.selectOne(pDto);
    	NurseProjectPO nurseProjectPO=nurseProjectService.selectByKey(project_id);
		modelAndView.addObject("orderDto", orderDto);
		modelAndView.addObject("orderDepositPO", orderDepositPO);
		modelAndView.addObject("nurseProjectPO", nurseProjectPO);
		modelAndView.setViewName("beauty/shop/businessOrder/startOrder.jsp");
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
	@RequestMapping(value="startOrder")
	public void startOrder(String order_id,HttpServletRequest request, HttpServletResponse response,HttpSession session){
		Dto outDto=orderManageService.modifyStartOrder(order_id);
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
	@RequestMapping(value="cancelOrder")
	public void cancelOrder(String order_id,HttpServletRequest request, HttpServletResponse response,HttpSession session){
		Dto outDto=orderManageService.modifyCancelOrder(order_id);
		String outString = IMSJson.toJson(outDto);
		IMSCxt.write(response, outString);
	}
	/**
	 * 
	 * 简要说明：初始化项目列表
	 * 编写者：陈骑元
	 * 创建时间：2017年5月25日 上午12:00:23
	 * @param 说明
	 * @return 说明
	 */
	@RequestMapping(value="initNurseProject")
	public ModelAndView initNurseProject(String order_id,String showWay,HttpServletRequest request, HttpServletResponse response){
		ModelAndView modelAndView= new ModelAndView();
		if("1".equals(showWay)){
			modelAndView.setViewName("beauty/shop/businessOrder/showProjectList.jsp");
		}else{
			modelAndView.addObject("order_id", order_id);
			modelAndView.setViewName("beauty/shop/businessOrder/nurseProjectList.jsp");
		}
		
		return modelAndView;
	}
	/**
	 * 
	 * 项目信息列表
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "listNurseProject")
	public void listNurseProject(HttpServletRequest request, HttpServletResponse response) {
		Dto pDto = Dtos.newDto(request);
		pDto.put("status", BeautyCons. SHOW_STATUS_ON);
		List<Dto> nurseProjectList =shopCommonService.listNurseProjectPage(pDto);
		String outString = IMSJson.toGridJson(nurseProjectList, pDto.getPageTotal());
		IMSCxt.write(response, outString);
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
	 * 简要说明：修改订单项目
	 * 编写者：陈骑元
	 * 创建时间：2017年5月28日 下午11:17:53
	 * @param 说明
	 * @return 说明
	 */
	@RequestMapping(value = "modifyOrderProject")
	public void modifyOrderProject(HttpServletRequest request, HttpServletResponse response){
		Dto inDto = Dtos.newDto(request);
		Dto outDto=orderManageService.modifyOrderProject(inDto);
		String outString = IMSJson.toJson(outDto);
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
	@RequestMapping(value="initCustomRecord")
	public ModelAndView initCustomRecord(String custom_user_id,HttpServletRequest request, HttpServletResponse response){
		ModelAndView modelAndView= new ModelAndView();
		CustomUserPO customUserPO=customUserService.selectByKey(custom_user_id);
	    modelAndView.addObject("customUserPO", customUserPO);
		modelAndView.setViewName("beauty/shop/customUser/customRecordList.jsp");
		return modelAndView;
	}
	
	/**
	 * 
	 * 简要说明：分页查询营业记录
	 * 编写者：陈骑元
	 * 创建时间：2017年5月8日 上午1:47:25
	 * @param 说明
	 * @return 说明
	 */
	@RequestMapping(value = "listCustomRecord")
	public  void listCustomRecord(HttpServletRequest request, HttpServletResponse response,HttpSession session) {
		Dto pDto = Dtos.newDto(request);
		pDto.setOrder(" a.pay_time DESC ");
		List<Dto> businessRecordList=orderManageService.listBusinessRecordPage(pDto);
		String outString = IMSJson.toGridJson(businessRecordList, pDto.getPageTotal(),IMSCons.DATETIMEMIN);
		IMSCxt.write(response, outString);
	}
	
	 /**
     * 
     * 简要说明：调到商家待预约的页面
     * 编写者：陈骑元
     * 创建时间：2017年5月10日 上午1:18:46
     * @param 说明
     * @return 说明
     */
	@RequestMapping(value = "goAddSubscribe")
	public  ModelAndView goAddSubscribe(HttpServletRequest request, HttpServletResponse response,HttpSession session) {
		ModelAndView modelAndView= new ModelAndView();
	    modelAndView.setViewName("beauty/shop/businessOrder/addSubscribe.jsp");
		return modelAndView;
	}
	
	/**
	 * 
	 * 简要说明：商家待预约信息
	 * 编写者：陈骑元
	 * 创建时间：2017年6月1日 上午2:17:00
	 * @param 说明
	 * @return 说明
	 */
	@RequestMapping(value="saveSubscribeOrder")
	public void saveSubscribeOrder(HttpServletRequest request, HttpServletResponse response,HttpSession session){
		Dto inDto=Dtos.newDto(request)	;
		ShopUserPO shopUserPO=IMSCxt.getShopUserInfo(session);
		Dto outDto=orderManageService.saveSubscribeOrder(inDto, shopUserPO);
		String outString = IMSJson.toJson(outDto);
		IMSCxt.write(response, outString);
	}
	
	/**
	 * 
	 * 简要说明：跳转到扫码支付页面
	 * 编写者：陈骑元
	 * 创建时间：2017年5月24日 下午11:21:51
	 * @param 说明
	 * @return 说明
	 */
	@RequestMapping(value="goScanCodeOrder")
	public ModelAndView goScanCodeOrder(String order_id,String returnWay,HttpServletRequest request, HttpServletResponse response,HttpSession session){
		ModelAndView modelAndView= new ModelAndView();
		Dto orderDto=orderManageService.queryOrderDetail(order_id);
		modelAndView.addObject("orderDto", orderDto);
		if("1".equals(returnWay)){
			modelAndView.setViewName("beauty/shop/businessOrder/unifiedOrder.jsp");
		}else{
			modelAndView.setViewName("beauty/shop/businessOrder/scanCodeOrder.jsp");
		}
		
		return modelAndView;
	}
	/**
	 * 
	 * 简要说明：小额扫码枪支付
	 * 编写者：陈骑元
	 * 创建时间：2017年6月6日 上午12:25:14
	 * @param 说明
	 * @return 说明
	 */
	@RequestMapping(value="unifiedPay")
	public void  unifiedPay(String order_id,String auth_code,HttpServletRequest request, 
			HttpServletResponse response,HttpSession session){
		Dto outDto=orderManageService.saveUnifiedPayOrder(order_id, auth_code);
		String outString = IMSJson.toJson(outDto);
		IMSCxt.write(response, outString);
	}
	/**
	 * 
	 * 简要说明：微信扫码支付
	 * 编写者：陈骑元
	 * 创建时间：2017年6月6日 上午12:25:14
	 * @param 说明
	 * @return 说明
	 */
	@RequestMapping(value="scanCodePay")
	public void  scanCodePay(String order_id,String pay_way,HttpServletRequest request, 
			HttpServletResponse response,HttpSession session){
		Dto outDto=orderManageService.saveScanCodePayOrder(order_id,pay_way);
		String outString = IMSJson.toJson(outDto);
		IMSCxt.write(response, outString);
	}
	/**
	 * 
	 * 简要说明：检查订单是否支付情况
	 * 编写者：陈骑元
	 * 创建时间：2017年6月7日 上午1:34:59
	 * @param 说明
	 * @return 说明
	 */
	@RequestMapping(value="checkOrderPay")
	public void checkOrderPay(String out_trade_no,HttpServletRequest request, 
			HttpServletResponse response){
		Dto outDto=Dtos.newDto();
		Dto pDto=Dtos.newDto();
		pDto.put("pay_code", out_trade_no);
		pDto.put("pay_back", BeautyCons.PAY_BACK_YES);
		OrderPayPO orderPayDto=orderPayService.selectOne(pDto);
		if(IMSUtils.isNotEmpty(orderPayDto)){
			if(BeautyCons.PAY_STATUS_YES.equals(orderPayDto.getPay_status())){
				outDto.setAppCode(IMSCons.SUCCESS);
				outDto.setAppMsg("支付成功");
			}else{
				outDto.setAppCode(IMSCons.ERROR);
				outDto.setAppMsg("支付失败");
			}
		}else{
			outDto.setAppCode(IMSCons.WARN);
			outDto.setAppMsg("未收到回执");
		}
		String outString = IMSJson.toJson(outDto);
		IMSCxt.write(response, outString);
	}
	
	/**
	 * 
	 * 简要说明：财务管理
	 * 编写者：陈骑元
	 * 创建时间：2017年6月12日 上午12:43:43
	 * @param 说明
	 * @return 说明
	 */
	@RequestMapping(value="initFinance")
	public ModelAndView initFinance(HttpServletRequest request, HttpServletResponse response){
		ModelAndView modelAndView= new ModelAndView();
		modelAndView.setViewName("beauty/shop/finance/init.jsp");
		return modelAndView;
	}
	/**
	 * 
	 * 简要说明：财务总
	 * 编写者：陈骑元
	 * 创建时间：2017年6月12日 上午12:43:43
	 * @param 说明
	 * @return 说明
	 */
	@RequestMapping(value="initFinanceTotal")
	public ModelAndView initFinanceTotal(HttpServletRequest request, HttpServletResponse response,HttpSession session){
		ModelAndView modelAndView= new ModelAndView();
		String total_month=IMSUtils.getCurrentDate("yyyy-MM");
		modelAndView.addObject("total_month", total_month);
		modelAndView.setViewName("beauty/shop/finance/financeTotal.jsp");
		return modelAndView;
	}
	/**
	 * 
	 * 简要说明：获取店铺汇总信息
	 * 编写者：陈骑元
	 * 创建时间：2017年6月17日 上午8:15:53
	 * @param 说明
	 * @return 说明
	 */
	@RequestMapping(value="queryFinanceTotal")
	public void queryFinanceTotal(String total_month,HttpServletRequest request, HttpServletResponse response,HttpSession session){
		Dto totalDto=Dtos.newDto();
		ShopUserPO shopUserPO=IMSCxt.getShopUserInfo(session);
		ShopPO shopPO=shopService.selectByKey(shopUserPO.getShop_id());
		Dto pDto=Dtos.newDto();
		pDto.put("shop_id", shopPO.getShop_id());
		pDto.put("total_month", total_month);
		Dto sumDto=orderManageService.queryFinanceTotal(pDto);
		Double month_cash_in=shopPO.getMonth_cash_in();  //店铺目标现金收入
		Double month_expend_in=shopPO.getMonth_expend_in();
		Double total_cash_income=sumDto.getDouble("total_cash_income");
		Double total_extend_income=sumDto.getDouble("total_extend_income");
		Double cash_income_percent=total_cash_income/month_cash_in*100;
		Double extend_income_percent=total_extend_income/month_expend_in*100;
		BigDecimal   b1   =   new   BigDecimal(cash_income_percent); 
		double   f1   =   b1.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue(); 
		BigDecimal   b2   =   new   BigDecimal(extend_income_percent); 
		double   f2   =   b2.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue(); 
		totalDto.put("month_cash_in", shopPO.getMonth_cash_in());
		totalDto.put("month_expend_in", shopPO.getMonth_expend_in());
		totalDto.put("total_cash_income", total_cash_income);
		totalDto.put("total_extend_income",total_extend_income);
		totalDto.put("cash_income_percent",f1+"%" );
		totalDto.put("extend_income_percent",f2+"%");
		String outString=IMSJson.toJson(totalDto);
		IMSCxt.write(response, outString);
	}
	/**
	 * 
	 * 简要说明：分页查询营业记录
	 * 编写者：陈骑元
	 * 创建时间：2017年5月8日 上午1:47:25
	 * @param 说明
	 * @return 说明
	 */
	@RequestMapping(value = "listFinanceTotal")
	public  void listFinanceTotal(HttpServletRequest request, HttpServletResponse response,HttpSession session) {
		ShopUserPO shopUserPO=IMSCxt.getShopUserInfo(session);
		Dto pDto = Dtos.newDto(request);
		String total_month=pDto.getString("total_month");
	    if(IMSUtils.isEmpty(total_month)){
	    	total_month=IMSUtils.getCurrentDate("yyyy-MM");
	    }
		pDto.put("total_month", total_month);
		pDto.put("shop_id", shopUserPO.getShop_id());
		pDto.setOrder(" a.pay_time DESC ");
		List<Dto> businessRecordList=orderManageService.listFinancePage(pDto);
		String outString = IMSJson.toGridJson(businessRecordList, pDto.getPageTotal(),IMSCons.DATETIMEMIN);
		IMSCxt.write(response, outString);
	}
	
	/**
	 * 
	 * 简要说明：下载财务总况表
	 * 编写者：陈骑元
	 * 创建时间：2017年6月15日 上午12:37:54
	 * @param 说明
	 * @return 说明
	 */
	@RequestMapping(value = "downloadShopFinance")
	public void downloadShopFinance(HttpServletRequest request, HttpServletResponse response,HttpSession session) {
		String folderPath = session.getServletContext().getRealPath("/excelData/");
		FileOperation.createFolder(folderPath);
		String outExcelPath = folderPath + File.separator + IMSId.uuid()+ ".xls";
		String fileName = "财务总况报表（"+IMSUtils.getCurrentDate("yyyyMMdd")+"）.xls";
		Dto pDto = Dtos.newDto(request);
		ShopUserPO shopUserPO=IMSCxt.getShopUserInfo(session);
		pDto.put("shop_id", shopUserPO.getShop_id());
		pDto.setOrder(" a.pay_time DESC ");
		List<Dto> financeList =orderManageService.listFinance(pDto);
		
		boolean flag = ExcelUtil.createShopFinanceExcel(financeList, outExcelPath);
		if(flag) {
			FileOperation.downloadFile(request,response,outExcelPath, fileName);
		}
	}
	/**
	 * 
	 * 简要说明：财务管理
	 * 编写者：陈骑元
	 * 创建时间：2017年6月12日 上午12:43:43
	 * @param 说明
	 * @return 说明
	 */
	@RequestMapping(value="initShopUserCount")
	public ModelAndView initShopUserCount(HttpServletRequest request, HttpServletResponse response){
		ModelAndView modelAndView= new ModelAndView();
		String total_month=IMSUtils.getCurrentDate("yyyy-MM");
		modelAndView.addObject("total_month", total_month);
		modelAndView.setViewName("beauty/shop/finance/shopUserCountList.jsp");
		return modelAndView;
	}
	/**
	 * 
	 * 简要说明：汇总统计
	 * 编写者：陈骑元
	 * 创建时间：2017年7月16日 下午11:51:26
	 * @param 说明
	 * @return 说明
	 */
	@RequestMapping(value="queryShopSum")
	public void queryShopSum(String total_month,HttpServletRequest request, HttpServletResponse response,HttpSession session){
		ShopUserPO shopUserPO=IMSCxt.getShopUserInfo(session);
		Dto pDto=Dtos.newDto();
		pDto.put("shop_id", shopUserPO.getShop_id());
		pDto.put("total_month", total_month);
		Dto dataDto=orderManageService.queryShopSum(pDto);
		String outString=IMSJson.toJson(dataDto);
		IMSCxt.write(response, outString);
	}
	/**
	 * 
	 * 简要说明：员工工资统计
	 * 编写者：陈骑元
	 * 创建时间：2017年5月8日 上午1:47:25
	 * @param 说明
	 * @return 说明
	 */
	@RequestMapping(value = "listShopUserCount")
	public  void listShopUserCount(HttpServletRequest request, HttpServletResponse response,HttpSession session) {
		ShopUserPO shopUserPO=IMSCxt.getShopUserInfo(session);
		Dto pDto = Dtos.newDto(request);
		String total_month=pDto.getString("total_month");
	    if(IMSUtils.isEmpty(total_month)){
	    	total_month=IMSUtils.getCurrentDate("yyyy-MM");
	    }
		pDto.put("total_month", total_month);
		pDto.put("shop_id", shopUserPO.getShop_id());
		List<Dto> shopUserCountdList=orderManageService.listShopUserCountPage(pDto);
		String outString = IMSJson.toGridJson(shopUserCountdList, pDto.getPageTotal(),IMSCons.DATETIMEMIN);
		IMSCxt.write(response, outString);
	}
	
	/**
	 * 
	 * 简要说明：下载财务总况表
	 * 编写者：陈骑元
	 * 创建时间：2017年6月15日 上午12:37:54
	 * @param 说明
	 * @return 说明
	 */
	@RequestMapping(value = "downloadShopUserCount")
	public void downloadShopUserCount(HttpServletRequest request, HttpServletResponse response,HttpSession session) {
		String folderPath = session.getServletContext().getRealPath("/excelData/");
		FileOperation.createFolder(folderPath);
		String outExcelPath = folderPath + File.separator + IMSId.uuid()+ ".xls";
		String fileName = "员工工资及业绩报表（"+IMSUtils.getCurrentDate("yyyyMMdd")+"）.xls";
		Dto pDto = Dtos.newDto(request);
		ShopUserPO shopUserPO=IMSCxt.getShopUserInfo(session);
		pDto.put("shop_id", shopUserPO.getShop_id());
		List<Dto> shopUserCountList =orderManageService.listShopUserCount(pDto);
		String total_month=pDto.getString("total_month");
		boolean flag = ExcelUtil.createShopUserCountExcel(shopUserCountList,total_month, outExcelPath);
		if(flag) {
			FileOperation.downloadFile(request,response,outExcelPath, fileName);
		}
	}
	/**
	 * 
	 * 简要说明：员工服务订单
	 * 编写者：陈骑元
	 * 创建时间：2017年6月12日 上午12:43:43
	 * @param 说明
	 * @return 说明
	 */
	@RequestMapping(value="initShopUserServerOrder")
	public ModelAndView initShopUserServerOrder(String shop_user_id,String total_month,HttpServletRequest request, HttpServletResponse response){
		ModelAndView modelAndView= new ModelAndView();
		ShopUserPO shopUserPO=shopUserService.selectByKey(shop_user_id);
		modelAndView.addObject("shopUserPO", shopUserPO);
		modelAndView.addObject("total_month", total_month);
		modelAndView.addObject("shop_user_id",shop_user_id);
		modelAndView.setViewName("beauty/shop/finance/shopUserServerOrderList.jsp");
		return modelAndView;
	}
	
	/**
	 * 
	 * 简要说明：员工工资统计
	 * 编写者：陈骑元
	 * 创建时间：2017年5月8日 上午1:47:25
	 * @param 说明
	 * @return 说明
	 */
	@RequestMapping(value = "listShopUserServerOrder")
	public  void listShopUserServerOrder(HttpServletRequest request, HttpServletResponse response,HttpSession session) {
		Dto pDto = Dtos.newDto(request);
		ShopUserPO shopUserPO=IMSCxt.getShopUserInfo(session);
		pDto.put("shop_id", shopUserPO.getShop_id());
		pDto.put("order_status", BeautyCons.ORDER_STATUS_COMPLETE);
		pDto.put("order_type", BeautyCons.ORDER_TYPE_PROJECT);
		pDto.setOrder(" a.finish_time DESC ");
		List<Dto> businessRecordList=orderManageService.listBusinessRecordPage(pDto);
		String outString = IMSJson.toGridJson(businessRecordList, pDto.getPageTotal(),IMSCons.DATETIMEMIN);
		IMSCxt.write(response, outString);
	}
	/**
	 * 
	 * 简要说明：跳转到收款页面
	 * 编写者：陈骑元
	 * 创建时间：2017年7月1日 下午5:01:42
	 * @param 说明
	 * @return 说明
	 */
	@RequestMapping(value = "goPayOrder")
	public ModelAndView goPayOrder(String order_id,HttpServletRequest request, HttpServletResponse response){
		ModelAndView modelAndView= new ModelAndView();
		Dto orderDto=orderManageService.queryOrderDetail(order_id);
		String project_id=orderDto.getString("project_id");
		NurseProjectPO nurseProjectPO=nurseProjectService.selectByKey(project_id);
		String custom_user_id=orderDto.getString("custom_user_id");
		CustomUserPO customUserPO=customUserService.selectByKey(custom_user_id);
		Dto pDto=Dtos.newDto();
    	pDto.put("project_id", project_id);
    	pDto.put("custom_user_id", customUserPO.getCustom_user_id());
    	List<Dto> projectList=orderManageService.queryPayProject(pDto);
    	int count=projectList.size();
    	modelAndView.addObject("beauty_num", customUserPO.getBeauty_num());
    	modelAndView.addObject("count", count);
		modelAndView.addObject("orderDto", orderDto);
		modelAndView.addObject("nurseProjectPO", nurseProjectPO);
		modelAndView.setViewName("beauty/shop/businessOrder/payOrder.jsp");
		return modelAndView;
	}
	/**
	 * 
	 * 简要说明：查询支付的项目
	 * 编写者：陈骑元
	 * 创建时间：2017年7月1日 下午11:45:59
	 * @param 说明
	 * @return 说明
	 */
	@RequestMapping(value = "initPayProject")
	public ModelAndView initPayProject(String custom_user_id,String project_id,HttpServletRequest request, HttpServletResponse response){
		ModelAndView modelAndView= new ModelAndView();
		modelAndView.addObject("project_id", project_id);
		modelAndView.addObject("custom_user_id", custom_user_id);
    	modelAndView.setViewName("beauty/shop/businessOrder/showPayProject.jsp");
		return modelAndView;
	}
	/**
	 * 
	 * 简要说明：查询支付的项目
	 * 编写者：陈骑元
	 * 创建时间：2017年7月1日 下午11:45:59
	 * @param 说明
	 * @return 说明
	 */
	@RequestMapping(value = "showPayProject")
	public void showPayProject(String custom_user_id,String project_id,HttpServletRequest request, HttpServletResponse response){
		Dto pDto=Dtos.newDto();
		pDto.put("project_id", project_id);
		pDto.put("custom_user_id", custom_user_id);
		List<Dto> projectList=orderManageService.queryPayProject(pDto);
		String outString = IMSJson.toGridJson(projectList, projectList.size(),IMSCons.DATE);
		IMSCxt.write(response, outString);
	}
	
	/**
	 * 
	 * 简要说明：颜值进行订单支付
	 * 编写者：陈骑元
	 * 创建时间：2017年6月11日 下午5:13:18
	 * @param 说明
	 * @return 说明
	 */
	@RequestMapping(value = "saveBeautyPayOrder")
	public void saveBeautyPayOrder(String order_id,int beauty_num,String mobile,String username,String checkCode,HttpServletRequest request, HttpServletResponse response,HttpSession session){
		Dto outDto=orderManageService.saveBeautyPayOrder(order_id,beauty_num,mobile,username,checkCode);
		String outString=IMSJson.toJson(outDto);
		IMSCxt.write(response, outString);
	}
		
	/**
	 * 
	 * 简要说明：项目支付订单
	 * 编写者：陈骑元
	 * 创建时间：2017年6月11日 下午5:13:18
	 * @param 说明
	 * @return 说明
	 */
	@RequestMapping(value = "saveProjectPayOrder")
	public void saveProjectPayOrderr(String order_id,String record_id,String mobile,String username,String checkCode,
			HttpServletRequest request, HttpServletResponse response,HttpSession session){
		Dto outDto=orderManageService.saveProjectPayOrder(order_id, record_id,mobile,username,checkCode);
		String outString=IMSJson.toJson(outDto);
		IMSCxt.write(response, outString);
	}
	
	
	
}
