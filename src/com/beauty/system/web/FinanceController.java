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

import com.beauty.common.service.FinanceService;
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
 * 类名:com.beauty.system.web.FinanceController
 * 描述:财务管理业务逻辑处理
 * 编写者:陈骑元
 * 创建时间:2017年5月29日 下午11:10:00
 * 修改说明:
 */
@Controller
@RequestMapping(value = "system/finance")
public class FinanceController {
	
	@Autowired
	private FinanceService financeService;
	
	/**
	 * 页面初始化现金流水记录
	 *
	 * @return
	 */
	@RequestMapping(value = "initCashRecord")
	public ModelAndView initCashRecord() {
		return new ModelAndView("beauty/system/finance/cashRecordList.jsp");
	}
	/**
	 * 
	 * 简要说明：
	 * 编写者：陈骑元
	 * 创建时间：2017年5月29日 下午11:27:34
	 * @param 说明
	 * @return 说明
	 */
	@RequestMapping(value = "listCashRecord")
	public void listCashRecord(HttpServletRequest request, HttpServletResponse response) {
		Dto pDto = Dtos.newDto(request);
		pDto.setOrder("  a.pay_time DESC ");
		List<Dto> cashRecordList =financeService.listCashRecordPage(pDto);
		String outString = IMSJson.toGridJson(cashRecordList, pDto.getPageTotal(),IMSCons.DATETIME);
		IMSCxt.write(response, outString);
	}
	
	/**
	 * 
	 * 简要说明：下载现金流水
	 * 编写者：陈骑元
	 * 创建时间：2017年6月15日 上午12:37:54
	 * @param 说明
	 * @return 说明
	 */
	@RequestMapping(value = "downloadCashRecord")
	public void downloadCashRecord(HttpServletRequest request, HttpServletResponse response,HttpSession session) {
		String folderPath = session.getServletContext().getRealPath("/excelData/");
		FileOperation.createFolder(folderPath);
		String outExcelPath = folderPath + File.separator + IMSId.uuid()+ ".xls";
		String fileName = "现金流水报表（"+IMSUtils.getCurrentDate("yyyyMMdd")+"）.xls";
		Dto pDto = Dtos.newDto(request);
		pDto.setOrder("  a.pay_time DESC ");
		List<Dto> recordList =financeService.listCashRecord(pDto);
		
		boolean flag = ExcelUtil.createCashRecordExcel(recordList, outExcelPath);
		if(flag) {
			FileOperation.downloadFile(request,response,outExcelPath, fileName);
		}
	}
	/**
	 * 页面初始化美丽币流水记录
	 *
	 * @return
	 */
	@RequestMapping(value = "initBeautyRecord")
	public ModelAndView initBeautyRecord() {
		return new ModelAndView("beauty/system/finance/beautyRecordList.jsp");
	}
	/**
	 * 
	 * 简要说明：初始化美丽币流水
	 * 编写者：陈骑元
	 * 创建时间：2017年5月29日 下午11:27:34
	 * @param 说明
	 * @return 说明
	 */
	@RequestMapping(value = "listBeautyRecord")
	public void listBeautyRecord(HttpServletRequest request, HttpServletResponse response) {
		Dto pDto = Dtos.newDto(request);
		pDto.setOrder("  a.pay_time DESC ");
		List<Dto> beautyRecordList =financeService.listBeautyRecordPage(pDto);
		String outString = IMSJson.toGridJson(beautyRecordList, pDto.getPageTotal(),IMSCons.DATETIME);
		IMSCxt.write(response, outString);
	}
	/**
	 * 
	 * 简要说明：下载美研币流水
	 * 编写者：陈骑元
	 * 创建时间：2017年6月15日 上午12:37:54
	 * @param 说明
	 * @return 说明
	 */
	@RequestMapping(value = "downloadBeautyRecord")
	public void downloadBeautyRecord(HttpServletRequest request, HttpServletResponse response,HttpSession session) {
		String folderPath = session.getServletContext().getRealPath("/excelData/");
		FileOperation.createFolder(folderPath);
		String outExcelPath = folderPath + File.separator + IMSId.uuid()+ ".xls";
		String fileName = "颜值流水报表（"+IMSUtils.getCurrentDate("yyyyMMdd")+"）.xls";
		Dto pDto = Dtos.newDto(request);
		pDto.setOrder("  a.pay_time DESC ");
		List<Dto> recordList =financeService.listBeautyRecord(pDto);
		
		boolean flag = ExcelUtil.createBeautyRecordExcel(recordList, outExcelPath);
		if(flag) {
			FileOperation.downloadFile(request,response,outExcelPath, fileName);
		}
	}
	/**
	 * 页面初始化分享记录
	 *
	 * @return
	 */
	@RequestMapping(value = "initShareBagRecord")
	public ModelAndView initShareBagRecord() {
		return new ModelAndView("beauty/system/finance/shareBagRecordList.jsp");
	}
	/**
	 * 
	 * 简要说明：初始化美丽币流水
	 * 编写者：陈骑元
	 * 创建时间：2017年5月29日 下午11:27:34
	 * @param 说明
	 * @return 说明
	 */
	@RequestMapping(value = "listShareBagRecord")
	public void listShareBagRecord(HttpServletRequest request, HttpServletResponse response) {
		Dto pDto = Dtos.newDto(request);
		pDto.setOrder("  a.bag_time DESC ");
		List<Dto> shareBagRecordList =financeService.listShareBagPage(pDto);
		String outString = IMSJson.toGridJson(shareBagRecordList, pDto.getPageTotal(),IMSCons.DATETIME);
		IMSCxt.write(response, outString);
	}
	/**
	 * 
	 * 简要说明：下载现金流水
	 * 编写者：陈骑元
	 * 创建时间：2017年6月15日 上午12:37:54
	 * @param 说明
	 * @return 说明
	 */
	@RequestMapping(value = "downloadShareBagRecord")
	public void downloadShareBagRecord(HttpServletRequest request, HttpServletResponse response,HttpSession session) {
		String folderPath = session.getServletContext().getRealPath("/excelData/");
		FileOperation.createFolder(folderPath);
		String outExcelPath = folderPath + File.separator + IMSId.uuid()+ ".xls";
		String fileName = "礼包分享记录报表（"+IMSUtils.getCurrentDate("yyyyMMdd")+"）.xls";
		Dto pDto = Dtos.newDto(request);
		pDto.setOrder("  a.bag_time DESC ");
		List<Dto> recordList =financeService.listShareBag(pDto);
		
		boolean flag = ExcelUtil.createShareBagRecordExcel(recordList, outExcelPath);
		if(flag) {
			FileOperation.downloadFile(request,response,outExcelPath, fileName);
		}
	}
	/**
	 * 
	 * 简要说明：初始化平台监控
	 * 编写者：陈骑元
	 * 创建时间：2017年7月6日 下午10:08:26
	 * @param 说明
	 * @return 说明
	 */
	@RequestMapping(value = "initPlatform")
	public ModelAndView  initPlatform(HttpServletRequest request, HttpServletResponse response){
		ModelAndView modelAndView=new ModelAndView();
		String month_first_day=IMSUtils.getCurrentMonthFisrtDay(IMSCons.DATE);
		String current_day=IMSUtils.getCurrentDate();
		String total_month=IMSUtils.getCurrentDate("yyyy-MM");
		modelAndView.addObject("month_first_day", month_first_day);
		modelAndView.addObject("current_day",  current_day);
		
		modelAndView.addObject("total_month", total_month);
		modelAndView.setViewName("beauty/system/finance/platform.jsp");
		return modelAndView;
	}
	/**
	 * 
	 * 简要说明：查询平台统计
	 * 编写者：陈骑元
	 * 创建时间：2017年7月7日 上午12:11:10
	 * @param 说明
	 * @return 说明
	 */
	@RequestMapping(value = "queryPlatformSum")
	public void queryPlatformSum(HttpServletRequest request, HttpServletResponse response){
		Dto pDto=Dtos.newDto(request);
		String time_type=pDto.getString("time_type");
		if(IMSUtils.isNotEmpty(time_type)){
			int month=Integer.parseInt(time_type);
			String create_time_begin=IMSUtils.getMonthFisrtDay(IMSCons.DATE, month);
			String create_time_end=IMSUtils.getLastMonthLastDay(IMSCons.DATE);
			pDto.put("create_time_begin", create_time_begin);
			pDto.put("create_time_end", create_time_end);
		}
		Dto dataDto=Dtos.newDto();
		Dto cashDto=financeService.queryCashGroup(pDto);
		Dto extendDto=financeService.queryExtendGroup(pDto);
		dataDto.putAll(cashDto);
		dataDto.putAll(extendDto);
		String outString=IMSJson.toJson(dataDto);
		IMSCxt.writeRaw(response, outString);
	}
	
	
	/**
	 * 
	 * 简要说明：初始化平台监控
	 * 编写者：陈骑元
	 * 创建时间：2017年7月6日 下午10:08:26
	 * @param 说明
	 * @return 说明
	 */
	@RequestMapping(value = "initShowPlatform")
	public ModelAndView  initShowPlatform(String shop_id,HttpServletRequest request, HttpServletResponse response){
		ModelAndView modelAndView=new ModelAndView();
		String month_first_day=IMSUtils.getCurrentMonthFisrtDay(IMSCons.DATE);
		String current_day=IMSUtils.getCurrentDate();
		modelAndView.addObject("month_first_day", month_first_day);
		modelAndView.addObject("current_day",  current_day);
		modelAndView.addObject("shop_id",shop_id);
		modelAndView.setViewName("beauty/system/finance/showPlatform.jsp");
		return modelAndView;
	}
	
	/**
	 * 
	 * 简要说明：初始化美丽币流水
	 * 编写者：陈骑元
	 * 创建时间：2017年5月29日 下午11:27:34
	 * @param 说明
	 * @return 说明
	 */
	@RequestMapping(value = "listPlatformSum")
	public void listPlatformSum(HttpServletRequest request, HttpServletResponse response) {
		Dto pDto = Dtos.newDto(request);
		List<Dto> dataList =financeService.listPlatformSum(pDto);
		String outString = IMSJson.toGridJson(dataList,dataList.size(),IMSCons.DATETIME);
		IMSCxt.write(response, outString);
	}
	/**
	 * 
	 * 简要说明：下载财务总控
	 * 编写者：陈骑元
	 * 创建时间：2017年6月15日 上午12:37:54
	 * @param 说明
	 * @return 说明
	 */
	@RequestMapping(value = "downloadPlatformSum")
	public void downloadPlatformSum(HttpServletRequest request, HttpServletResponse response,HttpSession session) {
		String folderPath = session.getServletContext().getRealPath("/excelData/");
		FileOperation.createFolder(folderPath);
		String outExcelPath = folderPath + File.separator + IMSId.uuid()+ ".xls";
		String fileName = "平台财务总控报表（"+IMSUtils.getCurrentDate("yyyyMMdd")+"）.xls";
		Dto pDto = Dtos.newDto(request);
		
		List<Dto> recordList =financeService.listPlatformSum(pDto);
		
		boolean flag = ExcelUtil.createPlatformSumExcel(recordList, outExcelPath);
		if(flag) {
			FileOperation.downloadFile(request,response,outExcelPath, fileName);
		}
	}
}
