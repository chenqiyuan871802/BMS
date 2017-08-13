package com.beauty.job;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.beauty.common.constant.BeautyCons;
import com.beauty.common.po.BagRecordPO;
import com.beauty.common.po.BeautyRecordPO;
import com.beauty.common.po.BusinessOrderPO;
import com.beauty.common.service.RecordCommonService;
import com.ims.common.core.asset.IMSCxt;

/**
 * 
 * 类名:com.beauty.job.BeautyJob
 * 描述:定时作业
 * 编写者:陈骑元
 * 创建时间:2017年7月8日 下午9:24:51
 * 修改说明:
 */
public class BeautyJob {
   
	@Autowired
	private RecordCommonService recordCommonService;
	/***
	 * 
	 * 简要说明：检查过期的颜值
	 * 编写者：陈骑元
	 * 创建时间：2017年7月8日 下午9:47:56
	 * @param 说明
	 * @return 说明
	 */
	public void checkOverdueBeauty(){
		String  overdueDayStr=IMSCxt.getParamValue(BeautyCons.BEAUTY_OVERTIME_KEY);
		int overdueDay=Integer.parseInt(overdueDayStr);
		List<BeautyRecordPO> beautyRecordList=recordCommonService.listOverdueBeauty(overdueDay);
		for(int i=0;i<beautyRecordList.size();i++){
			BeautyRecordPO beautyRecordPO=beautyRecordList.get(i);
			recordCommonService.updateOverdueBeauty(beautyRecordPO);
		}
	}
	/***
	 * 
	 * 简要说明：检查过期的订单
	 * 编写者：陈骑元
	 * 创建时间：2017年7月8日 下午9:47:56
	 * @param 说明
	 * @return 说明
	 */
	public void checkOverdueOrder(){
		String  overdueTimeStr=IMSCxt.getParamValue(BeautyCons.ORDRE_OVERDUE_TIME, "60");
		int overdueTime=Integer.parseInt(overdueTimeStr);
		List<BusinessOrderPO> businessOrderList=recordCommonService.listOverdueOrder(overdueTime);
		for(int i=0;i<businessOrderList.size();i++){
			BusinessOrderPO businessOrderPO=businessOrderList.get(i);
			recordCommonService.updateOverdueOrder(businessOrderPO);
		}
	}
	/***
	 * 
	 * 简要说明：检查过期的订单
	 * 编写者：陈骑元
	 * 创建时间：2017年7月8日 下午9:47:56
	 * @param 说明
	 * @return 说明
	 */
	public void checkOverdueBag(){
	
		List<BagRecordPO> bagRecordList=recordCommonService.listOverdueBag(0);
		for(int i=0;i<bagRecordList.size();i++){
			BagRecordPO bagRecordPO=bagRecordList.get(i);
			recordCommonService.updateOverdueBag(bagRecordPO);
		}
		
	}
	
}
