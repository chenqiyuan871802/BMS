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
import com.beauty.common.po.NurseBagPO;
import com.beauty.common.service.NurseBagService;
import com.beauty.common.service.OrderManageService;
import com.beauty.common.service.ShopCommonService;
import com.beauty.wechat.constant.WechatCons;
import com.beauty.wechat.model.Token;
import com.beauty.wechat.service.WechatCommonService;
import com.beauty.wechat.service.WechatOrderService;
import com.beauty.wechat.util.WechatCxt;
import com.ims.common.core.asset.IMSCons;
import com.ims.common.core.asset.IMSCxt;
import com.ims.common.core.asset.IMSJson;
import com.ims.common.core.asset.IMSUtils;
import com.ims.common.core.matatype.Dto;
import com.ims.common.core.matatype.Dtos;

/**
 * 
 * 类名:com.beauty.wechat.web.WechatBagController
 * 描述:微信礼包开发
 * 编写者:陈骑元
 * 创建时间:2017年5月19日 下午11:07:03
 * 修改说明:
 */
@Controller
@RequestMapping("wechat/bag")
public class WechatBagController {
	
	@Autowired
	private NurseBagService nurseBagService;
	@Autowired
	private  ShopCommonService shopCommonService;
	@Autowired
	private OrderManageService orderManageService;
	@Autowired
	private WechatOrderService wechatOrderService;
	
	/**
	 * 跳到我得礼包页面
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "goBag")
	public  ModelAndView goBag(HttpServletRequest request, HttpServletResponse response,HttpSession session) throws IOException {
		ModelAndView modelAndView= new ModelAndView();
	    modelAndView.setViewName("beauty/wechat/bag/bagList.jsp");
		return modelAndView;
	}
	/**
	 * 跳到我得礼包页面
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "searchBag")
	public void searchBag(HttpServletRequest request, HttpServletResponse response,HttpSession session) throws IOException {
	       List<Dto> bagList=shopCommonService.listActiveBag(null);
	       for(Dto bagDto:bagList){
	    	   int minute=bagDto.getInteger("remain_time");
	    	   String formatMinute=IMSUtils.changeMinuteFormat(minute);
	    	   bagDto.put("formatMinute", formatMinute);
	       }
	       String outString=IMSJson.toGridJson(bagList,bagList.size());
	       IMSCxt.write(response, outString);
	}
	/**
	 * 查看礼包详情
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "showBagDetail")
	public  ModelAndView showBagDetail(String bag_id,HttpServletRequest request, HttpServletResponse response,HttpSession session) throws IOException {
		ModelAndView modelAndView= new ModelAndView();
	    NurseBagPO bag=nurseBagService.selectByKey(bag_id);
		List<Dto>  bagProjectList=shopCommonService.listBagProject(bag_id);
		double oldTotalPrice=0;
		double newTotalPrice=0;
		String bagProject="";
		for(int i=0;i<bagProjectList.size();i++){
			Dto project=bagProjectList.get(i);
			String project_name=project.getString("project_name");
			int project_num=project.getInteger("project_num");
			oldTotalPrice+=project.getDouble("rmb_price")*project_num;
			newTotalPrice+=project.getDouble("project_new_price")*project_num;
			if(i==0){
				bagProject+=project_name;	
			}else{
				bagProject+="+"+project_name;
			}
		}
		double remainTotalPrice=oldTotalPrice-newTotalPrice;
		modelAndView.addObject("remainTotalPrice", remainTotalPrice);
		modelAndView.addObject("bagProject", bagProject);
		modelAndView.addObject("bagProjectList", bagProjectList);
		modelAndView.addObject("bag",bag);
		modelAndView.setViewName("beauty/wechat/bag/showBagDetail.jsp");
		return modelAndView;
	}
	/**
	 * 
	 * 简要说明：判断是否抢过礼包
	 * 编写者：陈骑元
	 * 创建时间：2017年6月27日 上午12:59:55
	 * @param 说明
	 * @return 说明
	 */
	@RequestMapping(value = "checkRobBag")
	public void checkRobBag(String bag_id,HttpServletRequest request,
			HttpServletResponse response,HttpSession session){
		CustomUserPO customUserPO=IMSCxt.getCustomUserInfo(session);
		Dto outDto=wechatOrderService.checkRobBag(bag_id, customUserPO);
		String outString=IMSJson.toJson(outDto);
		IMSCxt.write(response, outString);
	}
	/**
	 * 
	 * 简要说明：抢礼包
	 * 编写者：陈骑元
	 * 创建时间：2017年6月11日 下午5:13:18
	 * @param 说明
	 * @return 说明
	 */
	@RequestMapping(value = "goRobBag")
	public ModelAndView goRobBag(String bag_id,HttpServletRequest request, HttpServletResponse response,HttpSession session){
		 ModelAndView modelAndView= new ModelAndView();
	     NurseBagPO bag=nurseBagService.selectByKey(bag_id);
	     int overdue_time=  bag.getOverdue_time();
	     String overdue_date=IMSUtils.getCurrentDate(IMSCons.DATE, overdue_time);
	     modelAndView.addObject("bag",bag);
	     modelAndView.addObject("overdue_date", overdue_date);
	     modelAndView.setViewName("beauty/wechat/bag/buyBag.jsp");
	     return modelAndView;
	
	}
	/**
	 * 
	 * 简要说明：跳转我的礼包
	 * 编写者：陈骑元
	 * 创建时间：2017年6月11日 下午5:13:18
	 * @param 说明
	 * @return 说明
	 */
	@RequestMapping(value = "goMyBag")
	public ModelAndView goMyBag(HttpServletRequest request, HttpServletResponse response,HttpSession session){
		ModelAndView modelAndView= new ModelAndView();
		modelAndView.setViewName("beauty/wechat/bag/myBag.jsp");
		return modelAndView;
		
	}
	/**
	 * 
	 * 简要说明：抢礼包
	 * 编写者：陈骑元
	 * 创建时间：2017年6月11日 下午5:13:18
	 * @param 说明
	 * @return 说明
	 */
	@RequestMapping(value = "listMyBag")
	public void listMyBag(HttpServletRequest request, HttpServletResponse response,HttpSession session){
		Dto pDto=Dtos.newDto();
		CustomUserPO customUserPO=IMSCxt.getCustomUserInfo(session);
		pDto.put("custom_user_id",customUserPO.getCustom_user_id());
		pDto.put("status", BeautyCons.VAILD_STATUS_YES);
		List<Dto> bagList=orderManageService.listMyBagDto(pDto);
		String outString=IMSJson.toGridJson(bagList,bagList.size(),IMSCons.DATE);
		IMSCxt.write(response, outString);
		
	}
	/**
	 * 
	 * 简要说明：自动抢礼包
	 * 编写者：陈骑元
	 * 创建时间：2017年6月11日 下午5:13:18
	 * @param 说明
	 * @return 说明
	 * @throws IOException 
	 */
	@RequestMapping(value = "autoReceiveBag")
	public void autoReceiveBag(String record_id,HttpServletRequest request, HttpServletResponse response,HttpSession session) throws IOException{
		CustomUserPO customUserPO=IMSCxt.getCustomUserInfo(session);
	    orderManageService.saveReceiveBag(record_id,customUserPO.getCustom_user_id());
	    String request_url = IMSCxt.getParamValue(BeautyCons.REQUEST_URL_KEY); // 系统请求的地址
	    response.sendRedirect(request_url+"/wechat/project/initMyProject.jhtml");
	}
	/**
	 * 
	 * 简要说明：抢礼包
	 * 编写者：陈骑元
	 * 创建时间：2017年6月11日 下午5:13:18
	 * @param 说明
	 * @return 说明
	 */
	@RequestMapping(value = "receiveBag")
	public void receiveBag(String record_id,HttpServletRequest request, HttpServletResponse response,HttpSession session){
		CustomUserPO customUserPO=IMSCxt.getCustomUserInfo(session);
		Dto outDto=orderManageService.saveReceiveBag(record_id,customUserPO.getCustom_user_id());
		String outString=IMSJson.toJson(outDto);
		IMSCxt.write(response, outString);
		
	}
	/**
	 * 
	 * 简要说明：获取微信配置信息
	 * 编写者：陈骑元
	 * 创建时间：2017年6月25日 下午9:19:01
	 * @param 说明
	 * @return 说明
	 */
	@RequestMapping(value = "getWechatConfig")
	public void getWechatConfig(String url,HttpServletRequest request, HttpServletResponse response){
		Dto outDto=WechatCxt.getJsApiConfig(url);
		String outString=IMSJson.toJson(outDto);
		System.out.println(outString);
		
		IMSCxt.write(response, outString);
		
	}
	
	


}
