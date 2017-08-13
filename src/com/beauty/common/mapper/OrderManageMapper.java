package com.beauty.common.mapper;

import java.util.List;

import com.beauty.common.po.BeautyRecordPO;
import com.ims.common.core.annotation.Mapper;
import com.ims.common.core.matatype.Dto;

/**
 * 
 * 类名:com.beauty.common.mapper.OrderManageMapper
 * 描述:订单管理通用入口
 * 编写者:陈骑元
 * 创建时间:2017年5月8日 上午12:58:42
 * 修改说明:
 */
@Mapper
public interface OrderManageMapper {
    
	/**
	 * 
	 * 简要说明：查询营业记录
	 * 编写者：陈骑元
	 * 创建时间：2017年5月9日 上午1:16:00
	 * @param 说明
	 * @return 说明
	 */
	public List<Dto> listBusinessRecordPage(Dto pDto);
	/**
	 * 
	 * 简要说明：查询所有营业记录
	 * 编写者：陈骑元
	 * 创建时间：2017年5月30日 下午4:38:49
	 * @param 说明
	 * @return 说明
	 */
	public List<Dto> listAllRecordPage(Dto pDto);
	/**
	 * 
	 * 简要说明：查询微服务项目
	 * 编写者：陈骑元
	 * 创建时间：2017年7月23日 上午9:33:21
	 * @param 说明
	 * @return 说明
	 */
	public List<Dto> listNoServerRecordPage(Dto pDto);
	/**
	 * 
	 * 简要说明：查询所有营业记录
	 * 编写者：陈骑元
	 * 创建时间：2017年5月30日 下午4:38:49
	 * @param 说明
	 * @return 说明
	 */
	public List<Dto> listAllRecord(Dto pDto);
	/**
	 * 
	 * 简要说明：导出已经完成营业记录
	 * 编写者：陈骑元
	 * 创建时间：2017年5月30日 下午4:38:49
	 * @param 说明
	 * @return 说明
	 */
	public List<Dto> listShopRecordPage(Dto pDto);
	/**
	 * 
	 * 简要说明：导出已经完成营业记录
	 * 编写者：陈骑元
	 * 创建时间：2017年5月30日 下午4:38:49
	 * @param 说明
	 * @return 说明
	 */
	public List<Dto> listShopRecord(Dto pDto);
	/**
	 * 
	 * 简要说明：查询财务总况
	 * 编写者：陈骑元
	 * 创建时间：2017年6月14日 下午7:59:16
	 * @param 说明
	 * @return 说明
	 */
	public List<Dto> listFinancePage(Dto pDto);
	/**
	 * 
	 * 简要说明：财务总况表
	 * 编写者：陈骑元
	 * 创建时间：2017年6月15日 上午12:34:59
	 * @param 说明
	 * @return 说明
	 */
	public List<Dto> listFinance(Dto pDto);
	/**
	 * 
	 * 简要说明：财务月收入统计
	 * 编写者：陈骑元
	 * 创建时间：2017年6月15日 上午12:34:59
	 * @param 说明
	 * @return 说明
	 */
	public Dto queryFinanceTotal(Dto pDto);
	/**
	 * 
	 * 简要说明：查询护理订单
	 * 编写者：陈骑元
	 * 创建时间：2017年6月11日 上午11:34:23
	 * @param 说明
	 * @return 说明
	 */
	public List<Dto>  listNurseOrderPage(Dto pDto);
	/**
	 * 
	 * 简要说明：查询购买礼包订单
	 * 编写者：陈骑元
	 * 创建时间：2017年6月11日 上午11:34:23
	 * @param 说明
	 * @return 说明
	 */
	public List<Dto>  listBagOrderPage(Dto pDto);
	/**
	 * 
	 * 简要说明查询订单详情
	 * 编写者：陈骑元
	 * 创建时间：2017年5月10日 上午12:38:43
	 * @param 说明
	 * @return 说明
	 */
	public Dto queryOrderDetail(String order_id);
	/**
	 * 
	 * 简要说明：查询预约的订单
	 * 编写者：陈骑元
	 * 创建时间：2017年5月10日 上午1:05:38
	 * @param 说明
	 * @return 说明
	 */
	public List<Dto> listSubscribeOrderPage(Dto pDto);
	/**
	 * 
	 * 简要说明：查询有效的美研币
	 * 编写者：陈骑元
	 * 创建时间：2017年6月13日 下午8:36:02
	 * @param 说明
	 * @return 说明
	 */
	public List<BeautyRecordPO> listVaildBeauty(Dto pDto);
	/**
	 * 
	 * 简要说明：查询我的礼包
	 * 编写者：陈骑元
	 * 创建时间：2017年6月20日 下午10:37:47
	 * @param 说明
	 * @return 说明
	 */
	public List<Dto> listMyBag(Dto pDto);
	/**
	 * 
	 * 简要说明：查询我的项目
	 * 编写者：陈骑元
	 * 创建时间：2017年6月21日 下午10:38:44
	 * @param 说明
	 * @return 说明
	 */
	public List<Dto> listMyProjectPage(Dto pDto);
	/**
	 * 
	 * 简要说明：查询我的项目
	 * 编写者：陈骑元
	 * 创建时间：2017年6月21日 下午10:38:44
	 * @param 说明
	 * @return 说明
	 */
	public List<Dto> listProject(Dto pDto);
	/**
	 * 
	 * 简要说明：查询我的项目
	 * 编写者：陈骑元
	 * 创建时间：2017年6月21日 下午10:38:44
	 * @param 说明
	 * @return 说明
	 */
	public List<Dto> listMyProject(Dto pDto);
	/**
	 * 
	 * 简要说明：
	 * 编写者：陈骑元
	 * 创建时间：2017年6月21日 上午12:41:42
	 * @param 说明
	 * @return 说明
	 */
	public List<Dto> listBagProject(String bag_id);
	 /**
	  * 
	  * 简要说明：查询消费者与店铺关联信息
	  * 编写者：陈骑元
	  * 创建时间：2017年6月1日 上午2:48:32
	  * @param 说明
	  * @return 说明
	  */
	 Dto queryShopCustom(Dto pDto);
	 /**
	  * 
	  * 简要说明：查询礼包项目信息
	  * 编写者：陈骑元
	  * 创建时间：2017年6月24日 下午6:10:40
	  * @param 说明
	  * @return 说明
	  */
	 Dto queryBagProject(Dto pDto);
	 /**
	  * 
	  * 简要说明：查询可支付的项目
	  * 编写者：陈骑元
	  * 创建时间：2017年6月24日 下午4:07:46
	  * @param 说明
	  * @return 说明
	  */
	 List<Dto> queryPayProject(Dto pDto);
	 /**
	  * 
	  * 简要说明：保存消费者与店铺关联信息
	  * 编写者：陈骑元
	  * 创建时间：2017年6月1日 上午2:48:57
	  * @param 说明
	  * @return 说明
	  */
	 int saveShopCustom(Dto pDto);
	 /**
	  * 
	  * 简要说明：更新店铺和消费者关联信息
	  * 编写者：陈骑元
	  * 创建时间：2017年6月1日 上午2:49:19
	  * @param 说明
	  * @return 说明
	  */
	 int updateShopCustom(Dto pDto);
	 /**
	  * 
	  * 简要说明：查询预约时间
	  * 编写者：陈骑元
	  * 创建时间：2017年6月29日 下午10:13:06
	  * @param 说明
	  * @return 说明
	  */
	 List<Dto> queryGroupCountTime(Dto pDto);
	 /**
	  * 
	  * 简要说明：员工工资统计
	  * 编写者：陈骑元
	  * 创建时间：2017年7月16日 下午10:59:33
	  * @param 说明
	  * @return 说明
	  */
	 List<Dto> listShopUserCountPage(Dto pDto);
	 /**
	  * 
	  * 简要说明：员工工资统计
	  * 编写者：陈骑元
	  * 创建时间：2017年7月16日 下午10:59:33
	  * @param 说明
	  * @return 说明
	  */
	 List<Dto> listShopUserCount(Dto pDto);
	 /**
	  * 
	  * 简要说明：查询员工总汇总信息
	  * 编写者：陈骑元
	  * 创建时间：2017年7月16日 下午11:47:54
	  * @param 说明
	  * @return 说明
	  */
	 Dto queryShopSum(Dto pDto);
	
	
}
