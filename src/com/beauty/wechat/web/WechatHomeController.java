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
import com.beauty.common.po.ShopPO;
import com.beauty.common.service.BeautyConfigService;
import com.beauty.common.service.CustomUserService;
import com.beauty.common.service.OpinionService;
import com.beauty.common.service.RecordCommonService;
import com.beauty.wechat.util.WechatCxt;
import com.ims.common.core.asset.IMSCons;
import com.ims.common.core.asset.IMSCxt;
import com.ims.common.core.asset.IMSJson;
import com.ims.common.core.asset.IMSUtils;
import com.ims.common.core.id.IMSId;
import com.ims.common.core.matatype.Dto;
import com.ims.common.core.matatype.Dtos;

@Controller
@RequestMapping("wechat/home")
public class WechatHomeController {
	
	@Autowired
	private RecordCommonService recordCommonService;
	@Autowired
	private CustomUserService customUserService;
	@Autowired
	private OpinionService opinionService;
	
	
	/**
	 * 跳到我的页面
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "goHome")
	public  ModelAndView goLogin(HttpServletRequest request, HttpServletResponse response,HttpSession session) throws IOException {
		ModelAndView modelAndView= new ModelAndView();
		CustomUserPO customUserPO=IMSCxt.getCustomUserInfo(session);
		CustomUserPO customUserPONew=customUserService.selectByKey(customUserPO.getCustom_user_id());
		modelAndView.addObject("customUserPO", customUserPONew);
	    modelAndView.setViewName("beauty/wechat/my/home.jsp");
		return modelAndView;
	}
	/**
	 * 
	 * 简要说明：关于我们页面跳转
	 * 编写者：陈骑元
	 * 创建时间：2017年5月21日 下午10:43:39
	 * @param 说明
	 * @return 说明
	 */
	@RequestMapping(value = "goAboutMy")
	public ModelAndView goAboutMy(HttpServletRequest request, HttpServletResponse response){
		ModelAndView modelAndView= new ModelAndView();
	    modelAndView.setViewName("beauty/wechat/my/aboutMy.jsp");
		return modelAndView;
	}
	/**
	 * 
	 * 简要说明：关于我们页面跳转
	 * 编写者：陈骑元
	 * 创建时间：2017年5月21日 下午10:43:39
	 * @param 说明
	 * @return 说明
	 */
	@RequestMapping(value = "goMyProject")
	public ModelAndView goMyProject(HttpServletRequest request, HttpServletResponse response){
		ModelAndView modelAndView= new ModelAndView();
		modelAndView.setViewName("beauty/wechat/my/myUnusedProject.jsp");
		return modelAndView;
	}
	/**
	 * 
	 * 简要说明：跳转到个人设置
	 * 编写者：陈骑元
	 * 创建时间：2017年5月21日 下午10:43:39
	 * @param 说明
	 * @return 说明
	 */
	@RequestMapping(value = "goMyDetail")
	public ModelAndView goMyDetail(HttpServletRequest request, HttpServletResponse response,HttpSession session){
		ModelAndView modelAndView= new ModelAndView();
		CustomUserPO customUserPO=IMSCxt.getCustomUserInfo(session);
		CustomUserPO customUserPONew=customUserService.selectByKey(customUserPO.getCustom_user_id());
		modelAndView.addObject("customUserPO", customUserPONew);
	    modelAndView.setViewName("beauty/wechat/my/myDetail.jsp");
		return modelAndView;
	}
	
	/**
	 * 
	 * 简要说明：更新个人资料
	 * 编写者：陈骑元
	 * 创建时间：2017年5月21日 下午10:43:39
	 * @param 说明
	 * @return 说明
	 */
	@RequestMapping(value = "updateMyInfo")
	public void updateMyInfo(HttpServletRequest request, HttpServletResponse response,HttpSession session){
		Dto inDto=Dtos.newDto(request);
	    CustomUserPO customUserPO =new  CustomUserPO();
		IMSUtils.copyProperties(inDto,  customUserPO); 
		int row=customUserService.updateByKey(customUserPO);
		Dto outDto=Dtos.newDto();
		if(row>0){
			   outDto.setAppCode(IMSCons.SUCCESS);
			   outDto.setAppMsg("个人资料修改成功。");
		   }else{
			   outDto.setAppCode(IMSCons.ERROR);
			   outDto.setAppMsg("个人资料修改失败。");
		   }
		String outString=IMSJson.toJson(outDto);
		IMSCxt.write(response, outString);
	}
	/**
	 * 
	 * 简要说明：更新个人头像
	 * 编写者：陈骑元
	 * 创建时间：2017年5月21日 下午10:43:39
	 * @param 说明
	 * @return 说明
	 */
	@RequestMapping(value = "updateMyImg")
	public void updateMyImg(String media_id,String custom_user_id,HttpServletRequest request, HttpServletResponse response){
		Dto outDto=Dtos.newDto();
		String filePath = request.getSession().getServletContext().getRealPath(BeautyCons.CUSTOM_IMAGE_URL);
		String fileId = custom_user_id;
		String fileName=WechatCxt.downloadMedia(media_id, filePath, fileId);
		if(IMSUtils.isNotEmpty(fileName)){
		  String savePath =BeautyCons.CUSTOM_IMAGE_URL +fileName;
		  CustomUserPO customUserPO =new  CustomUserPO();
		  customUserPO.setCustom_user_id(custom_user_id);
		  customUserPO.setPhoto(savePath);
		  int row=customUserService.updateByKey(customUserPO);
			if(row>0){
				outDto.setAppCode(IMSCons.SUCCESS);
				outDto.put("savePath",savePath);
				outDto.setAppMsg("个人头像上传成功。");
			}else{
				outDto.setAppCode(IMSCons.ERROR);
				outDto.setAppMsg("个人头像上传失败。");
			}
		}else{
			outDto.setAppCode(IMSCons.ERROR);
			outDto.setAppMsg("个人头像上传失败。");
		}
			
		
		
		String outString=IMSJson.toJson(outDto);
		IMSCxt.write(response, outString);
	}
	/**
	 * 
	 * 简要说明：跳转我的颜值
	 * 编写者：陈骑元
	 * 创建时间：2017年5月21日 下午10:43:39
	 * @param 说明
	 * @return 说明
	 */
	@RequestMapping(value = "goMyBeauty")
	public ModelAndView goMyBeauty(HttpServletRequest request, HttpServletResponse response,HttpSession session){
		ModelAndView modelAndView= new ModelAndView();
		CustomUserPO customUserPO=IMSCxt.getCustomUserInfo(session);
		CustomUserPO customUserPONew=customUserService.selectByKey(customUserPO.getCustom_user_id());
		modelAndView.addObject("customUserPO", customUserPONew);
		modelAndView.setViewName("beauty/wechat/my/myBeauty.jsp");
		return modelAndView;
	}
	/**
	 * 
	 * 简要说明：跳转交换颜值的页面
	 * 编写者：陈骑元
	 * 创建时间：2017年5月21日 下午10:43:39
	 * @param 说明
	 * @return 说明
	 */
	@RequestMapping(value = "goExchangeBeauty")
	public ModelAndView goExchangeBeauty(HttpServletRequest request, HttpServletResponse response,HttpSession session){
		ModelAndView modelAndView= new ModelAndView();
		CustomUserPO customUserPO=IMSCxt.getCustomUserInfo(session);
		String mobile=customUserPO.getMobile();
		mobile=IMSUtils.repalceString(mobile, "****", 3, 7);
		modelAndView.addObject("mobile", mobile);
		modelAndView.setViewName("beauty/wechat/my/exchangeBeauty.jsp");
		return modelAndView;
	}
	/**
	 * 
	 * 简要说明：兑换颜值
	 * 编写者：陈骑元
	 * 创建时间：2017年5月21日 下午10:43:39
	 * @param 说明
	 * @return 说明
	 */
	@RequestMapping(value = "exchangeBeauty")
	public void exchangeBeauty(String cdkey,HttpServletRequest request, HttpServletResponse response,HttpSession session){
		CustomUserPO customUserPO=IMSCxt.getCustomUserInfo(session);
		Dto outDto=recordCommonService.saveExchangeBeauty(cdkey, customUserPO.getCustom_user_id());
		String outString=IMSJson.toJson(outDto);
		IMSCxt.write(response, outString);
	}
	/**
	 * 
	 * 简要说明：初始化颜值记录交易记录
	 * 编写者：陈骑元
	 * 创建时间：2017年5月21日 下午10:43:39
	 * @param 说明
	 * @return 说明
	 */
	@RequestMapping(value = "initBeautyRecord")
	public  ModelAndView initBeautyRecord(HttpServletRequest request, HttpServletResponse response,HttpSession session){
		ModelAndView modelAndView= new ModelAndView();
		modelAndView.setViewName("beauty/wechat/my/beautyRecordList.jsp");
		return modelAndView;
	}
	/**
	 * 
	 * 简要说明：查询颜值记录
	 * 编写者：陈骑元
	 * 创建时间：2017年5月21日 下午10:43:39
	 * @param 说明
	 * @return 说明
	 */
	@RequestMapping(value = "searchBeautyRecord")
	public  void searchBeautyRecord(HttpServletRequest request, HttpServletResponse response,HttpSession session){
		Dto pDto=Dtos.newDto(request);
		CustomUserPO customUserPO=IMSCxt.getCustomUserInfo(session);
		pDto.put("custom_user_id", customUserPO.getCustom_user_id());
		pDto.setOrder(" a.pay_time desc ");
		List<Dto> recordList=recordCommonService.queryBeautyRecordPage(pDto);
		String outString = IMSJson.toGridJson(recordList, pDto.getPageTotal(),IMSCons.DATETIMEMIN);
		IMSCxt.write(response, outString);
	}
	
	/**
	 * 
	 * 简要说明：初始化颜值记录交易记录
	 * 编写者：陈骑元
	 * 创建时间：2017年5月21日 下午10:43:39
	 * @param 说明
	 * @return 说明
	 */
	@RequestMapping(value = "goFeedback")
	public  ModelAndView goFeedback(HttpServletRequest request, HttpServletResponse response,HttpSession session){
		ModelAndView modelAndView= new ModelAndView();
		modelAndView.setViewName("beauty/wechat/my/feedback.jsp");
		return modelAndView;
	}
	/**
	 * 
	 * 简要说明：保存反馈意见
	 * 编写者：陈骑元
	 * 创建时间：2017年5月21日 下午10:43:39
	 * @param 说明
	 * @return 说明
	 */
	@RequestMapping(value = "saveOpinion")
	public  void saveOpinion(HttpServletRequest request, HttpServletResponse response,HttpSession session){
		Dto inDto=Dtos.newDto(request);
		CustomUserPO customUserPO=IMSCxt.getCustomUserInfo(session);
		inDto.put("custom_user_id", customUserPO.getCustom_user_id());
		Dto outDto=opinionService.save(inDto);
		String outString=IMSJson.toJson(outDto);
		IMSCxt.write(response, outString);
	}



}
