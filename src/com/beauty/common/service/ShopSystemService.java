package com.beauty.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beauty.common.constant.BeautyCons;
import com.beauty.common.po.ShopLoginLogPO;
import com.beauty.common.po.ShopPostPO;
import com.beauty.common.po.ShopUserPO;
import com.beauty.common.po.ShopWorkPO;
import com.beauty.common.service.ShopLoginLogService;
import com.beauty.common.service.ShopPostService;
import com.beauty.common.service.ShopUserService;
import com.ims.common.core.asset.IMSCodec;
import com.ims.common.core.asset.IMSCons;
import com.ims.common.core.asset.IMSUtils;
import com.ims.common.core.dao.SqlDao;
import com.ims.common.core.id.IMSId;
import com.ims.common.core.matatype.Dto;
import com.ims.common.core.matatype.Dtos;


/**
 * 
 * 类名:com.beauty.shop.service.ShopSystemService
 * 描述:商店登陆有关的服务逻辑处理
 * 编写者:陈骑元
 * 创建时间:2017年5月7日 上午12:11:00
 * 修改说明:
 */
@Service
public class ShopSystemService {
	@Autowired
	private SqlDao sqlDao;
	@Autowired
	private ShopUserService shopUserService;
	@Autowired
	private ShopLoginLogService shopLoginLogService;
	@Autowired
	private ShopPostService shopPostService;
	@Autowired
	private ShopWorkService shopWorkService;
	
	
	
	/**
	 * 验证用户登陆
	 * 
	 * @param inDto
	 * @return
	 */
	public Dto checkLogin(Dto inDto) {
		Dto outDto = Dtos.newDto();
		String account = inDto.getString("account");
		Dto userDto = Dtos.newDto("account", account);
		userDto.put("is_del", IMSCons.IS.NO);
		ShopUserPO userPO =shopUserService.selectOne(userDto);
		if (IMSUtils.isEmpty(userPO)) {
			outDto.setAppCode(IMSCons.WARN);
			outDto.setAppMsg("账号输入错误或不存在，请重新输入。");
			return outDto;
		}
		String status = userPO.getStatus();
		if (BeautyCons.SHOP_USER_STATUS_STOP.equals(status)) {
			outDto.setAppCode(IMSCons.WARN);
			outDto.setAppMsg("该帐号已被禁用，系统拒绝登录，请联系管理员。");
			return outDto;
		}
		if(BeautyCons.USER_TYPE_STAFF.equals(userPO.getUser_type())){
			int count=this.queryShopCount(userPO.getShop_id());
			if(count==0){
				outDto.setAppCode(IMSCons.WARN);
				outDto.setAppMsg("该账号关联店铺已经停用或不存在，请联系管理员。");
				return outDto;
			}
		}
		String password = inDto.getString("password");
		String decryptPassword = IMSCodec.decrypt(userPO.getPassword(), IMSCons.PASSWORD_KEY);
		if (password.equals(decryptPassword)) { // 判断密码是否一致
			//
			
			//查询最近一次登录时间
			Dto lDto=Dtos.newCalcDto( "MAX(login_time) ");
			lDto.put("user_id", userPO.getShop_user_id());
			String last_login_time=shopLoginLogService.calc(lDto);
			
			if(IMSUtils.isEmpty(last_login_time)){
				last_login_time=IMSUtils.getDateTimeStr();
			}
			userPO.setLast_login_time(last_login_time);
			if(BeautyCons.USER_TYPE_OWNER.equals(userPO.getUser_type())){
				userPO.setPost_name("店主");
			}else{
			   Dto workDto=Dtos.newDto();
			   workDto.put("shop_id", userPO.getShop_id());
			   ShopWorkPO shopWorkPO=shopWorkService.selectOne(workDto);
			   if(IMSUtils.isNotEmpty(shopWorkPO)){  //已经设定工作主机
				   String worksn=inDto.getString("worksn");
				   String shopWorkSn=shopWorkPO.getWork_sn();
				   if(!shopWorkSn.equals(worksn)){
					   outDto.setAppCode(IMSCons.WARN);
						outDto.setAppMsg("该账号只能在工作机登陆，请联系管理员。");
						return outDto;
				   }
			   }
			    String post_code=userPO.getPost_code(); //岗位代码
				Dto postDto=Dtos.newDto("post_code", post_code);
				ShopPostPO shopPostPO=shopPostService.selectOne(postDto);
				userPO.setPost_name(shopPostPO.getPost_name());
				userPO.setPost_id(shopPostPO.getPost_id());
			}
			outDto.setAppCode(IMSCons.SUCCESS);
			outDto.put("shopUserPO", userPO);
			return outDto;

		}else{
			outDto.setAppCode(IMSCons.WARN);
			outDto.setAppMsg("密码输入错误，请重新输入。");
			return outDto;
		}
        

	}
	/**
	 * 
	 * 简要说明：保存登陆日志
	 * 编写者：陈骑元
	 * 创建时间：2017年5月7日 上午1:06:27
	 * @param 说明
	 * @return 说明
	 */
	public boolean saveLoginLog(ShopUserPO shopUserPO,String sessionId,String loginIp,String explorer){
	  Dto pDto=Dtos.newDto("session_id", sessionId);
	  ShopLoginLogPO shopLoginLogPO =shopLoginLogService.selectOne(pDto);
	  int row=0;
	  boolean updateFlag=true; //更新标志 true 更新 false 插入
	  if(IMSUtils.isEmpty(shopLoginLogPO)){
		  updateFlag=false;
		  shopLoginLogPO =new  ShopLoginLogPO();
		  shopLoginLogPO.setLog_id(IMSId.appId());
	  }
	  shopLoginLogPO.setLogin_time(IMSUtils.getDateTime());
	  shopLoginLogPO.setAccount(shopUserPO.getAccount());
	  shopLoginLogPO.setUser_id(shopUserPO.getShop_user_id());
	  shopLoginLogPO.setUsername(shopUserPO.getUsername());
	  shopLoginLogPO.setSession_id(sessionId);
	  shopLoginLogPO.setExplorer(explorer);
	  shopLoginLogPO.setLogin_ip(loginIp);
	  if(updateFlag){
		row= shopLoginLogService.updateByKey(shopLoginLogPO);
	  }else{
		row= shopLoginLogService.insert(shopLoginLogPO);
	  }
	  return row>0;
	}
	/**
	 * 
	 * 简要说明：保存登陆日志
	 * 编写者：陈骑元
	 * 创建时间：2017年5月7日 上午1:06:27
	 * @param 说明
	 * @return 说明
	 */
	public boolean updateLoginLog(String sessionId,String exit_type){
		Dto pDto=Dtos.newDto("session_id", sessionId);
		ShopLoginLogPO shopLoginLogPO =shopLoginLogService.selectOne(pDto);
		shopLoginLogPO.setExit_time(IMSUtils.getDateTime());
		shopLoginLogPO.setExit_type(exit_type);
		int row=shopLoginLogService.updateByKey(shopLoginLogPO);
		return row>0;
	}
	/**
	 * 
	 * 简要说明：修改密码
	 * 编写者：陈骑元
	 * 创建时间：2017年5月7日 下午2:24:05
	 * @param 说明
	 * @return 说明
	 */
	public Dto updatePassword(Dto inDto,ShopUserPO shopUserPO){
		Dto outDto=Dtos.newDto();
		String shop_user_id=shopUserPO.getShop_user_id();
		String oldpwd=inDto.getString("oldpwd");
		String oldpassword = IMSCodec.encrypt(oldpwd, IMSCons.PASSWORD_KEY);
		if(shopUserPO.getPassword().equals(oldpassword)){
		   inDto.put("shop_user_id",shop_user_id);
		   outDto=shopUserService.updatePassword(inDto);
		}else{
			outDto.setAppCode(IMSCons.WARN);
			outDto.setAppMsg("操作失败，原始密码不一致。");
		}
		return outDto;
	}
	/**
	 * 
	 * 简要说明：查询店铺相关信息
	 * 编写者：陈骑元
	 * 创建时间：2017年4月17日 下午10:50:01
	 * @param 说明
	 * @return 说明
	 */
	public  Integer queryShopCount(String shop_id){
		
		return (Integer)sqlDao.selectOne("ShopCommonMapper.queryShopCount", shop_id);
	}

} 
