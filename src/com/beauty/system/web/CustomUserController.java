package com.beauty.system.web;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.ims.common.core.asset.IMSJson;
import com.ims.common.core.asset.IMSUtils;
import com.ims.common.core.id.IMSId;
import com.ims.common.core.asset.FileOperation;
import com.ims.common.core.asset.IMSCons;
import com.ims.common.core.asset.IMSCxt;
import com.ims.common.core.matatype.Dto;
import com.ims.common.core.matatype.Dtos;
import com.ims.common.core.asset.IMSFormater;
import com.beauty.common.constant.BeautyCons;
import com.beauty.common.po.CustomUserPO;
import com.beauty.common.service.CustomUserService;
import com.beauty.common.service.ShopCommonService;
import com.beauty.common.utils.IdUtil;

/**
 * 
 * 类描述：<b>顾客用户信息表[bis_custom_user]控制类</b>
 * 创建人：陈骑元
 * 邮箱：240823329@qq.com
 * 创建时间：2017-04-17 12:07:27
 * 修改人：
 * 修改时间：
 * 修改备注： 
 * @version 1.0
 */
@Controller
@RequestMapping(value = "system/customUser")
public class CustomUserController {
  
    @Autowired
	private  CustomUserService customUserService;
    @Autowired
    private  ShopCommonService shopCommonService;
	
	
	/**
	 * 页面初始化
	 *
	 * @return
	 */
	@RequestMapping(value = "init")
	public ModelAndView init() {
		return new ModelAndView("beauty/system/customUser/customUserList.jsp");
	}
	/**
	 * 页面初始化
	 *
	 * @return
	 */
	@RequestMapping(value = "initUser")
	public ModelAndView initUser(String shop_id) {
		 ModelAndView modelAndView= new ModelAndView();
		  modelAndView.addObject("shop_id", shop_id);
		  modelAndView.setViewName("beauty/system/customUser/showCustomUserList.jsp");
		return modelAndView;
	}
	
	/**
	 * 
	 * 分页查询顾客用户信息表信息
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "listCustomUser")
	public void listCustomUser(HttpServletRequest request, HttpServletResponse response) {
		Dto pDto = Dtos.newDto(request);
		pDto.setOrder(" b.recent_time DESC ,a.custom_user_id DESC");
		String recent_type=pDto.getString("recent_type");
		if(IMSUtils.isNotEmpty(recent_type)){
			int num=Integer.parseInt(recent_type);
			int dayNum=num*30;
			pDto.put("dayNum", dayNum);
		}
		List<Dto> customUserList =shopCommonService.listCustomUserPage(pDto);
		String outString = IMSJson.toGridJson(customUserList, pDto.getPageTotal(),IMSCons.DATETIMEMIN);
		IMSCxt.write(response, outString);
	}
	/**
	 * 
	 * 初始化新增顾客用户信息表页面
	 * @return
	 * 
	 */
	@RequestMapping(value = "goAdd")
	public ModelAndView goAdd() {
		return new ModelAndView("beauty/system/customUser/addCustomUser.jsp");
	}
	/**
	 * 保存顾客用户信息表信息
	 * @param response
	 * @param request
	 * @return
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	@RequestMapping(value = "saveCustomUser")
	public void saveCustomUser(@RequestParam(value = "photo_file", required = false) MultipartFile photo_file,HttpServletRequest request, HttpServletResponse response) throws IllegalStateException, IOException {
	    Dto inDto = Dtos.newDto(request);
	    String custom_user_id=IdUtil.createCustomUserId();
	    if (photo_file.getSize() > 0) { // 如果存在上传文件
			String folderPath = request.getSession().getServletContext()
					.getRealPath(BeautyCons.CUSTOM_IMAGE_URL);
			String fileName = photo_file.getOriginalFilename();
			String fileTrueName =custom_user_id + "."+ FileOperation.getFileType(fileName);
			FileOperation.createFolder(folderPath);
			File targetFile = new File(folderPath, fileTrueName);
			String filePath =BeautyCons.CUSTOM_IMAGE_URL+fileTrueName;
			photo_file.transferTo(targetFile);
		    inDto.put("photo", filePath);
		}
	    inDto.put("custom_user_id", custom_user_id);
	    inDto.put("enroll_mode", BeautyCons.ENROLL_MODE_SYS); //后台注册
		Dto outDto = customUserService.save(inDto);
		IMSCxt.write(response, IMSJson.toJson(outDto));
	}
	
	/**
	 * 待服务的项目
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "initShow")
	public ModelAndView initShow( String custom_user_id) {
	   ModelAndView modelAndView= new ModelAndView();
	   modelAndView.addObject("custom_user_id", custom_user_id);
	   modelAndView.setViewName("beauty/system/customUser/initShow.jsp");
	   return modelAndView;
	}
	
	/**
	 * 查看顾客用户信息表详情页面
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "goShow")
	public ModelAndView goShow( String custom_user_id) {
	   ModelAndView modelAndView= new ModelAndView();
	   CustomUserPO customUserPO=customUserService.selectByKey(custom_user_id);
	   modelAndView.addObject("customUserPO",customUserPO);
	   modelAndView.setViewName("beauty/system/customUser/showCustomUser.jsp");
	   return modelAndView;
	}
	/**
	 * 初始化修改顾客用户信息表页面
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "goModify")
	public ModelAndView goModify( String custom_user_id) {
	   ModelAndView modelAndView= new ModelAndView();
	   CustomUserPO customUserPO=customUserService.selectByKey(custom_user_id);
	   modelAndView.addObject("customUserPO",customUserPO);
	   modelAndView.setViewName("beauty/system/customUser/modifyCustomUser.jsp");
	   return modelAndView;
	}
	/**
	 * 修改顾客用户信息表信息
	 * @param response
	 * @param request
	 * @return
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	@RequestMapping(value = "updateCustomUser")
	public void updateCustomUser(@RequestParam(value = "photo_file", required = false) MultipartFile photo_file,
			HttpServletRequest request, HttpServletResponse response) throws IllegalStateException, IOException {
		Dto inDto = Dtos.newDto(request);
		String photo_id=IMSId.appId();
		  if (photo_file.getSize() > 0) { // 如果存在上传文件
				String folderPath = request.getSession().getServletContext()
						.getRealPath(BeautyCons.CUSTOM_IMAGE_URL);
				String fileName = photo_file.getOriginalFilename();
				String fileTrueName =photo_id + "."+ FileOperation.getFileType(fileName);
				FileOperation.createFolder(folderPath);
				File targetFile = new File(folderPath, fileTrueName);
				String filePath =BeautyCons.CUSTOM_IMAGE_URL +fileTrueName;
				photo_file.transferTo(targetFile);
			    inDto.put("photo", filePath);
			}
		Dto outDto =customUserService.update(inDto);
		IMSCxt.write(response, IMSJson.toJson(outDto));
	}
	/**
	 * 删除顾客用户信息表信息
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "deleteCustomUser")
	public void deleteCustomUser( String custom_user_id,HttpServletResponse response) {
		Dto outDto =customUserService.delete(custom_user_id);
		IMSCxt.write(response, IMSJson.toJson(outDto));
	}
	/**
	 * 批量删除顾客用户信息表信息
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "batchDeleteCustomUser")
	public void batchDeleteCustomUser( String custom_user_ids,HttpServletResponse response) {
	    List<String> custom_user_idList=IMSFormater.separatStringToList(custom_user_ids);
		Dto outDto =customUserService.batchDelete(custom_user_idList);
		IMSCxt.write(response, IMSJson.toJson(outDto));
	}
	/**
	 * 跳转到密码重置修改界面
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "goModifyPassword")
	public ModelAndView goModifyPassword(String custom_user_id) {
		 ModelAndView modelAndView= new ModelAndView();
		 modelAndView.addObject("custom_user_id",custom_user_id);
		 modelAndView.setViewName("beauty/system/customUser/modifyPassword.jsp");
		 return modelAndView;
	}
	/**
	 * 
	 * 简要说明：用户密码重置
	 * 编写者：陈骑元
	 * 创建时间：2016年12月19日 下午7:56:02
	 * @param 说明
	 * @return 说明
	 */
	@RequestMapping(value="updatePassword")
	public void updatePassword(HttpServletRequest request, HttpServletResponse response){
		Dto inDto = Dtos.newDto(request);
		Dto outDto =customUserService.updatePassword(inDto);
		IMSCxt.write(response, IMSJson.toJson(outDto));
	}
	/**
	 * 页面初始化
	 *
	 * @return
	 */
	@RequestMapping(value = "initSelect")
	public ModelAndView initSelect() {
		return new ModelAndView("beauty/system/customUser/selectCustomUserList.jsp");
	}
	/**
	 * 
	 * 简要说明：查询已经关注的微信用户
	 * 编写者：陈骑元
	 * 创建时间：2017年7月23日 下午4:15:28
	 * @param 说明
	 * @return 说明
	 */
	@RequestMapping(value="listSelectCustomUser")
	public void listSelectCustomUser(HttpServletRequest request, HttpServletResponse response){
		Dto pDto = Dtos.newDto(request);
		pDto.put("is_del", IMSCons.IS.NO);
		pDto.put("wechat_status", BeautyCons.WECHAT_STATUS_YES);
		pDto.setOrder(" enroll_time DESC ");
		List<CustomUserPO> customUserList=customUserService.like(pDto);
		String outString = IMSJson.toGridJson(customUserList,customUserList.size());
		IMSCxt.write(response, outString);
	}
	
}
