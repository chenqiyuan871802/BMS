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
import com.ims.common.core.asset.QrCodeUtils;
import com.ims.common.core.id.IMSId;
import com.ims.common.core.asset.FileOperation;
import com.ims.common.core.asset.IMSCxt;
import com.ims.common.core.matatype.Dto;
import com.ims.common.core.matatype.Dtos;
import com.ims.common.system.modules.po.UserPO;
import com.ims.common.core.asset.IMSFormater;
import com.beauty.common.constant.BeautyCons;
import com.beauty.common.po.NurseProjectPO;
import com.beauty.common.service.NurseProjectService;
import com.beauty.common.service.ShopCommonService;
import com.beauty.common.utils.IdUtil;

/**
 * 
 * 类描述：<b>护理项目信息表[bis_nurse_project]控制类</b> 创建人：陈骑元 邮箱：240823329@qq.com
 * 创建时间：2017-04-17 12:28:47 修改人： 修改时间： 修改备注：
 * 
 * @version 1.0
 */
@Controller
@RequestMapping(value = "system/nurseProject")
public class NurseProjectController {

	@Autowired
	private NurseProjectService nurseProjectService;
	@Autowired
	private ShopCommonService shopCommonService;

	/**
	 * 页面初始化
	 *
	 * @return
	 */
	@RequestMapping(value = "init")
	public ModelAndView init() {
		return new ModelAndView("beauty/system/nurseProject/nurseProjectList.jsp");
	}

	/**
	 * 
	 * 分页查询护理项目信息表信息
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "listNurseProject")
	public void listNurseProject(HttpServletRequest request, HttpServletResponse response) {
		Dto pDto = Dtos.newDto(request);
		pDto.setOrder(" b.sort_no ASC ,a.sort_no ASC ");
		List<Dto> nurseProjectList = shopCommonService.listNurseProjectPage(pDto);
		String outString = IMSJson.toGridJson(nurseProjectList, pDto.getPageTotal());
		IMSCxt.write(response, outString);
	}

	/**
	 * 
	 * 初始化新增护理项目信息表页面
	 * 
	 * @return
	 * 
	 */
	@RequestMapping(value = "goAdd")
	public ModelAndView goAdd() {
		return new ModelAndView("beauty/system/nurseProject/addNurseProject.jsp");
	}

	/**
	 * 保存护理项目信息表信息
	 * 
	 * @param response
	 * @param request
	 * @return
	 * @throws IOException
	 * @throws IllegalStateException
	 */
	@RequestMapping(value = "saveNurseProject")
	public void saveNurseProject(HttpServletRequest request, HttpServletResponse response)
			throws IllegalStateException, IOException {
		Dto inDto = Dtos.newDto(request);
		String project_id = IdUtil.createNurseProjectId();
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
								.getRealPath(BeautyCons.NURSE_IMAGE_URL);
						String fileName = mFile.getOriginalFilename();
						String fileTrueName = project_id + "S." + FileOperation.getFileType(fileName);
						FileOperation.createFolder(folderPath);
						File targetFile = new File(folderPath, fileTrueName);
						String filePath = BeautyCons.NURSE_IMAGE_URL + fileTrueName;
						mFile.transferTo(targetFile);
						inDto.put("cover_photo", filePath);
					} else {
						String folderPath = request.getSession().getServletContext()
								.getRealPath(BeautyCons.NURSE_IMAGE_URL);
						String fileName = mFile.getOriginalFilename();
						String fileTrueName = project_id + "B." + FileOperation.getFileType(fileName);
						FileOperation.createFolder(folderPath);
						File targetFile = new File(folderPath, fileTrueName);
						String filePath = BeautyCons.NURSE_IMAGE_URL + fileTrueName;
						mFile.transferTo(targetFile);
						inDto.put("cover_big_photo", filePath);
					}
				}
			}
		}

		inDto.put("project_id", project_id);
		Dto outDto = nurseProjectService.save(inDto);
		IMSCxt.write(response, IMSJson.toJson(outDto));
	}

	/**
	 * 查看护理项目信息表详情页面
	 * 
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "goShow")
	public ModelAndView goShow(String project_id) {
		ModelAndView modelAndView = new ModelAndView();
		Dto nurseProjectPO = shopCommonService.queryNurseProjectDetail(project_id);
		modelAndView.addObject("nurseProjectPO", nurseProjectPO);
		modelAndView.setViewName("beauty/system/nurseProject/showNurseProject.jsp");
		return modelAndView;
	}

	/**
	 * 初始化修改护理项目信息表页面
	 * 
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "goModify")
	public ModelAndView goModify(String project_id) {
		ModelAndView modelAndView = new ModelAndView();
		NurseProjectPO nurseProjectPO = nurseProjectService.selectByKey(project_id);
		modelAndView.addObject("nurseProjectPO", nurseProjectPO);
		modelAndView.setViewName("beauty/system/nurseProject/modifyNurseProject.jsp");
		return modelAndView;
	}

	/**
	 * 修改护理项目信息表信息
	 * 
	 * @param response
	 * @param request
	 * @return
	 * @throws IOException
	 * @throws IllegalStateException
	 */
	@RequestMapping(value = "updateNurseProject")
	public void updateNurseProject(HttpServletRequest request, HttpServletResponse response) throws IllegalStateException, IOException {
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
					String photo_id = IMSId.appId();
					if ("cover_photo_file".equals(fileForm)) {

						String folderPath = request.getSession().getServletContext()
								.getRealPath(BeautyCons.NURSE_IMAGE_URL);
						String fileName = mFile.getOriginalFilename();
						String fileTrueName = photo_id + "S." + FileOperation.getFileType(fileName);
						FileOperation.createFolder(folderPath);
						File targetFile = new File(folderPath, fileTrueName);
						String filePath = BeautyCons.NURSE_IMAGE_URL + fileTrueName;
						mFile.transferTo(targetFile);
						inDto.put("cover_photo", filePath);
					} else {
						String folderPath = request.getSession().getServletContext()
								.getRealPath(BeautyCons.NURSE_IMAGE_URL);
						String fileName = mFile.getOriginalFilename();
						String fileTrueName = photo_id + "B." + FileOperation.getFileType(fileName);
						FileOperation.createFolder(folderPath);
						File targetFile = new File(folderPath, fileTrueName);
						String filePath = BeautyCons.NURSE_IMAGE_URL + fileTrueName;
						mFile.transferTo(targetFile);
						inDto.put("cover_big_photo", filePath);
					}
				}
			}
		}
		Dto outDto = nurseProjectService.update(inDto);
		IMSCxt.write(response, IMSJson.toJson(outDto));
	}

	/**
	 * 删除护理项目信息表信息
	 * 
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "deleteNurseProject")
	public void deleteNurseProject(String project_id, HttpServletRequest request, HttpServletResponse response) {
		UserPO userPO = IMSCxt.getUserInfo(request.getSession());
		Dto outDto = nurseProjectService.delete(project_id, userPO.getUser_id());
		IMSCxt.write(response, IMSJson.toJson(outDto));
	}

	/**
	 * 批量删除护理项目信息表信息
	 * 
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "batchDeleteNurseProject")
	public void batchDeleteNurseProject(String project_ids, HttpServletResponse response) {
		List<String> project_idList = IMSFormater.separatStringToList(project_ids);
		Dto outDto = nurseProjectService.batchDelete(project_idList);
		IMSCxt.write(response, IMSJson.toJson(outDto));
	}

}
