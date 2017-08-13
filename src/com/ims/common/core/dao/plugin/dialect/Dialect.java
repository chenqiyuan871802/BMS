package com.ims.common.core.dao.plugin.dialect;

import com.ims.common.core.matatype.Dto;


/**
 * 
 * 类描述： <b>方言抽象</b>
 * 创建人：陈骑元
 * 创建时间：2016-6-3 上午12:16:23
 * 修改人：蓝枫 
 * 修改时间：2016-6-3 上午12:16:23
 * 修改备注： 
 * @version
 */
public interface Dialect {

	/**
	 * 将查询SQL转换为分页SQL
	 * 
	 * @param pSql
	 *            原始SQL
	 * @param pDto
	 *            包含分页信息的参数对象
	 * @return
	 */
	String getPageSql(String pSql, Dto pDto);

	/**
	 * 将sql转换为带排序的SQL
	 * 
	 * @param pSql
	 *            原始SQL
	 * @param order
	 *            排序器
	 * @return
	 */
	String getOrderSql(String pSql, String order);

	/**
	 * 生成查询记录总数的SQL
	 * 
	 * @param pSql
	 *            原始SQL
	 * @return 总记录数的sql
	 */
	String getCountSql(String pSql);

}
