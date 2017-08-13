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
import com.ims.common.system.modules.po.UserPO;
import com.ims.common.core.asset.IMSFormater;
import com.beauty.common.constant.BeautyCons;
import com.beauty.common.po.ShopUserPO;
import com.beauty.common.service.ShopCommonService;
import com.beauty.common.service.ShopUserService;

/**
 * 
 * 类描述：<b>店铺员工信息表[bis_shop_user]控制类</b>
 * 创建人：陈骑元
 * 邮箱：240823329@qq.com
 * 创建时间：2017-04-17 12:09:21
 * 修改人：
 * 修改时间：
 * 修改备注： 
 * @version 1.0
 */
@Controller
@RequestMapping(value = "system/shopUser")
public class ShopUserController {
  
    @Autowired
	private  ShopUserService shopUserService;
    @Autowired
    private  ShopCommonService shopCommonService;
	
	
	/**
	 * 页面初始化
	 *
	 * @return
	 */
	@RequestMapping(value = "init")
	public ModelAndView init() {
		return new ModelAndView("beauty/system/shopUser/shopUserList.jsp");
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
		  modelAndView.setViewName("beauty/system/shopUser/showShopUserList.jsp");
		return modelAndView;
	}
	
	/**
	 * 
	 * 分页查询店铺员工信息表信息
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "listShopUser")
	public void listShopUser(HttpServletRequest request, HttpServletResponse response) {
		Dto pDto = Dtos.newDto(request);
		List<Dto> shopUserList =shopCommonService.listShopUserPage(pDto);
		String outString = IMSJson.toGridJson(shopUserList, pDto.getPageTotal(),IMSCons.DATE);
		IMSCxt.write(response, outString);
	}
	/**
	 * 
	 * 初始化新增店铺员工信息表页面
	 * @return
	 * 
	 */
	@RequestMapping(value = "goAdd")
	public ModelAndView goAdd(String shop_id) {
		  ModelAndView modelAndView= new ModelAndView();
		  modelAndView.addObject("current_date", IMSUtils.getDate());
		  modelAndView.addObject("shop_id", shop_id);
		  modelAndView.setViewName("beauty/system/shopUser/addShopUser.jsp");
		return modelAndView;
	}
	/**
	 * 保存店铺员工信息表信息
	 * @param response
	 * @param request
	 * @return
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	@RequestMapping(value = "saveShopUser")
	public void saveShopUser(@RequestParam(value = "photo_file", required = false) MultipartFile photo_file,HttpServletRequest request, HttpServletResponse response) throws IllegalStateException, IOException {
	    Dto inDto = Dtos.newDto(request);
	    String shop_user_id=IMSId.appId();
	    inDto.put("shop_user_id", shop_user_id);
	    if (photo_file.getSize() > 0) { // 如果存在上传文件
			String folderPath = request.getSession().getServletContext()
					.getRealPath(BeautyCons.PHOTO_IMAGE_URL);
			String fileName = photo_file.getOriginalFilename();
			String fileTrueName =shop_user_id + "."+ FileOperation.getFileType(fileName);
			FileOperation.createFolder(folderPath);
			File targetFile = new File(folderPath, fileTrueName);
			String filePath =BeautyCons.PHOTO_IMAGE_URL +fileTrueName;
			photo_file.transferTo(targetFile);
		    inDto.put("photo", filePath);
		} 
		Dto outDto = shopUserService.save(inDto);
		IMSCxt.write(response, IMSJson.toJson(outDto));
	}
	/**
	 * 查看店铺员工信息表详情页面
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "goShow")
	public ModelAndView goShow( String shop_user_id) {
	   ModelAndView modelAndView= new ModelAndView();
	   ShopUserPO shopUserPO=shopUserService.selectByKey(shop_user_id);
	   modelAndView.addObject("shopUserPO",shopUserPO);
	   modelAndView.setViewName("showShopUser.jsp");
	   return modelAndView;
	}
	/**
	 * 初始化修改店铺员工信息表页面
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "goModify")
	public ModelAndView goModify( String shop_user_id) {
	   ModelAndView modelAndView= new ModelAndView();
	   ShopUserPO shopUserPO=shopUserService.selectByKey(shop_user_id);
	   modelAndView.addObject("shopUserPO",shopUserPO);
	   modelAndView.setViewName("beauty/system/shopUser/modifyShopUser.jsp");
	   return modelAndView;
	}
	/**
	 * 跳转到密码重置修改界面
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "goModifyPassword")
	public ModelAndView goModifyPassword(String shop_user_id) {
		 ModelAndView modelAndView= new ModelAndView();
		 modelAndView.addObject("shop_user_id",shop_user_id);
		 modelAndView.setViewName("beauty/system/shopUser/modifyPassword.jsp");
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
		Dto outDto =shopUserService.updatePassword(inDto);
		IMSCxt.write(response, IMSJson.toJson(outDto));
	}
	/**
	 * 修改店铺员工信息表信息
	 * @param response
	 * @param request
	 * @return
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	@RequestMapping(value = "updateShopUser")
	public void updateShopUser(@RequestParam(value = "photo_file", required = false) MultipartFile photo_file,HttpServletRequest request, HttpServletResponse response) throws IllegalStateException, IOException {
		Dto inDto = Dtos.newDto(request);
		 if (photo_file.getSize() > 0) { // 如果存在上传文件
				String folderPath = request.getSession().getServletContext()
						.getRealPath(BeautyCons.PHOTO_IMAGE_URL);
				String fileName = photo_file.getOriginalFilename();
				String fileTrueName =IMSId.appId() + "."+ FileOperation.getFileType(fileName);
				FileOperation.createFolder(folderPath);
				File targetFile = new File(folderPath, fileTrueName);
				String filePath =BeautyCons.PHOTO_IMAGE_URL +fileTrueName;
				photo_file.transferTo(targetFile);
			    inDto.put("photo", filePath);
			} 
		Dto outDto =shopUserService.update(inDto);
		IMSCxt.write(response, IMSJson.toJson(outDto));
	}
	/**
	 * 删除店铺员工信息表信息
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "deleteShopUser")
	public void deleteShopUser( String shop_user_id,HttpServletRequest request,HttpServletResponse response) {
		UserPO userPO = IMSCxt.getUserInfo(request.getSession());
		Dto outDto =shopUserService.delete(shop_user_id,userPO.getUser_id());
		IMSCxt.write(response, IMSJson.toJson(outDto));
	}
	/**
	 * 批量删除店铺员工信息表信息
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "batchDeleteShopUser")
	public void batchDeleteShopUser( String shop_user_ids,HttpServletResponse response) {
	    List<String> shop_user_idList=IMSFormater.separatStringToList(shop_user_ids);
		Dto outDto =shopUserService.batchDelete(shop_user_idList);
		IMSCxt.write(response, IMSJson.toJson(outDto));
	}
	
}
