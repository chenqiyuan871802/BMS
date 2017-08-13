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
import com.beauty.common.po.NurseProjectPO;
import com.beauty.common.po.ShopPO;
import com.beauty.common.service.NurseProjectService;
import com.beauty.common.service.OrderManageService;
import com.beauty.common.service.ShopCommonService;
import com.beauty.common.service.ShopService;
import com.ims.common.core.asset.IMSCons;
import com.ims.common.core.asset.IMSCxt;
import com.ims.common.core.asset.IMSJson;
import com.ims.common.core.asset.IMSUtils;
import com.ims.common.core.matatype.Dto;
import com.ims.common.core.matatype.Dtos;

/**
 * 
 * 类名:com.beauty.wechat.web.WechatProjectController
 * 描述:微信项目相关处理
 * 编写者:陈骑元
 * 创建时间:2017年5月19日 下午11:11:52
 * 修改说明:
 */
@Controller
@RequestMapping("wechat/project")
public class WechatProjectController{
	
	@Autowired
	private NurseProjectService nurseProjectService;
	
	@Autowired
	private  ShopCommonService shopCommonService;
	
	@Autowired
	private ShopService shopService;
	@Autowired
	private OrderManageService orderManageService;
	/**
	 * 查找项目
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "findProject")
	public  ModelAndView findPorject(String code,HttpServletRequest request, HttpServletResponse response,HttpSession session) throws IOException {
		ModelAndView modelAndView= new ModelAndView();
		Dto pDto=Dtos.newDto();
		pDto.put("status", BeautyCons.SHOW_STATUS_ON);
		pDto.setOrder(" b.sort_no ASC ,a.sort_no ASC  ");
		List<NurseProjectPO> projectList=shopCommonService.listNurseProject(pDto);
		modelAndView.addObject("projectList", projectList);
	    modelAndView.setViewName("beauty/wechat/project/findProject.jsp");
		return modelAndView;
	}
	/**
	 * 查找项目
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "showProjectDetail")
	public  ModelAndView showProjectDetail(String project_id,HttpServletRequest request, HttpServletResponse response,HttpSession session) throws IOException {
		ModelAndView modelAndView= new ModelAndView();
		NurseProjectPO nurseProjectPO=nurseProjectService.selectByKey(project_id);
		modelAndView.addObject("project", nurseProjectPO);
		modelAndView.setViewName("beauty/wechat/project/showProjectDetail.jsp");
		return modelAndView;
	}
	
	/**
	 * 查找项目
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "showProjectDetailMenu")
	public  ModelAndView showProjectDetailMenu(HttpServletRequest request, HttpServletResponse response,HttpSession session) throws IOException {
		Dto pDto=Dtos.newDto(request);
		String paramKey=pDto.keySet().toString();
		String project_id=IMSUtils.getNumber(paramKey);
		ModelAndView modelAndView= new ModelAndView();
		NurseProjectPO nurseProjectPO=nurseProjectService.selectByKey(project_id);
		modelAndView.addObject("project", nurseProjectPO);
		modelAndView.setViewName("beauty/wechat/project/showProjectDetail.jsp");
		return modelAndView;
	}
	
	/**
	 * 
	 * 简要说明：调到预约项目
	 * 编写者：陈骑元
	 * 创建时间：2017年5月21日 下午10:43:39
	 * @param 说明
	 * @return 说明
	 */
	@RequestMapping(value = "goSubscribeProject")
	public ModelAndView goSubscribeProject(String project_id,String shop_id,HttpServletRequest request, HttpServletResponse response,HttpSession session){
		ModelAndView modelAndView= new ModelAndView();
		CustomUserPO customUserPO=IMSCxt.getCustomUserInfo(session);
		NurseProjectPO nurseProjectPO=nurseProjectService.selectByKey(project_id);
		ShopPO shopPO=shopService.selectByKey(shop_id);
		String startDate=IMSUtils.getCurrentDate(IMSCons.DATE, 1);;
		String endDate=IMSUtils.getCurrentDate(IMSCons.DATE, 7);
		modelAndView.addObject("startDate", startDate);
		modelAndView.addObject("endDate", endDate);
		modelAndView.addObject("customUserPO", customUserPO);
		modelAndView.addObject("project", nurseProjectPO);
		modelAndView.addObject("shopPO", shopPO);
		modelAndView.setViewName("beauty/wechat/project/subscribeProject.jsp");
		return modelAndView;
	}
	
	/**
	 * 查找项目  初始化我的项目
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "initMyProject")
	public  ModelAndView initMyProject(HttpServletRequest request, HttpServletResponse response,HttpSession session) throws IOException {
		ModelAndView modelAndView= new ModelAndView();
		modelAndView.setViewName("beauty/wechat/project/myProject.jsp");
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
	@RequestMapping(value = "listMyProject")
	public void listMyProject(HttpServletRequest request, HttpServletResponse response,HttpSession session){
		CustomUserPO customUserPO=IMSCxt.getCustomUserInfo(session);
		Dto pDto =Dtos.newDto(request);
		pDto.put("custom_user_id", customUserPO.getCustom_user_id());
		pDto.setOrder( " ab.draw_time DESC ");
		List<Dto> porjectList=orderManageService.listProject(pDto);
		String outString = IMSJson.toGridJson(porjectList, pDto.getPageTotal(),IMSCons.DATE);
		IMSCxt.write(response, outString);
	}


}
