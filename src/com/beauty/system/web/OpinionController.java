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
import com.beauty.common.po.OpinionPO;
import com.beauty.common.service.OpinionService;

/**
 * 
 * 类描述：<b>返回意见[bis_opinion]控制类</b>
 * 创建人：陈骑元
 * 邮箱：240823329@qq.com
 * 创建时间：2017-06-17 11:27:21
 * 修改人：
 * 修改时间：
 * 修改备注： 
 * @version 1.0
 */
@Controller
@RequestMapping(value = "system/opinion")
public class OpinionController {
  
    @Autowired
	private  OpinionService opinionService;
	
	
	/**
	 * 页面初始化
	 *
	 * @return
	 */
	@RequestMapping(value = "init")
	public ModelAndView init() {
		return new ModelAndView("beauty/system/opinion/opinionList.jsp");
	}
	
	/**
	 * 
	 * 分页查询返回意见信息
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "listOpinion")
	public void listOpinion(HttpServletRequest request, HttpServletResponse response) {
		Dto pDto = Dtos.newDto(request);
		pDto.setOrder(" a.create_time DESC ");
		List<Dto> opinionList =opinionService.listOpinionPage(pDto);
		String outString = IMSJson.toGridJson(opinionList, pDto.getPageTotal());
		IMSCxt.write(response, outString);
	}
	/**
	 * 
	 * 初始化新增返回意见页面
	 * @return
	 * 
	 */
	@RequestMapping(value = "goAdd")
	public ModelAndView goAdd() {
		return new ModelAndView("addOpinion.jsp");
	}
	/**
	 * 保存返回意见信息
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "saveOpinion")
	public void saveOpinion(HttpServletRequest request, HttpServletResponse response) {
	    Dto inDto = Dtos.newDto(request);
		Dto outDto = opinionService.save(inDto);
		IMSCxt.write(response, IMSJson.toJson(outDto));
	}
	/**
	 * 查看返回意见详情页面
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "goShow")
	public ModelAndView goShow( String opinion_id) {
	   ModelAndView modelAndView= new ModelAndView();
	   Dto opinionPO=opinionService.queryOpinionDetail(opinion_id);
	   modelAndView.addObject("opinionPO",opinionPO);
	   modelAndView.setViewName("beauty/system/opinion/showOpinion.jsp");
	   return modelAndView;
	}
	/**
	 * 初始化修改返回意见页面
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "goModify")
	public ModelAndView goModify( String opinion_id) {
	   ModelAndView modelAndView= new ModelAndView();
	   OpinionPO opinionPO=opinionService.selectByKey(opinion_id);
	   modelAndView.addObject("opinionPO",opinionPO);
	   modelAndView.setViewName("modifyOpinion.jsp");
	   return modelAndView;
	}
	/**
	 * 修改返回意见信息
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "updateOpinion")
	public void updateOpinion(HttpServletRequest request, HttpServletResponse response) {
		Dto inDto = Dtos.newDto(request);
		Dto outDto =opinionService.update(inDto);
		IMSCxt.write(response, IMSJson.toJson(outDto));
	}
	/**
	 * 删除返回意见信息
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "deleteOpinion")
	public void deleteOpinion( String opinion_id,HttpServletResponse response) {
		Dto outDto =opinionService.delete(opinion_id);
		IMSCxt.write(response, IMSJson.toJson(outDto));
	}
	/**
	 * 批量删除返回意见信息
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "batchDeleteOpinion")
	public void batchDeleteOpinion( String opinion_ids,HttpServletResponse response) {
	    List<String> opinion_idList=IMSFormater.separatStringToList(opinion_ids);
		Dto outDto =opinionService.batchDelete(opinion_idList);
		IMSCxt.write(response, IMSJson.toJson(outDto));
	}
	
}
