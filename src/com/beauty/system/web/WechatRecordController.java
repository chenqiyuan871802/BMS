package com.beauty.system.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.ims.common.core.asset.IMSJson;
import com.ims.common.core.asset.IMSCons;
import com.ims.common.core.asset.IMSCxt;
import com.ims.common.core.matatype.Dto;
import com.ims.common.core.matatype.Dtos;
import com.ims.common.core.asset.IMSFormater;
import com.beauty.common.constant.BeautyCons;
import com.beauty.common.po.CustomUserPO;
import com.beauty.common.po.WechatRecordPO;
import com.beauty.common.service.CustomUserService;
import com.beauty.common.service.SystemCommonService;
import com.beauty.common.service.WechatRecordService;

/**
 * 
 * 类描述：<b>微信记录表[bis_wechat_record]控制类</b>
 * 创建人：陈骑元
 * 邮箱：240823329@qq.com
 * 创建时间：2017-07-23 10:05:06
 * 修改人：
 * 修改时间：
 * 修改备注： 
 * @version 1.0
 */
@Controller
@RequestMapping(value = "system/wechatRecord")
public class WechatRecordController {
  
    @Autowired
	private  WechatRecordService wechatRecordService;
    @Autowired
    private  SystemCommonService systemCommonService;
    @Autowired
    private  CustomUserService customUserService;
	
	
	/**
	 * 页面初始化
	 *
	 * @return
	 */
	@RequestMapping(value = "init")
	public ModelAndView init() {
		return new ModelAndView("beauty/system/wechatRecord/wechatRecordList.jsp");
	}
	
	/**
	 * 
	 * 分页查询微信记录表信息
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "listWechatRecord")
	public void listWechatRecord(HttpServletRequest request, HttpServletResponse response) {
		Dto pDto = Dtos.newDto(request);
		pDto.setOrder(" a.create_time DESC ");
		List<Dto> wechatRecordList =systemCommonService.listWechatRecordPage(pDto);
		String outString = IMSJson.toGridJson(wechatRecordList, pDto.getPageTotal());
		IMSCxt.write(response, outString);
	}
	/**
	 * 
	 * 初始化新增微信记录表页面
	 * @return
	 * 
	 */
	@RequestMapping(value = "goAdd")
	public ModelAndView goAdd() {
		return new ModelAndView("beauty/system/wechatRecord/sendWechat.jsp");
	}
	/**
	 * 保存微信记录表信息
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "saveWechatRecord")
	public void saveWechatRecord(HttpServletRequest request, HttpServletResponse response) {
	    Dto inDto = Dtos.newDto(request);
		Dto outDto = wechatRecordService.save(inDto);
		IMSCxt.write(response, IMSJson.toJson(outDto));
	}
	/**
	 * 查看微信记录表详情页面
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "goShow")
	public ModelAndView goShow( String record_id) {
	   ModelAndView modelAndView= new ModelAndView();
	   WechatRecordPO wechatRecordPO=wechatRecordService.selectByKey(record_id);
	   modelAndView.addObject("wechatRecordPO",wechatRecordPO);
	   modelAndView.setViewName("showWechatRecord.jsp");
	   return modelAndView;
	}
	/**
	 * 初始化修改微信记录表页面
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "goModify")
	public ModelAndView goModify( String record_id) {
	   ModelAndView modelAndView= new ModelAndView();
	   WechatRecordPO wechatRecordPO=wechatRecordService.selectByKey(record_id);
	   modelAndView.addObject("wechatRecordPO",wechatRecordPO);
	   modelAndView.setViewName("modifyWechatRecord.jsp");
	   return modelAndView;
	}
	/**
	 * 修改微信记录表信息
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "updateWechatRecord")
	public void updateWechatRecord(HttpServletRequest request, HttpServletResponse response) {
		Dto inDto = Dtos.newDto(request);
		Dto outDto =wechatRecordService.update(inDto);
		IMSCxt.write(response, IMSJson.toJson(outDto));
	}
	/**
	 * 删除微信记录表信息
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "deleteWechatRecord")
	public void deleteWechatRecord( String record_id,HttpServletResponse response) {
		Dto outDto =wechatRecordService.delete(record_id);
		IMSCxt.write(response, IMSJson.toJson(outDto));
	}
	/**
	 * 批量删除微信记录表信息
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "batchDeleteWechatRecord")
	public void batchDeleteWechatRecord( String record_ids,HttpServletResponse response) {
	    List<String> record_idList=IMSFormater.separatStringToList(record_ids);
		Dto outDto =wechatRecordService.batchDelete(record_idList);
		IMSCxt.write(response, IMSJson.toJson(outDto));
	}
	/**
	 *  发送微信
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "sendWechat")
	public void sendWechat(HttpServletRequest request, HttpServletResponse response) {
	    Dto inDto = Dtos.newDto(request);
		Dto outDto = wechatRecordService.save(inDto);
		IMSCxt.write(response, IMSJson.toJson(outDto));
	}
	
}
