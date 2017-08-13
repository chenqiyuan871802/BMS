package com.beauty.common.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ims.common.core.annotation.Mapper;
import com.ims.common.core.matatype.Dto;
import com.beauty.common.po.OrderPayPO;

/**
 * 
 * 类描述：<b>订单支付信息[bis_order_pay]数据访问接口</b>
 * 创建人：陈骑元
 * 邮箱：240823329@qq.com
 * 创建时间：2017-05-07 22:47:43
 * 修改人：
 * 修改时间：
 * 修改备注： 
 * @version 1.0
 */
@Mapper
public interface OrderPayMapper {

	/**
	 * 插入一个数据持久化对象(插入字段为传入PO实体的非空属性)
	 * <p> 防止DB字段缺省值需要程序中再次赋值
	 *
	 * @param orderPayPO
	 *            要插入的数据持久化对象
	 * @return 返回影响行数
	 */
	int insert(OrderPayPO orderPayPO);
	
	/**
	 * 插入一个数据持久化对象(含所有字段)
	 * 
	 * @param orderPayPO
	 *            要插入的数据持久化对象
	 * @return 返回影响行数
	 */
	int insertAll(OrderPayPO orderPayPO);

	/**
	 * 根据主键修改数据持久化对象
	 * 
	 * @param orderPayPO
	 *            要修改的数据持久化对象
	 * @return int 返回影响行数
	 */
	int updateByKey(OrderPayPO orderPayPO);

	/**
	 * 根据主键查询并返回数据持久化对象
	 * 
	 * @return OrderPayPO
	 */
	OrderPayPO selectByKey(@Param(value = "pay_id") String pay_id);

	/**
	 * 根据唯一组合条件查询并返回数据持久化对象
	 * 
	 * @return OrderPayPO
	 */
	OrderPayPO selectOne(Dto pDto);

	/**
	 * 根据Dto查询并返回数据持久化对象集合
	 * 
	 * @return List<OrderPayPO>
	 */
	List<OrderPayPO> list(Dto pDto);

	/**
	 * 根据Dto查询并返回分页数据持久化对象集合
	 * 
	 * @return List<OrderPayPO>
	 */
	List<OrderPayPO> listPage(Dto pDto);
		
	/**
	 * 根据Dto模糊查询并返回数据持久化对象集合(字符型字段模糊匹配，其余字段精确匹配)
	 * 
	 * @return List<OrderPayPO>
	 */
	List<OrderPayPO> like(Dto pDto);

	/**
	 * 根据Dto模糊查询并返回分页数据持久化对象集合(字符型字段模糊匹配，其余字段精确匹配)
	 * 
	 * @return List<OrderPayPO>
	 */
	List<OrderPayPO> likePage(Dto pDto);

	/**
	 * 根据主键删除数据持久化对象
	 *
	 * @return 影响行数
	 */
	int deleteByKey(@Param(value = "pay_id") String pay_id);
	
	/**
	 * 批量根据主键删除数据持久化对象
	 *
	 * @return 影响行数
	 */
    int batchDeleteByKey(List<String> pay_idList);
	
	/**
	 * 根据Dto统计行数
	 * 
	 * @param pDto
	 * @return
	 */
	int rows(Dto pDto);
	
	/**
	 * 根据数学表达式进行数学运算
	 * 
	 * @param pDto
	 * @return String
	 */
	String calc(Dto pDto);
	
}
