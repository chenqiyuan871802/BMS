package com.beauty.common.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ims.common.core.annotation.Mapper;
import com.ims.common.core.matatype.Dto;
import com.beauty.common.po.BusinessOrderPO;

/**
 * 
 * 类描述：<b>营业订单[bis_business_order]数据访问接口</b>
 * 创建人：陈骑元
 * 邮箱：240823329@qq.com
 * 创建时间：2017-05-08 23:48:18
 * 修改人：
 * 修改时间：
 * 修改备注： 
 * @version 1.0
 */
@Mapper
public interface BusinessOrderMapper {

	/**
	 * 插入一个数据持久化对象(插入字段为传入PO实体的非空属性)
	 * <p> 防止DB字段缺省值需要程序中再次赋值
	 *
	 * @param businessOrderPO
	 *            要插入的数据持久化对象
	 * @return 返回影响行数
	 */
	int insert(BusinessOrderPO businessOrderPO);
	
	/**
	 * 插入一个数据持久化对象(含所有字段)
	 * 
	 * @param businessOrderPO
	 *            要插入的数据持久化对象
	 * @return 返回影响行数
	 */
	int insertAll(BusinessOrderPO businessOrderPO);

	/**
	 * 根据主键修改数据持久化对象
	 * 
	 * @param businessOrderPO
	 *            要修改的数据持久化对象
	 * @return int 返回影响行数
	 */
	int updateByKey(BusinessOrderPO businessOrderPO);

	/**
	 * 根据主键查询并返回数据持久化对象
	 * 
	 * @return BusinessOrderPO
	 */
	BusinessOrderPO selectByKey(@Param(value = "order_id") String order_id);

	/**
	 * 根据唯一组合条件查询并返回数据持久化对象
	 * 
	 * @return BusinessOrderPO
	 */
	BusinessOrderPO selectOne(Dto pDto);

	/**
	 * 根据Dto查询并返回数据持久化对象集合
	 * 
	 * @return List<BusinessOrderPO>
	 */
	List<BusinessOrderPO> list(Dto pDto);

	/**
	 * 根据Dto查询并返回分页数据持久化对象集合
	 * 
	 * @return List<BusinessOrderPO>
	 */
	List<BusinessOrderPO> listPage(Dto pDto);
		
	/**
	 * 根据Dto模糊查询并返回数据持久化对象集合(字符型字段模糊匹配，其余字段精确匹配)
	 * 
	 * @return List<BusinessOrderPO>
	 */
	List<BusinessOrderPO> like(Dto pDto);

	/**
	 * 根据Dto模糊查询并返回分页数据持久化对象集合(字符型字段模糊匹配，其余字段精确匹配)
	 * 
	 * @return List<BusinessOrderPO>
	 */
	List<BusinessOrderPO> likePage(Dto pDto);

	/**
	 * 根据主键删除数据持久化对象
	 *
	 * @return 影响行数
	 */
	int deleteByKey(@Param(value = "order_id") String order_id);
	
	/**
	 * 批量根据主键删除数据持久化对象
	 *
	 * @return 影响行数
	 */
    int batchDeleteByKey(List<String> order_idList);
	
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
