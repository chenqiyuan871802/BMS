package com.beauty.common.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beauty.common.po.ShopUserPO;
import com.google.common.collect.Lists;
import com.ims.common.core.asset.IMSCons;
import com.ims.common.core.asset.IMSCxt;
import com.ims.common.core.asset.IMSUtils;
import com.ims.common.core.dao.SqlDao;
import com.ims.common.core.dao.plugin.DBType;
import com.ims.common.core.matatype.Dto;
import com.ims.common.core.matatype.Dtos;
import com.ims.common.system.asset.DicCons;
import com.ims.common.system.modules.po.MenuPO;
import com.ims.common.system.modules.po.TreeModel;
/**
 * 
 * 类名:com.beauty.common.service.FinanceService
 * 描述:财务管理业务逻辑
 * 编写者:陈骑元
 * 创建时间:2017年5月29日 下午11:21:30
 * 修改说明:
 */
@Service
public class FinanceService {
    
	@Autowired
	private SqlDao sqlDao;
	/**
	 * 
	 * 简要说明：现金流水
	 * 编写者：陈骑元
	 * 创建时间：2017年5月29日 下午11:22:19
	 * @param 说明
	 * @return 说明
	 */
	public List<Dto> listCashRecordPage(Dto pDto){
		
		return sqlDao.list("FinanceMapper.listCashRecordPage", pDto);
	}
	/**
	 * 
	 * 简要说明：现金流水
	 * 编写者：陈骑元
	 * 创建时间：2017年5月29日 下午11:22:19
	 * @param 说明
	 * @return 说明
	 */
	public List<Dto> listCashRecord(Dto pDto){
		
		return sqlDao.list("FinanceMapper.listCashRecord", pDto);
	}
	/**
	 * 
	 * 简要说明：颜值流水
	 * 编写者：陈骑元
	 * 创建时间：2017年5月29日 下午11:22:19
	 * @param 说明
	 * @return 说明
	 */
	public List<Dto> listBeautyRecordPage(Dto pDto){
		
		return sqlDao.list("FinanceMapper.listBeautyRecordPage", pDto);
	}
	/**
	 * 
	 * 简要说明：颜值流水
	 * 编写者：陈骑元
	 * 创建时间：2017年5月29日 下午11:22:19
	 * @param 说明
	 * @return 说明
	 */
	public List<Dto> listBeautyRecord(Dto pDto){
		
		return sqlDao.list("FinanceMapper.listBeautyRecord", pDto);
	}
	/**
	 * 
	 * 简要说明：查询礼包分享记录
	 * 编写者：陈骑元
	 * 创建时间：2017年6月27日 上午1:32:12
	 * @param 说明
	 * @return 说明
	 */
	public List<Dto> listShareBagPage(Dto pDto){
		
		return sqlDao.list("FinanceMapper.listShareBagPage", pDto);
	}
	
	/**
	 * 
	 * 简要说明：查询礼包分享记录
	 * 编写者：陈骑元
	 * 创建时间：2017年6月27日 上午1:32:12
	 * @param 说明
	 * @return 说明
	 */
	public List<Dto> listShareBag(Dto pDto){
		
		return sqlDao.list("FinanceMapper.listShareBag", pDto);
	}
	
	/**
	 * 
	 * 简要说明：查询现金统计
	 * 编写者：陈骑元
	 * 创建时间：2017年6月27日 上午1:32:12
	 * @param 说明
	 * @return 说明
	 */
	public Dto queryCashGroup(Dto pDto){
		
		return sqlDao.selectDto("FinanceMapper.queryCashGroup", pDto);
	}
	
	/**
	 * 
	 * 简要说明：查询消耗收入
	 * 编写者：陈骑元
	 * 创建时间：2017年6月27日 上午1:32:12
	 * @param 说明
	 * @return 说明
	 */
	public Dto queryExtendGroup(Dto pDto){
		
		return sqlDao.selectDto("FinanceMapper.queryExtendGroup", pDto);
	}
	/**
	 * 
	 * 简要说明：查询消耗收入
	 * 编写者：陈骑元
	 * 创建时间：2017年6月27日 上午1:32:12
	 * @param 说明
	 * @return 说明
	 */
	public List<Dto> listPlatformSum(Dto pDto){
		
		return sqlDao.list("FinanceMapper.listPlatformSum", pDto);
	}
	
}
