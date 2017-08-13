package com.ims.common.core.dao.plugin.dialect;

import org.apache.commons.lang3.StringUtils;

import com.ims.common.core.asset.IMSUtils;
import com.ims.common.core.matatype.Dto;

/**
 * 
 * 类描述： <b>Oracle分页方言实现</b>
 * 创建人：陈骑元
 * 创建时间：2016-6-3 上午12:16:59
 * 修改人：蓝枫 
 * 修改时间：2016-6-3 上午12:16:59
 * 修改备注： 
 * @version
 */
public class OracleDialect implements Dialect {

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
	public String getPageSql(String pSql, Dto pDto) {
		Integer startInteger = pDto.getPageStart();
		Integer limitInteger = pDto.getPageLimit();
		int start, limit, end;
		if (IMSUtils.isEmpty(startInteger)) {
			start = 0;
		} else {
			start = startInteger.intValue();
		}
		if (IMSUtils.isEmpty(limitInteger)) {
			limit = Integer.MAX_VALUE;
		} else {
			limit = limitInteger.intValue();
		}
		end = start + limit;
		start = start + 1;
		StringBuilder stringBuilder = new StringBuilder("SELECT * FROM (SELECT page.*, ROWNUM AS ims_rn_ FROM (");
		stringBuilder.append(pSql);
		stringBuilder.append(") page) WHERE ims_rn_ BETWEEN ");
		stringBuilder.append(start).append(" AND ").append(end);
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
		String countSql = "SELECT COUNT(0) AS ims_count_ FROM (" + pSql + ")";
		return countSql;
	}
}
