package com.beauty.system.web;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
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
import com.beauty.common.po.NurseBagPO;
import com.beauty.common.service.NurseBagService;
import com.beauty.common.service.ShopCommonService;
import com.beauty.common.utils.IdUtil;

/**
 * 
 * 类描述：<b>礼包信息[bis_nurse_bag]控制类</b>
 * 创建人：陈骑元
 * 邮箱：240823329@qq.com
 * 创建时间：2017-04-23 18:08:32
 * 修改人：
 * 修改时间：
 * 修改备注： 
 * @version 1.0
 */
@Controller
@RequestMapping(value = "system/nurseBag")
public class NurseBagController {
  
    @Autowired
	private  NurseBagService nurseBagService;
    @Autowired
    private  ShopCommonService shopCommonService;
	
	
	/**
	 * 页面初始化
	 *
	 * @return
	 */
	@RequestMapping(value = "init")
	public ModelAndView init() {
		return new ModelAndView("beauty/system/nurseBag/nurseBagList.jsp");
	}
	
	/**
	 * 
	 * 分页查询礼包信息信息
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "listNurseBag")
	public void listNurseBag(HttpServletRequest request, HttpServletResponse response) {
		Dto pDto = Dtos.newDto(request);
		pDto.setOrder("  a.sort_no ASC ");
		List<Dto> nurseBagList =shopCommonService.listNurseBagPage(pDto);
		String outString = IMSJson.toGridJson(nurseBagList, pDto.getPageTotal(),IMSCons.DATE);
		IMSCxt.write(response, outString);
	}
	/**
	 * 
	 * 初始化新增礼包信息页面
	 * @return
	 * 
	 */
	@RequestMapping(value = "goAdd")
	public ModelAndView goAdd() {
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("beauty/system/nurseBag/addNurseBag.jsp");
		modelAndView.addObject("current_time", IMSUtils.getDateStr(IMSCons.DATE));
		return modelAndView;
	}
	/**
	 * 保存礼包信息信息
	 * @param response
	 * @param request
	 * @return
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	@RequestMapping(value = "saveNurseBag")
	public void saveNurseBag(
			HttpServletRequest request, HttpServletResponse response) throws IllegalStateException, IOException {
	    Dto inDto = Dtos.newDto(request);
	    inDto.println();
	    String bag_id=IdUtil.createNurseBagId();
	    CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		if (multipartResolver.isMultipart(request)) {
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			Iterator<String> files = multipartRequest.getFileNames();
			while (files.hasNext()) {
				String fileForm = files.next();
				MultipartFile mFile = multipartRequest.getFile(fileForm);
				if (mFile != null && mFile.getSize() > 0) {
					if ("cover_photo_file".equals(fileForm)) {
						String folderPath = request.getSession().getServletContext()
								.getRealPath(BeautyCons.BAG_IMAGE_URL);
						String fileName = mFile.getOriginalFilename();
						String fileTrueName =bag_id + "S."+ FileOperation.getFileType(fileName);
						FileOperation.createFolder(folderPath);
						File targetFile = new File(folderPath, fileTrueName);
						String filePath =BeautyCons.BAG_IMAGE_URL+fileTrueName;
						mFile.transferTo(targetFile);
					    inDto.put("cover_photo", filePath);
					} else {
						String folderPath = request.getSession().getServletContext()
								.getRealPath(BeautyCons.BAG_IMAGE_URL);
						String fileName = mFile.getOriginalFilename();
						String fileTrueName =bag_id + "B."+ FileOperation.getFileType(fileName);
						FileOperation.createFolder(folderPath);
						File targetFile = new File(folderPath, fileTrueName);
						String filePath =BeautyCons.BAG_IMAGE_URL+fileTrueName;
						mFile.transferTo(targetFile);
					    inDto.put("cover_big_photo", filePath);
					}
				}
			}
		}
		
		 inDto.put("bag_id", bag_id);
		Dto outDto = nurseBagService.save(inDto);
		IMSCxt.write(response, IMSJson.toJson(outDto));
	}
	/**
	 * 查看礼包信息详情页面
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "goShow")
	public ModelAndView goShow( String bag_id) {
	   ModelAndView modelAndView= new ModelAndView();
	   NurseBagPO nurseBagPO=nurseBagService.selectByKey(bag_id);
	   modelAndView.addObject("nurseBagPO",nurseBagPO);
	   modelAndView.setViewName("beauty/system/nurseBag/showNurseBag.jsp");
	   return modelAndView;
	}
	/**
	 * 初始化修改礼包信息页面
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "goModify")
	public ModelAndView goModify( String bag_id) {
	   ModelAndView modelAndView= new ModelAndView();
	   NurseBagPO nurseBagPO=nurseBagService.selectByKey(bag_id);
	   List<Dto>  bagProjectList=shopCommonService.listBagProject(bag_id);
	   modelAndView.addObject("bagProjectList", bagProjectList);
	   modelAndView.addObject("nurseBagPO",nurseBagPO);
	   modelAndView.setViewName("beauty/system/nurseBag/modifyNurseBag.jsp");
	   return modelAndView;
	}
	/**
	 * 修改礼包信息信息
	 * @param response
	 * @param request
	 * @return
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	@RequestMapping(value = "updateNurseBag")
	public void updateNurseBag(HttpServletRequest request, HttpServletResponse response) throws IllegalStateException, IOException {
		Dto inDto = Dtos.newDto(request);
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		if (multipartResolver.isMultipart(request)) {
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			Iterator<String> files = multipartRequest.getFileNames();
			while (files.hasNext()) {
				String fileForm = files.next();
				MultipartFile mFile = multipartRequest.getFile(fileForm);
				if (mFile != null && mFile.getSize() > 0) {
					if ("cover_photo_file".equals(fileForm)) {
						String folderPath = request.getSession().getServletContext()
								.getRealPath(BeautyCons.BAG_IMAGE_URL);
						String fileName = mFile.getOriginalFilename();
						String fileTrueName =IMSId.appId() + "S."+ FileOperation.getFileType(fileName);
						FileOperation.createFolder(folderPath);
						File targetFile = new File(folderPath, fileTrueName);
						String filePath =BeautyCons.BAG_IMAGE_URL+fileTrueName;
						mFile.transferTo(targetFile);
					    inDto.put("cover_photo", filePath);
					} else {
						String folderPath = request.getSession().getServletContext()
								.getRealPath(BeautyCons.BAG_IMAGE_URL);
						String fileName = mFile.getOriginalFilename();
						String fileTrueName =IMSId.appId()+ "B."+ FileOperation.getFileType(fileName);
						FileOperation.createFolder(folderPath);
						File targetFile = new File(folderPath, fileTrueName);
						String filePath =BeautyCons.BAG_IMAGE_URL+fileTrueName;
						mFile.transferTo(targetFile);
					    inDto.put("cover_big_photo", filePath);
					}
				}
			}
		}
		
		Dto outDto =nurseBagService.update(inDto);
		IMSCxt.write(response, IMSJson.toJson(outDto));
	}
	/**
	 * 删除礼包信息信息
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "deleteNurseBag")
	public void deleteNurseBag( String bag_id,HttpServletRequest request,HttpServletResponse response) {
		UserPO userPO = IMSCxt.getUserInfo(request.getSession());
		Dto outDto =nurseBagService.delete(bag_id,userPO.getUser_id());
		IMSCxt.write(response, IMSJson.toJson(outDto));
	}
	/**
	 * 批量删除礼包信息信息
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "batchDeleteNurseBag")
	public void batchDeleteNurseBag( String bag_ids,HttpServletResponse response) {
	    List<String> bag_idList=IMSFormater.separatStringToList(bag_ids);
		Dto outDto =nurseBagService.batchDelete(bag_idList);
		IMSCxt.write(response, IMSJson.toJson(outDto));
	}
	/**
	 * 护理项目列表页面初始化
	 *
	 * @return
	 */
	@RequestMapping(value = "initNurseProject")
	public ModelAndView initNurseProject(String operate_mode) {
		 ModelAndView modelAndView= new ModelAndView();
		 //operate_mode 操作模式 1添加 2修改
		 modelAndView.addObject("operate_mode",operate_mode);
		 modelAndView.setViewName("beauty/system/nurseBag/showNurseProjectList.jsp");
		 return modelAndView;
	}
	/**
	 * 
	 * 简要说明：查询礼包项目
	 * 编写者：陈骑元
	 * 创建时间：2017年4月25日 下午11:39:06
	 * @param 说明
	 * @return 说明
	 */
	@RequestMapping(value = "queryBagProjectList")
	public void queryBagProjectList(String bag_id,HttpServletResponse response ){
		List<Dto>  bagProjectList=shopCommonService.listBagProject(bag_id);
		String outString = IMSJson.toGridJson(bagProjectList, bagProjectList.size(),IMSCons.DATETIMEMIN);
		IMSCxt.write(response, outString);
	}
}
