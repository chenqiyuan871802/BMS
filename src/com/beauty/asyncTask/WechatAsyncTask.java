package com.beauty.asyncTask;

import java.io.File;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.beauty.common.constant.BeautyCons;
import com.beauty.common.po.CustomUserPO;
import com.beauty.common.service.CustomUserService;
import com.beauty.common.utils.IdUtil;
import com.beauty.wechat.model.WechatUser;
import com.beauty.wechat.util.WechatCxt;
import com.ims.common.core.asset.FileOperation;
import com.ims.common.core.asset.IMSCons;
import com.ims.common.core.asset.IMSUtils;
import com.ims.common.core.matatype.Dto;
import com.ims.common.core.matatype.Dtos;

/**
 * 
 * 类名:com.beauty.wechat.task.WechatAsyncTask
 * 描述:微信异步处理任务
 * 编写者:陈骑元
 * 创建时间:2017年4月30日 上午12:20:23
 * 修改说明:
 */
@Component
public class WechatAsyncTask {
	
	private static Log log = LogFactory.getLog(WechatAsyncTask.class);
	/**
	 * 消费者业务逻辑处理类
	 */
	@Autowired
	private CustomUserService customUserService;
	/**
	 * 
	 * 简要说明：异步下载网络文件
	 * 编写者：陈骑元
	 * 创建时间：2017年4月30日 上午10:34:11
	 * @param 说明 fileUrl文件URL地址 saveFilePath //保存文件路径
	 * @return 说明
	 */
	@Async
	public void asyncDownloadUrlFile(String fileUrl,String saveFilePath){
		
		FileOperation.downloadUrlFile(fileUrl,saveFilePath);
		
	}
	
	/**
	 * 
	 * 简要说明：异步处理订阅事件 保存顾客信息
	 * 编写者：陈骑元
	 * 创建时间：2017年4月30日 上午11:06:38
	 * @param 说明 rootPath//项目部署地址
	 * @return 说明
	 */
	@Async
	public void asyncSubscribeEvent(Dto inDto,String rootPath){
		//订阅号微信号的openid
		String openid =inDto.getString("FromUserName");
		//获取开发者信息
		WechatUser wechatUser=WechatCxt.getUserInfo(openid); 
		if(IMSUtils.isNotEmpty(wechatUser)){
			
			String headImageUrl=wechatUser.getHeadimgurl(); //获取用户头像URL
			CustomUserPO customUserPO =null;
			Dto pDto=Dtos.newDto();
			pDto.put("openid", openid);
			pDto.put("is_del", IMSCons.IS.NO); //查询之前是否关注过，如果关注过,测进行更新操作
			CustomUserPO customUserPOOld=customUserService.selectOne(pDto);
			boolean updateFlag=false;
			if(IMSUtils.isNotEmpty(customUserPOOld)){  //如果已经关注过，或者退订过
				customUserPO=customUserPOOld;
				updateFlag=true;
			}else{
				customUserPO=new CustomUserPO();
				String custom_user_id=IdUtil.createCustomUserId();
				customUserPO.setCustom_user_id(custom_user_id);
			}
			if(IMSUtils.isNotEmpty(headImageUrl)){  //微信用户头像URL信息，如果不为空测进行头像下载
				String fileName=customUserPO.getCustom_user_id()+".png";
				String folderPath = rootPath+File.separator+BeautyCons.CUSTOM_IMAGE_URL;
				FileOperation.createFolder(folderPath);
				String saveFilePath=folderPath+File.separator+fileName;
				FileOperation.downloadUrlFile(headImageUrl,saveFilePath);//下载头像信息
				customUserPO.setPhoto(BeautyCons.CUSTOM_IMAGE_URL+fileName); //存储用户照片URL地址
				
			}
			 customUserPO.setOpenid(wechatUser.getOpenid());
			 customUserPO.setNikename(wechatUser.getNickname());
			 customUserPO.setSex(wechatUser.getSex()+"");
			 customUserPO.setRemark(wechatUser.getRemark());
			 customUserPO.setAddress(wechatUser.getProvince()+wechatUser.getCity());
			 customUserPO.setEnroll_mode(BeautyCons.ENROLL_MODE_APP);
		     customUserPO.setEnroll_time(IMSUtils.getDateTime());
			 customUserPO.setIs_del(IMSCons.IS.NO);
			 customUserPO.setWechat_status(BeautyCons.WECHAT_STATUS_YES);
			 if(updateFlag){
				 if(IMSUtils.isEmpty(customUserPO.getUsername()))	{
					 customUserPO.setUsername(wechatUser.getNickname());
				 }
				 customUserService.updateByKey(customUserPO);
			 }else{
				 customUserPO.setUsername(wechatUser.getNickname());
				 customUserService.insert(customUserPO);
			 }
			log.info("异步处理微信用户订阅信息成功:顾客的会员编号["+customUserPO.getCustom_user_id()+"],微信号标识["+openid+"]");
		}else{
			log.error("异步处理微信用户订阅信息出错，原因无法获取到用户的基本信息");
		}
		
		
	}
	/**
	 * 
	 * 简要说明：异步处理微信退订
	 * 编写者：陈骑元
	 * 创建时间：2017年5月1日 下午11:24:04
	 * @param 说明
	 * @return 说明
	 */
	@Async
	public void asyncUnSubscribeEvent(Dto inDto){
		//订阅号微信号的openid
		String openid =inDto.getString("FromUserName");
		if(IMSUtils.isNotEmpty(openid)){
			Dto pDto=Dtos.newDto();
			pDto.put("openid", openid);
			pDto.put("is_del", IMSCons.IS.NO); 
			CustomUserPO customUserPO=customUserService.selectOne(pDto);
			if(IMSUtils.isNotEmpty(customUserPO)){
				customUserPO.setWechat_status(BeautyCons.WECHAT_STATUS_EXIT);
			    customUserService.updateByKey(customUserPO);
			}else{
				log.info("微信号标识["+openid+"]的用户在系统中不存在");
			}
		}else{
			log.error("异步处理微信用户退订信息出错，原因无法获取到用户的标识号");
		}
		
	}
	
	

}
