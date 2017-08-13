package com.beauty.common.mapper;

import com.ims.common.core.annotation.Mapper;
import com.ims.common.core.matatype.Dto;

/**
 * 
 * 类名:com.beauty.common.mapper.ShopManageMapper
 * 描述:店铺管理数据入口
 * 编写者:陈骑元
 * 创建时间:2017年5月8日 上午12:57:22
 * 修改说明:
 */
@Mapper
public interface ShopManageMapper {
    
	/**
	 * 
	 * 简要说明：查询门店现金收入情况
	 * 编写者：陈骑元
	 * 创建时间：2017年5月8日 上午1:00:41
	 * @param 说明
	 * @return 说明
	 */
    public Dto queryShopCashSum(Dto pDto);
    /**
     * 
     * 简要说明：门店订单的统计
     * 编写者：陈骑元
     * 创建时间：2017年5月8日 上午1:18:22
     * @param 说明
     * @return 说明
     */
    public int queryShopOrderCount(Dto pDto);
	
}
