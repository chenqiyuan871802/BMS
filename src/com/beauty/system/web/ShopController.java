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
import com.ims.common.core.asset.IMSCons;
import com.ims.common.core.asset.IMSCxt;
import com.ims.common.core.matatype.Dto;
import com.ims.common.core.matatype.Dtos;
import com.ims.common.system.modules.po.UserPO;
import com.ims.common.core.asset.IMSFormater;
import com.beauty.common.constant.BeautyCons;
import com.beauty.common.po.ShopPO;
import com.beauty.common.po.ShopUserPO;
import com.beauty.common.service.ShopCommonService;
import com.beauty.common.service.ShopService;
import com.beauty.common.service.ShopUserService;
import com.beauty.common.utils.IdUtil;

/**
 * 
 * 类描述：<b>店铺信息表[bis_shop]控制类</b> 创建人：陈骑元 邮箱：240823329@qq.com 创建时间：2017-04-17
 * 12:08:34 修改人： 修改时间： 修改备注：
 * 
 * @version 1.0
 */
@Controller
@RequestMapping(value = "system/shopSys")
public class ShopController {

	@Autowired
	private ShopService shopService;
	@Autowired
	private ShopUserService shopUserService;
	@Autowired
	private ShopCommonService shopCommonService;

	/**
	 * 页面初始化
	 *
	 * @return
	 */
	@RequestMapping(value = "init")
	public ModelAndView init() {
		return new ModelAndView("beauty/system/shop/shopList.jsp");
	}

	/**
	 * 
	 * 分页查询店铺信息表信息
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "listShop")
	public void listShop(HttpServletRequest request, HttpServletResponse response) {
		Dto pDto = Dtos.newDto(request);
		pDto.setOrder(" bis_shop.sort_no ASC ");
		List<Dto> shopList = shopCommonService.listShopPage(pDto);
		String outString = IMSJson.toGridJson(shopList, pDto.getPageTotal());
		IMSCxt.write(response, outString);
	}

	/**
	 * 
	 * 查询全部店铺信息
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "queryShop")
	public void queryShop(HttpServletRequest request, HttpServletResponse response) {
		Dto pDto = Dtos.newDto(request);
		pDto.put("show_status",BeautyCons.STATUS_YES);
		pDto.setOrder("bis_shop.sort_no ASC ");
		List<Dto> shopList = shopCommonService.listShop(pDto);
		String outString = IMSJson.toJson(shopList);
		IMSCxt.write(response, outString);
	}

	/**
	 * 
	 * 初始化新增店铺信息表页面
	 * 
	 * @return
	 * 
	 */
	@RequestMapping(value = "goAdd")
	public ModelAndView goAdd() {
		return new ModelAndView("beauty/system/shop/addShop.jsp");
	}

	/**
	 * 保存店铺信息表信息
	 * 
	 * @param response
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "saveShop")
	public void saveShop(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Dto inDto = Dtos.newDto(request);
		String shop_id = IdUtil.createShopId();
		inDto.put("shop_id", shop_id);
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		if (multipartResolver.isMultipart(request)) {
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			Iterator<String> files = multipartRequest.getFileNames();
			while (files.hasNext()) {
				String fileForm = files.next();
				MultipartFile mFile = multipartRequest.getFile(fileForm);
				if (mFile != null && mFile.getSize() > 0) {
					if ("shop_image_file".equals(fileForm)) {
						String folderPath = request.getSession().getServletContext()
								.getRealPath(BeautyCons.SHOP_IMAGE_URL);
						String fileName = mFile.getOriginalFilename();
						String fileTrueName = IMSId.appId() + "S." + FileOperation.getFileType(fileName);
						FileOperation.createFolder(folderPath);
						File targetFile = new File(folderPath, fileTrueName);
						String filePath = BeautyCons.SHOP_IMAGE_URL + fileTrueName;
						mFile.transferTo(targetFile);
						inDto.put("shop_image", filePath);

					} else {
						String folderPath = request.getSession().getServletContext()
								.getRealPath(BeautyCons.SHOP_IMAGE_URL);
						String fileName = mFile.getOriginalFilename();
						String fileTrueName = IMSId.appId() + "B." + FileOperation.getFileType(fileName);
						FileOperation.createFolder(folderPath);
						File targetFile = new File(folderPath, fileTrueName);
						String filePath = BeautyCons.SHOP_IMAGE_URL + fileTrueName;
						mFile.transferTo(targetFile);
						inDto.put("shop_detail_image", filePath);
					}
				}
			}
		}
		String qrCodeFolderPath = request.getSession().getServletContext().getRealPath(BeautyCons.SHOP_QRCODE_URL);
		String qrCodeImage = qrCodeFolderPath + File.separator + shop_id + ".png";
		FileOperation.createFolder(qrCodeFolderPath);
		String request_url = IMSCxt.getParamValue(BeautyCons.REQUEST_URL_KEY); // 系统请求的地址
		String url = request_url + "/wechat/order/listBeautyInfo.jhtml?shop_id=" + shop_id;
		String savePath = BeautyCons.SHOP_QRCODE_URL + shop_id + ".png";
		QrCodeUtils.createQrCode(url, qrCodeImage);
		inDto.put("shop_qrcode", savePath);
		Dto outDto = shopService.save(inDto);
		IMSCxt.write(response, IMSJson.toJson(outDto));
	}

	/**
	 * 查看店铺信息表详情页面
	 * 
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "goShow")
	public ModelAndView goShow(String shop_id) {
		ModelAndView modelAndView = new ModelAndView();
		ShopPO shopPO = shopService.selectByKey(shop_id);
		ShopUserPO shopUserPO = shopUserService.selectByKey(shopPO.getOwner_id());
		modelAndView.addObject("shopPO", shopPO);
		modelAndView.addObject("shopUserPO", shopUserPO);
		modelAndView.setViewName("beauty/system/shop/showShop.jsp");
		return modelAndView;
	}

	/**
	 * 初始化修改店铺信息表页面
	 * 
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "goModify")
	public ModelAndView goModify(String shop_id) {
		ModelAndView modelAndView = new ModelAndView();
		ShopPO shopPO = shopService.selectByKey(shop_id);
		modelAndView.addObject("shopPO", shopPO);
		modelAndView.setViewName("beauty/system/shop/modifyShop.jsp");
		return modelAndView;
	}

	/**
	 * 修改店铺信息表信息
	 * 
	 * @param response
	 * @param request
	 * @return
	 * @throws IOException
	 * @throws IllegalStateException
	 */
	@RequestMapping(value = "updateShop")
	public void updateShop(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Dto inDto = Dtos.newDto(request);
		String shop_id = inDto.getString("shop_id");
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		if (multipartResolver.isMultipart(request)) {
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			Iterator<String> files = multipartRequest.getFileNames();
			while (files.hasNext()) {
				String fileForm = files.next();
				MultipartFile mFile = multipartRequest.getFile(fileForm);
				if (mFile != null && mFile.getSize() > 0) {
					if ("shop_image_file".equals(fileForm)) {
						String folderPath = request.getSession().getServletContext()
								.getRealPath(BeautyCons.SHOP_IMAGE_URL);
						String fileName = mFile.getOriginalFilename();
						String fileTrueName = IMSId.appId() + "S." + FileOperation.getFileType(fileName);
						FileOperation.createFolder(folderPath);
						File targetFile = new File(folderPath, fileTrueName);
						String filePath = BeautyCons.SHOP_IMAGE_URL + fileTrueName;
						mFile.transferTo(targetFile);

						inDto.put("shop_image", filePath);
					} else {
						String folderPath = request.getSession().getServletContext()
								.getRealPath(BeautyCons.SHOP_IMAGE_URL);
						String fileName = mFile.getOriginalFilename();
						String fileTrueName = IMSId.appId() + "B." + FileOperation.getFileType(fileName);
						FileOperation.createFolder(folderPath);
						File targetFile = new File(folderPath, fileTrueName);
						String filePath = BeautyCons.SHOP_IMAGE_URL + fileTrueName;
						mFile.transferTo(targetFile);
						inDto.put("shop_detail_image", filePath);

					}
				}
			}
		}
		String qrId=IMSId.appId();
		String qrCodeFolderPath = request.getSession().getServletContext().getRealPath(BeautyCons.SHOP_QRCODE_URL);
		String qrCodeImage = qrCodeFolderPath + File.separator + qrId + ".png";
		FileOperation.createFolder(qrCodeFolderPath);
		String request_url = IMSCxt.getParamValue(BeautyCons.REQUEST_URL_KEY); // 系统请求的地址
		String url = request_url + "/wechat/order/listBeautyInfo.jhtml?shop_id=" + shop_id;
		String savePath = BeautyCons.SHOP_QRCODE_URL + qrId + ".png";
		QrCodeUtils.createQrCode(url, qrCodeImage);
		inDto.put("shop_qrcode", savePath);
		Dto outDto = shopService.update(inDto);
		IMSCxt.write(response, IMSJson.toJson(outDto));
	}

	/**
	 * 删除店铺信息表信息
	 * 
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "deleteShop")
	public void deleteShop(String shop_id, HttpServletRequest request, HttpServletResponse response) {
		UserPO userPO = IMSCxt.getUserInfo(request.getSession());
		Dto outDto = shopService.delete(shop_id, userPO.getUser_id());
		IMSCxt.write(response, IMSJson.toJson(outDto));
	}

	/**
	 * 批量删除店铺信息表信息
	 * 
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "batchDeleteShop")
	public void batchDeleteShop(String shop_ids, HttpServletResponse response) {
		List<String> shop_idList = IMSFormater.separatStringToList(shop_ids);
		Dto outDto = shopService.batchDelete(shop_idList);
		IMSCxt.write(response, IMSJson.toJson(outDto));
	}

	/**
	 * 页面初始化
	 *
	 * @return
	 */
	@RequestMapping(value = "initInfo")
	public ModelAndView initInfo(String shop_id) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("shop_id", shop_id);
		modelAndView.setViewName("beauty/system/shop/initInfo.jsp");
		return modelAndView;
	}

}
