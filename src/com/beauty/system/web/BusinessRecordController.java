package com.beauty.system.web;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.beauty.common.constant.BeautyCons;
import com.beauty.common.po.NurseBagPO;
import com.beauty.common.po.NurseProjectPO;
import com.beauty.common.po.OrderDepositPO;
import com.beauty.common.po.ShopUserPO;
import com.beauty.common.service.NurseBagService;
import com.beauty.common.service.NurseProjectService;
import com.beauty.common.service.OrderDepositService;
import com.beauty.common.service.OrderManageService;
import com.beauty.common.service.ShopCommonService;
import com.beauty.common.utils.ExcelUtil;
import com.ims.common.core.asset.FileOperation;
import com.ims.common.core.asset.IMSCons;
import com.ims.common.core.asset.IMSCxt;
import com.ims.common.core.asset.IMSJson;
import com.ims.common.core.asset.IMSUtils;
import com.ims.common.core.id.IMSId;
import com.ims.common.core.matatype.Dto;
import com.ims.common.core.matatype.Dtos;

/**
 * 
 * 类名:com.beauty.system.web.BusinessRecordController 描述:营业记录 编写者:陈骑元
 * 创建时间:2017年5月30日 下午4:48:15 修改说明:
 */
@Controller
@RequestMapping("system/business")
public class BusinessRecordController{

	@Autowired
	private OrderManageService orderManageService;
	// 定金信息
	@Autowired
	private OrderDepositService orderDepositService;
	@Autowired
	private NurseProjectService nurseProjectService; // 护理项目
	@Autowired
	private NurseBagService nurseBagService; // 护理项目
	@Autowired
	private ShopCommonService shopCommonService;

	/**
	 * 
	 * 简要说明：营业记录初始化 编写者：陈骑元 创建时间：2017年5月8日 上午1:47:25
	 * 
	 * @param 说明
	 * @return 说明
	 */
	@RequestMapping(value = "init")
	public ModelAndView init(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("beauty/system/business/businessRecordList.jsp");
		return modelAndView;
	}
	/**
	 * 
	 * 简要说明：营业记录初始化 编写者：陈骑元 创建时间：2017年5月8日 上午1:47:25
	 * 
	 * @param 说明
	 * @return 说明
	 */
	@RequestMapping(value = "initRecord")
	public ModelAndView initRecord(String shop_id,HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("shop_id", shop_id);
		modelAndView.setViewName("beauty/system/business/showBusinessRecordList.jsp");
		return modelAndView;
	}

	/**
	 * 
	 * 简要说明：分页查询营业记录 编写者：陈骑元 创建时间：2017年5月8日 上午1:47:25
	 * 
	 * @param 说明
	 * @return 说明
	 */
	@RequestMapping(value = "listAllRecord")
	public void listAllRecord(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		Dto pDto = Dtos.newDto(request);
		pDto.setOrder(" a.finish_time DESC ");
		List<Dto> businessRecordList = orderManageService.listAllRecordPage(pDto);
		String outString = IMSJson.toGridJson(businessRecordList, pDto.getPageTotal(), IMSCons.DATETIMEMIN);
		IMSCxt.write(response, outString);
	}
	/**
	 * 
	 * 简要说明：分页查询营业记录 编写者：陈骑元 创建时间：2017年5月8日 上午1:47:25
	 * 
	 * @param 说明
	 * @return 说明
	 */
	@RequestMapping(value = "listNoServerRecord")
	public void listNoServerRecord(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		Dto pDto = Dtos.newDto(request);
		pDto.setOrder(" a.create_time DESC ");
		List<Dto> businessRecordList = orderManageService.listNoServerRecordPage(pDto);
		String outString = IMSJson.toGridJson(businessRecordList, pDto.getPageTotal(), IMSCons.DATETIMEMIN);
		IMSCxt.write(response, outString);
	}
	/**
	 * 
	 * 简要说明：初始化消费记录
	 * 编写者：陈骑元
	 * 创建时间：2017年7月23日 上午8:59:47
	 * @param 说明
	 * @return 说明
	 */
	@RequestMapping(value = "initCustomRecord")
	public ModelAndView initCustomRecord(String custom_user_id,HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("custom_user_id", custom_user_id);
		modelAndView.setViewName("beauty/system/business/showCustomRecordList.jsp");
		return modelAndView;
	}
	/**
	 * 
	 * 简要说明：初始化消费记录
	 * 编写者：陈骑元
	 * 创建时间：2017年7月23日 上午8:59:47
	 * @param 说明
	 * @return 说明
	 */
	@RequestMapping(value = "initNoServerRecord")
	public ModelAndView initNoServerRecord(String custom_user_id,HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("custom_user_id", custom_user_id);
		modelAndView.setViewName("beauty/system/business/showNoServerRecordList.jsp");
		return modelAndView;
	}
	/**
	 * 
	 * 简要说明：分页查询营业记录 编写者：陈骑元 创建时间：2017年5月8日 上午1:47:25
	 * 
	 * @param 说明
	 * @return 说明
	 */
	@RequestMapping(value = "listShopRecord")
	public void listShopRecord(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		Dto pDto = Dtos.newDto(request);
		String time_type=pDto.getString("time_type");
		if(IMSUtils.isNotEmpty(time_type)){
			int month=Integer.parseInt(time_type);
			String create_time_begin=IMSUtils.getMonthFisrtDay(IMSCons.DATE, month);
			String create_time_end=IMSUtils.getLastMonthLastDay(IMSCons.DATE);
			pDto.put("pay_time_begin", create_time_begin);
			pDto.put("pay_time_end", create_time_end);
		}
		pDto.setOrder(" a.pay_time DESC ");
		List<Dto> businessRecordList = orderManageService.listShopRecordPage(pDto);
		String outString = IMSJson.toGridJson(businessRecordList, pDto.getPageTotal(), IMSCons.DATETIMEMIN);
		IMSCxt.write(response, outString);
	}

	/**
	 * 
	 * 简要说明:查看订单详情信息 编写者：陈骑元 创建时间：2017年5月8日 上午1:47:25
	 * 
	 * @param 说明
	 * @return 说明
	 */
	@RequestMapping(value = "showOrderDetail")
	public ModelAndView showOrderDetail(String order_id, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		Dto orderDto = orderManageService.queryOrderDetail(order_id);
		modelAndView.addObject("orderDto", orderDto);
		String order_type = orderDto.getString("order_type");
		String order_status = orderDto.getString("order_status");
		String project_id = orderDto.getString("project_id");
		if (BeautyCons.ORDER_TYPE_PROJECT.equals(order_type)) {
			
			Dto pDto = Dtos.newDto("order_id", order_id);
			OrderDepositPO orderDepositPO = orderDepositService.selectOne(pDto);
			NurseProjectPO nurseProjectPO = nurseProjectService.selectByKey(project_id);
			if (BeautyCons.ORDER_STATUS_SUBSCRIBE.equals(order_status)) { // 已预约
				modelAndView.setViewName("beauty/system/business/showOrderSubscribeDetail.jsp");
			} else if (BeautyCons.ORDER_STATUS_SERVER.equals(order_status)) {
				modelAndView.setViewName("beauty/system/business/showOrderServerDetail.jsp");
			} else if (BeautyCons.ORDER_STATUS_PAY.equals(order_status)) {
				modelAndView.setViewName("beauty/system/business/showOrderPayDetail.jsp");
			} else {
				modelAndView.setViewName("beauty/system/business/showOrderCompleteDetail.jsp");
			}
			modelAndView.addObject("orderDepositPO", orderDepositPO);
			modelAndView.addObject("nurseProjectPO", nurseProjectPO);
		} else if (BeautyCons.ORDER_TYPE_BEAUTY.equals(order_type)) {
			
			modelAndView.setViewName("beauty/system/business/showBeautyOrderDetail.jsp");
		} else {
			NurseBagPO  nurseBag=nurseBagService.selectByKey(project_id);
			List<Dto>  bagProjectList=shopCommonService.listBagProject(project_id);
			
			String bagProject="";
			for(int i=0;i<bagProjectList.size();i++){
				Dto project=bagProjectList.get(i);
				String project_name=project.getString("project_name");
				
				if(i==0){
					bagProject+=project_name;	
				}else{
					bagProject+="+"+project_name;
				}
			}
			modelAndView.addObject("bagProject", bagProject);
			modelAndView.addObject("nurseBag", nurseBag);
			modelAndView.setViewName("beauty/system/business/showBagOrderDetail.jsp");
		}

		return modelAndView;
	}
	/**
	 * 
	 * 简要说明：下载财务总况表
	 * 编写者：陈骑元
	 * 创建时间：2017年6月15日 上午12:37:54
	 * @param 说明
	 * @return 说明
	 */
	@RequestMapping(value = "downloadSystemFinance")
	public void downloadSystemFinance(HttpServletRequest request, HttpServletResponse response,HttpSession session) {
		String folderPath = session.getServletContext().getRealPath("/excelData/");
		FileOperation.createFolder(folderPath);
		String outExcelPath = folderPath + File.separator + IMSId.uuid()+ ".xls";
		String fileName = "营业记录报表（"+IMSUtils.getCurrentDate("yyyyMMdd")+"）.xls";
		Dto pDto = Dtos.newDto(request);
		pDto.setOrder(" a.finish_time DESC ");
		List<Dto> financeList =orderManageService.listAllRecord(pDto);
		
		boolean flag = ExcelUtil.createSystemFinanceExcel(financeList, outExcelPath);
		if(flag) {
			FileOperation.downloadFile(request,response,outExcelPath, fileName);
		}
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
		String fileName = "营业记录报表（"+IMSUtils.getCurrentDate("yyyyMMdd")+"）.xls";
		Dto pDto = Dtos.newDto(request);
		pDto.setOrder(" a.pay_time DESC ");
		List<Dto> financeList =orderManageService.listShopRecord(pDto);
		
		boolean flag = ExcelUtil.createSystemFinanceExcel(financeList, outExcelPath);
		if(flag) {
			FileOperation.downloadFile(request,response,outExcelPath, fileName);
		}
	}
}
