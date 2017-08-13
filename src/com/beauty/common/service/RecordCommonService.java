package com.beauty.common.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.beauty.common.constant.BeautyCons;
import com.beauty.common.mapper.BagRecordMapper;
import com.beauty.common.mapper.BeautyRecordMapper;
import com.beauty.common.mapper.BusinessOrderMapper;
import com.beauty.common.mapper.CouponActiveMapper;
import com.beauty.common.mapper.CouponRecordMapper;
import com.beauty.common.mapper.CustomUserMapper;
import com.beauty.common.mapper.ProjectRecordMapper;
import com.beauty.common.po.BagRecordPO;
import com.beauty.common.po.BeautyRecordPO;
import com.beauty.common.po.BusinessOrderPO;
import com.beauty.common.po.CouponActivePO;
import com.beauty.common.po.CouponRecordPO;
import com.beauty.common.po.CustomUserPO;
import com.beauty.common.po.ProjectRecordPO;
import com.ims.common.core.asset.IMSCons;
import com.ims.common.core.asset.IMSUtils;
import com.ims.common.core.dao.SqlDao;
import com.ims.common.core.id.IMSId;
import com.ims.common.core.matatype.Dto;
import com.ims.common.core.matatype.Dtos;

/**
 * 
 * 类名:com.beauty.common.service.RecordCommonService
 * 描述:流水记录通用处理
 * 编写者:陈骑元
 * 创建时间:2017年5月22日 上午12:43:11
 * 修改说明:
 */
@Service
public class RecordCommonService {
    
	@Autowired
	private SqlDao sqlDao;
	/**
	 * 礼券兑换记录数据库操作
	 */
	@Autowired
    private  CouponRecordMapper couponRecordMapper;
	/**
	 * 礼券活动数据库操作
	 */
   @Autowired  
	private  CouponActiveMapper couponActiveMapper;
   @Autowired  
   private  BeautyRecordMapper beautyRecordMapper;
   @Autowired
   private  CustomUserMapper customUserMapper;
   @Autowired
   private  BusinessOrderMapper businessOrderMapper;
   @Autowired
   private  ProjectRecordMapper projectRecordMapper;
   @Autowired
   private  BagRecordMapper bagRecordMapper;
	/**
	 * 
	 * 简要说明：查询个人统计颜值
	 * 编写者：陈骑元
	 * 创建时间：2017年5月22日 上午12:45:13
	 * @param 说明
	 * @return 说明
	 */
	public Dto queryBeautySum(Dto pDto){
		
		return sqlDao.selectDto("RecordCommonMapper.queryBeautySum", pDto);
	}
	/**
	 * 
	 * 简要说明：分页查询
	 * 编写者：陈骑元
	 * 创建时间：2017年5月23日 上午12:43:53
	 * @param 说明
	 * @return 说明
	 */
	public List<Dto> queryBeautyRecordPage(Dto pDto){
		
		return sqlDao.list("RecordCommonMapper.queryBeautyRecordPage", pDto);
	}
	/**
	 * 
	 * 简要说明：查询过期的颜值
	 * 编写者：陈骑元
	 * 创建时间：2017年5月23日 上午12:43:53
	 * @param 说明
	 * @return 说明
	 */
	public List<BeautyRecordPO> listOverdueBeauty(int overdueDay){
		
		return sqlDao.list("RecordCommonMapper.listOverdueBeauty", overdueDay);
	}
	/**
	 * 
	 * 简要说明：处理预约过期订单
	 * 编写者：陈骑元
	 * 创建时间：2017年5月23日 上午12:43:53
	 * @param 说明
	 * @return 说明
	 */
	public List<BusinessOrderPO> listOverdueOrder(int overdueTime){
		
		return sqlDao.list("RecordCommonMapper.listOverdueOrder", overdueTime);
	}
	/**
	 * 
	 * 简要说明：处理预约过期 礼包 
	 * 编写者：陈骑元
	 * 创建时间：2017年5月23日 上午12:43:53
	 * @param 说明
	 * @return 说明
	 */
	public List<BagRecordPO> listOverdueBag(int overdueTime){
		
		return sqlDao.list("RecordCommonMapper.listOverdueBag", overdueTime);
	}
	
	/**
	 * 
	 * 简要说明：更新过期颜值
	 * 编写者：陈骑元
	 * 创建时间：2017年5月22日 下午10:24:03
	 * @param 说明
	 * @return 说明
	 */
	@Transactional
	public void updateOverdueBeauty(BeautyRecordPO beautyRecordPO){
	    String custom_user_id=beautyRecordPO.getCustom_user_id();
	    if(IMSUtils.isNotEmpty(custom_user_id)){
	    	CustomUserPO customUserPO=customUserMapper.selectByKey(custom_user_id);
	    	if(IMSUtils.isNotEmpty(customUserPO)){
	    		int remain_num=beautyRecordPO.getRemain_num();
	    		int num=customUserPO.getBeauty_num()-remain_num;
	    		customUserPO.setBeauty_num(num);
	    		customUserMapper.updateByKey(customUserPO);
	    	}
	    }
	    beautyRecordPO.setVaild_status(BeautyCons.VAILD_STATUS_NO);
	    beautyRecordMapper.updateByKey(beautyRecordPO);
	}
	/**
	 * 
	 * 简要说明：更新过期 订单
	 * 编写者：陈骑元
	 * 创建时间：2017年5月22日 下午10:24:03
	 * @param 说明
	 * @return 说明
	 */
	@Transactional
	public void updateOverdueOrder(BusinessOrderPO businessOrderPO){
		 businessOrderPO.setOrder_status(BeautyCons.ORDER_STATUS_OVERDUE);
		 businessOrderPO.setCancel_time(IMSUtils.getDateTime());
		 businessOrderMapper.updateByKey(businessOrderPO);
	}
	/**
	 * 
	 * 简要说明：更新过期 订单
	 * 编写者：陈骑元
	 * 创建时间：2017年5月22日 下午10:24:03
	 * @param 说明
	 * @return 说明
	 */
	@Transactional
	public void updateOverdueBag(BagRecordPO bagRecordPO){
	    Dto pDto=Dtos.newDto();
	    pDto.put("bag_record_id", bagRecordPO.getRecord_id());
	    pDto.put("project_status", BeautyCons.PROJECT_STATUS_NOUSE);
		List<ProjectRecordPO> projectRecordList=projectRecordMapper.list(pDto);
		for(ProjectRecordPO projectPO:projectRecordList){
			projectPO.setProject_status(BeautyCons.PROJECT_STATUS_OVERDUE);
			projectRecordMapper.updateByKey(projectPO);
		}
		bagRecordPO.setStatus(BeautyCons.VAILD_STATUS_NO);
		bagRecordMapper.updateByKey(bagRecordPO);
	}
	/**
	 * 
	 * 简要说明：兑换颜值
	 * 编写者：陈骑元
	 * 创建时间：2017年5月22日 下午10:24:03
	 * @param 说明
	 * @return 说明
	 */
	@Transactional
	public Dto saveExchangeBeauty(String cdkey,String custom_user_id){
		Dto outDto=Dtos.newDto();
		Dto pDto=Dtos.newDto("cdkey", cdkey);
		CouponRecordPO couponRecordPO=couponRecordMapper.selectOne(pDto);
		if(IMSUtils.isNotEmpty(couponRecordPO)){
			String  status=couponRecordPO.getStatus();
			if(BeautyCons.BOND_STATUS_NO.equals(status)){//兑换码有效  
				CustomUserPO customUserPO=customUserMapper.selectByKey(custom_user_id);
				Date currentTime=IMSUtils.getDateTime();
				String active_id=couponRecordPO.getActive_id() ;//获取礼券活动编号
				CouponActivePO couponActivePO=couponActiveMapper.selectByKey(active_id);
				int bond_num= couponActivePO.getBond_num();
				BeautyRecordPO beautyRecordPO=new BeautyRecordPO();
				beautyRecordPO.setRecord_id(IMSId.appId());
				beautyRecordPO.setCustom_user_id(custom_user_id);
				beautyRecordPO.setOrder_id(active_id);
				beautyRecordPO.setCdkey(cdkey);
				beautyRecordPO.setGive_num(0);
				beautyRecordPO.setRemain_num(0);
				beautyRecordPO.setBeauty_num(bond_num); //颜值个数
				beautyRecordPO.setRecord_type(BeautyCons.BEAUTY_RECORD_TYPE_DH);
				beautyRecordPO.setVaild_status(BeautyCons.VAILD_STATUS_YES);
				beautyRecordPO.setPay_time(currentTime);
				beautyRecordPO.setTotal_money(0d);
				beautyRecordPO.setSinge_price(0d);
				beautyRecordMapper.insert(beautyRecordPO);
				couponRecordPO.setMobile(customUserPO.getMobile());
				couponRecordPO.setExchange_time(currentTime);
				couponRecordPO.setStatus(BeautyCons.BOND_STATUS_YES);
				couponRecordMapper.updateByKey(couponRecordPO);
			
				CustomUserPO customUserUpdate=new CustomUserPO();
				customUserUpdate.setCustom_user_id(customUserPO.getCustom_user_id());
				int beauty_num=customUserPO.getBeauty_num()+bond_num;
				customUserUpdate.setBeauty_num(beauty_num);
				customUserMapper.updateByKey(customUserUpdate);
				outDto.setAppCode(IMSCons.SUCCESS);
				 outDto.setAppMsg("兑换成功，获得"+bond_num+"个颜值");
			}else if(BeautyCons.BOND_STATUS_YES.equals(status)){
				 outDto.setAppCode(IMSCons.ERROR);
				 outDto.setAppMsg("该兑换码已兑换");
			}else{
				 outDto.setAppCode(IMSCons.ERROR);
				 outDto.setAppMsg("兑换码已过期");
			}
		}else{
			 outDto.setAppCode(IMSCons.ERROR);
			 outDto.setAppMsg("兑换码不正确");
		}
		return outDto;
	}
}
