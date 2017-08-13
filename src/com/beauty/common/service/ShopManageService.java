package com.beauty.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beauty.common.mapper.ShopManageMapper;
import com.ims.common.core.matatype.Dto;
/**
 * 
 * 类名:com.beauty.shop.service.ShopManageService
 * 描述:店铺管理相关业务逻辑
 * 编写者:陈骑元
 * 创建时间:2017年5月7日 下午11:10:13
 * 修改说明:
 */
@Service
public class ShopManageService {
	/**
	 *店铺管理通用数据库接口
	 */
	@Autowired
	private ShopManageMapper shopManageMapper;
	
	
	
	/**
	 * 
	 * 简要说明：查询门店现金收入情况
	 * 编写者：陈骑元
	 * 创建时间：2017年5月8日 上午1:00:41
	 * @param 说明
	 * @return 说明
	 */
    public Dto queryShopCashSum(Dto pDto){
    	
    	return shopManageMapper.queryShopCashSum(pDto);
    }
    /**
     * 
     * 简要说明：门店订单的统计
     * 编写者：陈骑元
     * 创建时间：2017年5月8日 上午1:17:37
     * @param 说明
     * @return 说明
     */
	public int  queryShopOrderCount(Dto pDto){
		
		return shopManageMapper.queryShopOrderCount(pDto);
	}
}
