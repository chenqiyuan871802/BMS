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
import com.ims.common.system.modules.po.UserPO;
import com.ims.common.system.modules.service.ParamService;
import com.ims.common.core.asset.IMSFormater;
import com.beauty.common.constant.BeautyCons;
import com.beauty.common.po.BeautyConfigPO;
import com.beauty.common.service.BeautyConfigService;

/**
 * 
 * 类描述：<b>颜值配置[bis_beauty_config]控制类</b>
 * 创建人：陈骑元
 * 邮箱：240823329@qq.com
 * 创建时间：2017-04-26 21:46:53
 * 修改人：
 * 修改时间：
 * 修改备注： 
 * @version 1.0
 */
@Controller
@RequestMapping(value = "system/beautyConfig")
public class BeautyConfigController {
  
    @Autowired
	private  BeautyConfigService beautyConfigService;
    @Autowired
    private  ParamService paramService;
	
	
	/**
	 * 页面初始化
	 *
	 * @return
	 */
	@RequestMapping(value = "init")
	public ModelAndView init() {
		return new ModelAndView("beauty/system/beautyConfig/beautyConfigList.jsp");
	}
	
	/**
	 * 
	 * 分页查询颜值配置信息
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "listBeautyConfig")
	public void listBeautyConfig(HttpServletRequest request, HttpServletResponse response) {
		Dto pDto = Dtos.newDto(request);
		pDto.setOrder(" buy_num ASC ");
		pDto.put("is_del", IMSCons.IS.NO); //查询有效
		List<BeautyConfigPO> beautyConfigList =beautyConfigService.listPage(pDto);
		String outString = IMSJson.toGridJson(beautyConfigList, pDto.getPageTotal());
		IMSCxt.write(response, outString);
	}
	/**
	 * 
	 * 初始化新增颜值配置页面
	 * @return
	 * 
	 */
	@RequestMapping(value = "goAdd")
	public ModelAndView goAdd() {
		return new ModelAndView("beauty/system/beautyConfig/addBeautyConfig.jsp");
	}
	/**
	 * 保存颜值配置信息
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "saveBeautyConfig")
	public void saveBeautyConfig(HttpServletRequest request, HttpServletResponse response) {
	    Dto inDto = Dtos.newDto(request);
		Dto outDto = beautyConfigService.save(inDto);
		IMSCxt.write(response, IMSJson.toJson(outDto));
	}
	/**
	 * 查看颜值配置详情页面
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "goShow")
	public ModelAndView goShow( String config_id) {
	   ModelAndView modelAndView= new ModelAndView();
	   BeautyConfigPO beautyConfigPO=beautyConfigService.selectByKey(config_id);
	   modelAndView.addObject("beautyConfigPO",beautyConfigPO);
	   modelAndView.setViewName("showBeautyConfig.jsp");
	   return modelAndView;
	}
	/**
	 * 初始化修改颜值配置页面
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "goModify")
	public ModelAndView goModify( String config_id) {
	   ModelAndView modelAndView= new ModelAndView();
	   BeautyConfigPO beautyConfigPO=beautyConfigService.selectByKey(config_id);
	   modelAndView.addObject("beautyConfigPO",beautyConfigPO);
	   modelAndView.setViewName("beauty/system/beautyConfig/modifyBeautyConfig.jsp");
	   return modelAndView;
	}
	/**
	 * 修改颜值配置信息
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "updateBeautyConfig")
	public void updateBeautyConfig(HttpServletRequest request, HttpServletResponse response) {
		Dto inDto = Dtos.newDto(request);
		Dto outDto =beautyConfigService.update(inDto);
		IMSCxt.write(response, IMSJson.toJson(outDto));
	}
	/**
	 * 删除颜值配置信息
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "deleteBeautyConfig")
	public void deleteBeautyConfig( String config_id,HttpServletRequest request,HttpServletResponse response) {
		UserPO userPO = IMSCxt.getUserInfo(request.getSession());
		Dto outDto =beautyConfigService.delete(config_id,userPO.getUser_id());
		IMSCxt.write(response, IMSJson.toJson(outDto));
	}
	/**
	 * 批量删除颜值配置信息
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "batchDeleteBeautyConfig")
	public void batchDeleteBeautyConfig( String config_ids,HttpServletResponse response) {
	    List<String> config_idList=IMSFormater.separatStringToList(config_ids);
		Dto outDto =beautyConfigService.batchDelete(config_idList);
		IMSCxt.write(response, IMSJson.toJson(outDto));
	}
	/**
	 * 
	 * 跳转到颜值配置页面
	 * @return
	 * 
	 */
	@RequestMapping(value = "goConfig")
	public ModelAndView goConfig() {
		return new ModelAndView("beauty/system/beautyConfig/beautyConfig.jsp");
	}
	/**
	 * 
	 * 跳转到颜值配置页面
	 * @return
	 * 
	 */
	@RequestMapping(value = "saveConfig")
	public void saveConfig(HttpServletRequest request,HttpServletResponse response) {
		Dto inDto = Dtos.newDto(request);
		UserPO userPO = IMSCxt.getUserInfo(request.getSession());
		Dto outDto =Dtos.newDto();
		String exchange_beauty=inDto.getString(BeautyCons.EXCHANGE_BEAUTY_KEY);
		String beauty_overtime=inDto.getString(BeautyCons.BEAUTY_OVERTIME_KEY);
		paramService.update(BeautyCons.EXCHANGE_BEAUTY_KEY, exchange_beauty, userPO.getUser_id());
		paramService.update(BeautyCons.BEAUTY_OVERTIME_KEY,beauty_overtime, userPO.getUser_id());
		outDto.setAppCode(IMSCons.SUCCESS);
		outDto.setAppMsg("操作完成，颜值配置信息成功。");
		IMSCxt.write(response, IMSJson.toJson(outDto));
	}
}
