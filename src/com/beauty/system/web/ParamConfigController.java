package com.beauty.system.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.beauty.common.constant.BeautyCons;
import com.ims.common.core.asset.IMSCons;
import com.ims.common.core.asset.IMSCxt;
import com.ims.common.core.asset.IMSJson;
import com.ims.common.core.asset.IMSUtils;
import com.ims.common.core.id.IMSId;
import com.ims.common.core.matatype.Dto;
import com.ims.common.core.matatype.Dtos;
import com.ims.common.system.modules.po.ParamPO;
import com.ims.common.system.modules.service.ParamService;

/**
 * 
 * 类名:com.beauty.system.web.ParamConfigController
 * 描述:短信参数设置模板
 * 编写者:陈骑元
 * 创建时间:2017年5月10日 下午11:39:25
 * 修改说明:
 */
@Controller
@RequestMapping("system/paramConfig")
public class ParamConfigController {
	
	@Autowired
	private  ParamService paramService;
	/**
	 * 短信参数配置界面
	 *
	 * @return
	 */
	@RequestMapping(value = "init")
	public ModelAndView init() {
		return new ModelAndView("beauty/system/paramConfig/paramConfig.jsp");
	}
	/**
	 * 
	 * 短信参数配置界面
	 * @return
	 */
	@RequestMapping(value = "saveSmsConfig")
	public void saveSmsConfig(HttpServletRequest request,HttpServletResponse response) {
		Dto inDto = Dtos.newDto(request);
		Dto outDto=Dtos.newDto();
		saveOrUpdateParam(inDto,BeautyCons.SMS_URL,"短信网关");
		saveOrUpdateParam(inDto,BeautyCons.SMS_APP_KEY,"短信AppKey");
		saveOrUpdateParam(inDto,BeautyCons.SMS_APP_SRCRET,"短信AppSecret");
		saveOrUpdateParam(inDto,BeautyCons.SMS_SIGNE,"短信签名");
		saveOrUpdateParam(inDto,BeautyCons.SMS_TEMPLATE_CODE,"短信模板编号");
		outDto.setAppCode(IMSCons.SUCCESS);
		outDto.setAppMsg("操作完成，短信参数信息保存成功。");
		IMSCxt.write(response, IMSJson.toJson(outDto));
	}
	/**
	 * 
	 * 预约参数保存界面
	 * @return
	 */
	@RequestMapping(value = "saveSubConfig")
	public void saveSubConfig(HttpServletRequest request,HttpServletResponse response) {
		Dto inDto = Dtos.newDto(request);
		Dto outDto=Dtos.newDto();
		saveOrUpdateParam(inDto,BeautyCons.SUBSCRIBE_DEPOSIT,"预约定金");
		saveOrUpdateParam(inDto,BeautyCons.EVERY_TWO_LIMIT,"每两小时预约上限");
		saveOrUpdateParam(inDto,BeautyCons.CANCEL_TIME,"可取消预定时间");
		outDto.setAppCode(IMSCons.SUCCESS);
		outDto.setAppMsg("操作完成，预约参数信息保存成功。");
		IMSCxt.write(response, IMSJson.toJson(outDto));
	}
	/**
	 * 
	 * 简要说明：保存或者更新参数信息
	 * 编写者：陈骑元
	 * 创建时间：2017年5月11日 下午10:10:41
	 * @param 说明
	 * @return 说明
	 */
	private void saveOrUpdateParam(Dto inDto,String param_key,String param_name){
		String param_value=inDto.getString(param_key);
		if(IMSUtils.isNotEmpty(param_value)){
			String user_id= inDto.getString(IMSCons.LOGIN_USER_ID);
			 Dto countDto = Dtos.newDto();
		     countDto.put("param_key", param_key);
		     int count=paramService.rows(countDto);
		     if(count>0){ //说明已经存在
		    	 paramService.update(param_key, param_value, user_id);
		     }else{
		    	 ParamPO paramPO=new ParamPO();
		    	 paramPO.setParam_id(IMSId.uuid());
		    	 paramPO.setCatalog_cascade_id("0.001.001");
		    	 paramPO.setCatalog_id("5423b9ba9ac6472c80881827acafe9e9");
		    	 paramPO.setStatus("1");
		    	 paramPO.setEdit_mode("0");
		    	 paramPO.setParam_key(param_key);
		    	 paramPO.setParam_name(param_name);
		    	 paramPO.setParam_value(param_value);
		    	 paramPO.setCreate_user_id(user_id);
		    	 paramPO.setCreate_time(IMSUtils.getDateTime());
		    	 paramPO.setModify_user_id(user_id);
		    	 paramPO.setModify_time(IMSUtils.getDateTime());
		    	 paramService.insert(paramPO);
		    	
		     }
		}
		
	}

}
