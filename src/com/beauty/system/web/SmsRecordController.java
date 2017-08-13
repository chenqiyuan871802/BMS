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
import com.ims.common.core.asset.IMSFormater;
import com.beauty.common.po.SmsRecordPO;
import com.beauty.common.service.SmsRecordService;
import com.beauty.common.service.SystemCommonService;

/**
 * 
 * 类描述：<b>短信记录[bis_sms_record]控制类</b>
 * 创建人：陈骑元
 * 邮箱：240823329@qq.com
 * 创建时间：2017-05-12 00:26:48
 * 修改人：
 * 修改时间：
 * 修改备注： 
 * @version 1.0
 */
@Controller
@RequestMapping(value = "system/smsRecord")
public class SmsRecordController {
  
    @Autowired
	private  SmsRecordService smsRecordService;
    @Autowired
    private  SystemCommonService systemCommonService;
	
	
	/**
	 * 页面初始化
	 *
	 * @return
	 */
	@RequestMapping(value = "init")
	public ModelAndView init() {
		return new ModelAndView("beauty/system/smsRecord/smsRecordList.jsp");
	}
	
	/**
	 * 
	 * 分页查询短信记录信息
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "listSmsRecord")
	public void listSmsRecord(HttpServletRequest request, HttpServletResponse response) {
		Dto pDto = Dtos.newDto(request);
		pDto.setOrder(" a.create_time DESC ");
		List<Dto> smsRecordList =systemCommonService.listSmsRecordPage(pDto);
		String outString = IMSJson.toGridJson(smsRecordList, pDto.getPageTotal());
		IMSCxt.write(response, outString);
	}
	/**
	 * 
	 * 跳到短信发送界面
	 * @return
	 * 
	 */
	@RequestMapping(value = "goSend")
	public ModelAndView goSend() {
		return new ModelAndView("beauty/system/smsRecord/sendSms.jsp");
	}
	
	/**
	 *  发送短信
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "sendSms")
	public void sendSms(HttpServletRequest request, HttpServletResponse response) {
	    Dto inDto = Dtos.newDto(request);
		Dto outDto = smsRecordService.save(inDto);
		IMSCxt.write(response, IMSJson.toJson(outDto));
	}
	/**
	 * 查看短信记录详情页面
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "goShow")
	public ModelAndView goShow( String record_id) {
	   ModelAndView modelAndView= new ModelAndView();
	   SmsRecordPO smsRecordPO=smsRecordService.selectByKey(record_id);
	   modelAndView.addObject("smsRecordPO",smsRecordPO);
	   modelAndView.setViewName("showSmsRecord.jsp");
	   return modelAndView;
	}
	/**
	 * 初始化修改短信记录页面
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "goModify")
	public ModelAndView goModify( String record_id) {
	   ModelAndView modelAndView= new ModelAndView();
	   SmsRecordPO smsRecordPO=smsRecordService.selectByKey(record_id);
	   modelAndView.addObject("smsRecordPO",smsRecordPO);
	   modelAndView.setViewName("modifySmsRecord.jsp");
	   return modelAndView;
	}
	/**
	 * 修改短信记录信息
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "updateSmsRecord")
	public void updateSmsRecord(HttpServletRequest request, HttpServletResponse response) {
		Dto inDto = Dtos.newDto(request);
		Dto outDto =smsRecordService.update(inDto);
		IMSCxt.write(response, IMSJson.toJson(outDto));
	}
	/**
	 * 删除短信记录信息
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "deleteSmsRecord")
	public void deleteSmsRecord( String record_id,HttpServletResponse response) {
		Dto outDto =smsRecordService.delete(record_id);
		IMSCxt.write(response, IMSJson.toJson(outDto));
	}
	/**
	 * 批量删除短信记录信息
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "batchDeleteSmsRecord")
	public void batchDeleteSmsRecord( String record_ids,HttpServletResponse response) {
	    List<String> record_idList=IMSFormater.separatStringToList(record_ids);
		Dto outDto =smsRecordService.batchDelete(record_idList);
		IMSCxt.write(response, IMSJson.toJson(outDto));
	}
	
}
