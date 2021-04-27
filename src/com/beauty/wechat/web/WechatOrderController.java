package com.beauty.wechat.web;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.beauty.common.constant.BeautyCons;
import com.beauty.common.po.BeautyConfigPO;
import com.beauty.common.po.CustomUserPO;
import com.beauty.common.po.NurseBagPO;
import com.beauty.common.po.NurseProjectPO;
import com.beauty.common.po.OrderDepositPO;
import com.beauty.common.po.ShopPO;
import com.beauty.common.service.BeautyConfigService;
import com.beauty.common.service.CustomUserService;
import com.beauty.common.service.NurseBagService;
import com.beauty.common.service.NurseProjectService;
import com.beauty.common.service.OrderDepositService;
import com.beauty.common.service.OrderManageService;
import com.beauty.wechat.service.WechatOrderService;
import com.ims.common.core.asset.IMSCons;
import com.ims.common.core.asset.IMSCxt;
import com.ims.common.core.asset.IMSJson;
import com.ims.common.core.matatype.Dto;
import com.ims.common.core.matatype.Dtos;

/**
 * 
 * 类名:com.beauty.wechat.web.WechatOrderController
 * 描述:微信订单相关
 * 编写者:陈骑元
 * 创建时间:2017年5月19日 下午11:10:47
 * 修改说明:
 */
@Controller
@RequestMapping("wechat/order")
public class WechatOrderController{
	@Autowired
	private WechatOrderService wechatOrderService;
	@Autowired
	private BeautyConfigService beautyConfigService;
	@Autowired
	private OrderManageService orderManageService;
	//定金信息
    @Autowired
	private OrderDepositService orderDepositService;
	@Autowired
    private NurseProjectService nurseProjectService;  //护理项目
	@Autowired
	private CustomUserService customUserService;
	@Autowired
	private NurseBagService nurseBagService;
	/**
	 * 
	 * 简要说明：展示预约时间
	 * 编写者：陈骑元
	 * 创建时间：2017年6月27日 下午10:37:02
	 * @param 说明
	 * @return 说明
	 */
	@RequestMapping("showSubscribeTime")
	public void showSubscribeTime(String shop_id,String subscribe_date,
			HttpServletRequest request, HttpServletResponse response){
		List<Dto> listDto=wechatOrderService.queryShopSubscibeTime(shop_id, subscribe_date);
		String outString = IMSJson.toGridJson(listDto,listDto.size());
		IMSCxt.write(response, outString);
	}
	/**
	 * 
	 * 简要说明：保存预约订单信息
	 * 编写者：陈骑元
	 * 创建时间：2017年6月3日 下午9:29:27
	 * @param 说明
	 * @return 说明
	 */
	@RequestMapping("saveAppSubscribeOrder")
	public void saveAppSubscribeOrder(HttpServletRequest request, HttpServletResponse response,HttpSession session){
		 Dto inDto=Dtos.newDto(request);
		 CustomUserPO customUserPO=IMSCxt.getCustomUserInfo(session);
		 Dto outDto=wechatOrderService.saveAppSubscribeOrder(inDto, customUserPO);
		 String outString=IMSJson.toJson(outDto);
		 IMSCxt.write(response, outString);
	}
	
	/**
	 * 
	 * 简要说明：查询美丽币的购买信息
	 * 编写者：陈骑元
	 * 创建时间：2017年6月10日 下午3:14:56
	 * @param 说明
	 * @return 说明
	 */
	@RequestMapping(value = "listBeautyInfo")
	public ModelAndView listBeautyInfo(String shop_id,HttpServletRequest request, HttpServletResponse response,HttpSession session){
		Dto pDto=Dtos.newDto();
		pDto.put("is_del", IMSCons.IS.NO);
		pDto.setOrder(" buy_num ASC");
		List<BeautyConfigPO> configList=beautyConfigService.list(pDto);
		//一元人民币可以等价多少颜值
	    String exchange_beauty_str=IMSCxt.getParamValue(BeautyCons.EXCHANGE_BEAUTY_KEY);
	    int  exchange_beauty=Integer.parseInt(exchange_beauty_str);
	    List<Dto> beautyList=new ArrayList<Dto>();
	    for(BeautyConfigPO config:configList){
	    	Dto beautyDto=Dtos.newDto();
	    	int  buy_num=config.getBuy_num();
	    	beautyDto.put("config_id", config.getConfig_id());
	    	beautyDto.put("buy_num", buy_num);
	    	beautyDto.put("give_num", config.getGive_num());
	    	BigDecimal b1 = new BigDecimal(Double.toString(buy_num));  
	        BigDecimal b2 = new BigDecimal(Double.toString(exchange_beauty));  
	        double total_price=b1.divide(b2, 2, BigDecimal.ROUND_HALF_UP).doubleValue();  
	        beautyDto.put("total_price", total_price);
	        beautyList.add(beautyDto);
	    }
		ModelAndView modelAndView= new ModelAndView();
		modelAndView.addObject("beautyList", beautyList);
		modelAndView.addObject("shop_id", shop_id);
	    modelAndView.setViewName("beauty/wechat/my/buyBeauty.jsp");
		return modelAndView;
	}
	
	/**
	 * 
	 * 简要说明：保存美丽币购买信息
	 * 编写者：陈骑元
	 * 创建时间：2017年6月3日 下午9:29:27
	 * @param 说明
	 * @return 说明
	 */
	@RequestMapping("saveBuyBeautyOrder")
	public void saveBuyBeautyOrder(HttpServletRequest request, HttpServletResponse response,HttpSession session){
		 Dto inDto=Dtos.newDto(request);
		 CustomUserPO customUserPO=IMSCxt.getCustomUserInfo(session);
		 Dto outDto=wechatOrderService.saveBuyBeautyOrder(inDto, customUserPO);
		 String outString=IMSJson.toJson(outDto);
		 IMSCxt.write(response, outString);
	}
	
	/**
	 * 
	 * 简要说明：初始化护理订单
	 * 编写者：陈骑元
	 * 创建时间：2017年6月11日 上午11:36:57
	 * @param 说明
	 * @return 说明
	 */
	@RequestMapping(value = "goMyOrder")
	public ModelAndView goMyOrder(String index,HttpServletRequest request, HttpServletResponse response,HttpSession session){
		ModelAndView modelAndView= new ModelAndView();
		modelAndView.addObject("index", index);
		modelAndView.setViewName("beauty/wechat/my/order/myOrder.jsp");
		return modelAndView;
	}
	/**
	 * 
	 * 简要说明：搜索护理订单
	 * 编写者：陈骑元
	 * 创建时间：2017年5月21日 上午10:57:42
	 * @param 说明
	 * @return 说明
	 */
	@RequestMapping(value = "listNurseOrder")
	public void listNurseOrder(HttpServletRequest request, HttpServletResponse response,HttpSession session){
		CustomUserPO customUserPO=IMSCxt.getCustomUserInfo(session);
		Dto pDto =Dtos.newDto(request);
		pDto.put("custom_user_id", customUserPO.getCustom_user_id());
		pDto.setOrder( " a.create_time DESC ");
		List<Dto> nurseOrderList=orderManageService.listNurseOrderPage(pDto);
		String outString = IMSJson.toGridJson(nurseOrderList, pDto.getPageTotal(),IMSCons.DATETIMEMIN);
		IMSCxt.write(response, outString);
	}
	/**
	 * 
	 * 简要说明：取消订单
	 * 编写者：陈骑元
	 * 创建时间：2017年6月11日 下午3:16:04
	 * @param 说明
	 * @return 说明
	 */
	@RequestMapping(value = "cancelOrder")
	public void cancelOrder(String order_id,HttpServletRequest request, HttpServletResponse response,HttpSession session){
		 Dto outDto=wechatOrderService.cancelOrder(order_id);
		 String outString=IMSJson.toJson(outDto);
		 IMSCxt.write(response, outString);
	}
	
	
	/**
	 * 
	 * 简要说明：查看护理订单详情
	 * 编写者：陈骑元
	 * 创建时间：2017年6月11日 下午10:16:06
	 * @param 说明
	 * @return 说明
	 */
	@RequestMapping(value="goPayOrder")
	public ModelAndView goPayOrder(String order_id,HttpServletRequest request, HttpServletResponse response,HttpSession session){
		ModelAndView modelAndView= new ModelAndView();
		Dto orderDto=orderManageService.queryOrderDetail(order_id);
		CustomUserPO customUserPO=IMSCxt.getCustomUserInfo(session);
		CustomUserPO customUserPONew=customUserService.selectByKey(customUserPO.getCustom_user_id());
		String project_id=orderDto.getString("project_id");
    	NurseProjectPO nurseProjectPO=nurseProjectService.selectByKey(project_id);
    	Dto pDto=Dtos.newDto();
    	pDto.put("project_id", project_id);
    	pDto.put("custom_user_id", customUserPO.getCustom_user_id());
    	List<Dto> projectList=orderManageService.queryPayProject(pDto);
    	int count=projectList.size();
    	modelAndView.addObject("count", count);
    	modelAndView.addObject("projectList",projectList);
    	modelAndView.addObject("customUserPO", customUserPONew);
    	modelAndView.addObject("orderDto", orderDto);
		modelAndView.addObject("nurseProjectPO", nurseProjectPO);
		modelAndView.setViewName("beauty/wechat/my/order/payOrder.jsp");
		return modelAndView;
		
	}
	/**
	 * 
	 * 简要说明：微信支付订单
	 * 编写者：陈骑元
	 * 创建时间：2017年6月11日 下午5:13:18
	 * @param 说明
	 * @return 说明
	 */
	@RequestMapping(value = "payOrder")
	public void payOrder(String order_id,HttpServletRequest request, HttpServletResponse response,HttpSession session){
		CustomUserPO customUserPO=IMSCxt.getCustomUserInfo(session);
		 Dto outDto=wechatOrderService.payOrder(order_id,customUserPO);
		 String outString=IMSJson.toJson(outDto);
		 IMSCxt.write(response, outString);
	}
	/**
	 * 
	 * 简要说明：微信支付订单
	 * 编写者：陈骑元
	 * 创建时间：2017年6月11日 下午5:13:18
	 * @param 说明
	 * @return 说明
	 */
	@RequestMapping(value = "beautyPayOrder")
	public void beautyPayOrder(String order_id,int beauty_num,HttpServletRequest request, HttpServletResponse response,HttpSession session){
		CustomUserPO customUserPO=IMSCxt.getCustomUserInfo(session);
		CustomUserPO customUserPONew=customUserService.selectByKey(customUserPO.getCustom_user_id());
		Dto outDto=wechatOrderService.saveBeautyPayOrder(order_id,beauty_num,customUserPONew);
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
	@RequestMapping(value = "projectPayOrder")
	public void projectPayOrder(String order_id,String record_id,HttpServletRequest request, HttpServletResponse response,HttpSession session){
		CustomUserPO customUserPO=IMSCxt.getCustomUserInfo(session);
		Dto outDto=wechatOrderService.saveProjectPayOrder(order_id, record_id, customUserPO);
		String outString=IMSJson.toJson(outDto);
		IMSCxt.write(response, outString);
	}
	
	
	/**
	 * 
	 * 简要说明：查看护理订单详情
	 * 编写者：陈骑元
	 * 创建时间：2017年6月11日 下午10:16:06
	 * @param 说明
	 * @return 说明
	 */
	@RequestMapping(value="showNurseOrderDetail")
	public ModelAndView showNurseOrderDetail(String order_id,HttpServletRequest request, HttpServletResponse response,HttpSession session){
		ModelAndView modelAndView= new ModelAndView();
		Dto orderDto=orderManageService.queryOrderDetail(order_id);
		String project_id=orderDto.getString("project_id");
    	Dto pDto=Dtos.newDto("order_id", order_id);
    	OrderDepositPO orderDepositPO=orderDepositService.selectOne(pDto);
    	NurseProjectPO nurseProjectPO=nurseProjectService.selectByKey(project_id);
    	modelAndView.addObject("orderDto", orderDto);
    	modelAndView.addObject("orderDepositPO", orderDepositPO);
		modelAndView.addObject("nurseProjectPO", nurseProjectPO);
		String order_status=orderDto.getString("order_status");
		if(BeautyCons.ORDER_STATUS_SUBSCRIBE.equals(order_status)){ //已预约
			modelAndView.setViewName("beauty/wechat/my/order/showSubscribeOrderDetail.jsp");
    	}else if(BeautyCons.ORDER_STATUS_SERVER.equals(order_status)){
    		modelAndView.setViewName("beauty/wechat/my/order/showServerOrderDetail.jsp");
    	}else if(BeautyCons.ORDER_STATUS_PAY.equals(order_status)){
    		modelAndView.setViewName("beauty/wechat/my/order/showPayOrderDetail.jsp");
    	}else if(BeautyCons.ORDER_STATUS_UNDO.equals(order_status)){
    		modelAndView.setViewName("beauty/wechat/my/order/showCancelOrderDetail.jsp");
    	}else{
    		modelAndView.setViewName("beauty/wechat/my/order/showCompleteOrderDetail.jsp");
    	}
		
		return modelAndView;
		
	}
	/**
	 * 
	 * 简要说明：查看护理订单详情
	 * 编写者：陈骑元
	 * 创建时间：2017年6月11日 下午10:16:06
	 * @param 说明
	 * @return 说明
	 */
	@RequestMapping(value="showBagOrderDetail")
	public ModelAndView showBagOrderDetail(String order_id,String overdue_date,HttpServletRequest request, HttpServletResponse response,HttpSession session){
		ModelAndView modelAndView= new ModelAndView();
		Dto orderDto=orderManageService.queryOrderDetail(order_id);
		String project_id=orderDto.getString("project_id");
		NurseBagPO nurseBagPO=nurseBagService.selectByKey(project_id);
		modelAndView.addObject("nurseBagPO", nurseBagPO);
		modelAndView.addObject("orderDto", orderDto);
		modelAndView.addObject("overdue_date", overdue_date);
		modelAndView.setViewName("beauty/wechat/my/order/showBagOrderDetail.jsp");
		return modelAndView;
		
	}
	/**
	 * 
	 * 简要说明：保存礼包购买信息
	 * 编写者：陈骑元
	 * 创建时间：2017年6月3日 下午9:29:27
	 * @param 说明
	 * @return 说明
	 */
	@RequestMapping("saveBuyBagOrder")
	public void saveBuyBagOrder(HttpServletRequest request, HttpServletResponse response,HttpSession session){
		 Dto inDto=Dtos.newDto(request);
		 CustomUserPO customUserPO=IMSCxt.getCustomUserInfo(session);
		 Dto outDto=wechatOrderService.saveBuyBagOrder(inDto, customUserPO);
		 String outString=IMSJson.toJson(outDto);
		 IMSCxt.write(response, outString);
	}
	
	/**
	 * 
	 * 简要说明：保存礼包购买信息
	 * 编写者：陈骑元
	 * 创建时间：2017年6月3日 下午9:29:27
	 * @param 说明
	 * @return 说明
	 */
	@RequestMapping("saveFreeBagOrder")
	public void saveFreeBagOrder(HttpServletRequest request, HttpServletResponse response,HttpSession session){
		 Dto inDto=Dtos.newDto(request);
		 CustomUserPO customUserPO=IMSCxt.getCustomUserInfo(session);
		 Dto outDto=wechatOrderService.saveFreeBagOrder(inDto, customUserPO);
		 String outString=IMSJson.toJson(outDto);
		 IMSCxt.write(response, outString);
	}
	/**
	 * 
	 * 简要说明：搜索护理订单
	 * 编写者：陈骑元
	 * 创建时间：2017年5月21日 上午10:57:42
	 * @param 说明
	 * @return 说明
	 */
	@RequestMapping(value = "listBagOrder")
	public void listBagOrder(HttpServletRequest request, HttpServletResponse response,HttpSession session){
		CustomUserPO customUserPO=IMSCxt.getCustomUserInfo(session);
		Dto pDto =Dtos.newDto(request);
		pDto.put("custom_user_id", customUserPO.getCustom_user_id());
		pDto.setOrder( " a.create_time DESC ");
		List<Dto> bagOrderList=orderManageService.listBagOrderPage(pDto);
		String outString = IMSJson.toGridJson(bagOrderList, pDto.getPageTotal(),IMSCons.DATE);
		IMSCxt.write(response, outString);
	}
	
}
