package com.ims.common.core.dao.plugin.dialect;

import org.apache.commons.lang3.StringUtils;

import com.ims.common.core.asset.IMSUtils;
import com.ims.common.core.matatype.Dto;

/**
 * 
 * 类描述 * 缺省通用分页方言实现
 * 
 * <p>已知可适配此通用分页方言的数据库产品有：Mysql、PostgreSQL、...更多待验证。
 * ： 
 * 创建人：陈骑元
 * 创建时间：2016-6-3 上午12:21:30
 * 修改人：蓝枫 
 * 修改时间：2016-6-3 上午12:21:30
 * 修改备注： 
 * @version
 */
public class DefaultDialect implements Dialect {

	/**
	 * 将查询SQL转换为分页SQL
	 * 
	 * @param pSql
	 *            原始SQL
	 * @param pDto
	 *            包含分页信息的参数对象
	 * @return
	 */
	@Override
	public String getPageSql(String pSql, Dto pDto){
		Integer startInteger = pDto.getPageStart();
		Integer limitInteger = pDto.getPageLimit();
		int start, limit;
		if (IMSUtils.isEmpty(startInteger)) {
			start = 0;
		}else {
			start = startInteger.intValue();
		}
		if (IMSUtils.isEmpty(limitInteger)) {
			limit = Integer.MAX_VALUE;
		}else {
			limit = limitInteger.intValue();
		}
        StringBuilder stringBuilder = new StringBuilder(pSql);
        //步长
        stringBuilder.append(" limit ");
        stringBuilder.append(limit);
        //start相当于SQL中的offset，偏移起始位置
        stringBuilder.append(" offset ");
        stringBuilder.append(start);
        return stringBuilder.toString();
	}

	/**
	 * 将sql转换为带排序的SQL
	 * 
	 * @return 
	 */
	@Override
	public String getOrderSql(String pSql, String order){
		//用大写ORDER来匹配，约定SQL关键字都用大写。
		if (StringUtils.contains(pSql, "ORDER")) {
			pSql  = pSql+ ", " + order;
		}else {
			pSql  = pSql+ " ORDER BY  " + order;
		}
		return pSql;
	}
	
	/**
	 * 生成查询记录总数的SQL
	 * 
	 * @return 总记录数的sql
	 */
	@Override
	public String getCountSql(String pSql){
		String countSql = "SELECT COUNT(0) FROM (" + pSql + ") AS ims_count_";
		return countSql;
	}
	
}
