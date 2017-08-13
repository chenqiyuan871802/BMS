package com.ims.common.core.dao.plugin.dialect;

import org.apache.commons.lang3.StringUtils;

import com.ims.common.core.asset.IMSUtils;
import com.ims.common.core.matatype.Dto;


/**
 * 
 * 类描述： MS SQLServer分页方言实现
 * 创建人：陈骑元
 * 创建时间：2016-6-3 上午12:19:32
 * 修改人：蓝枫 
 * 修改时间：2016-6-3 上午12:19:32
 * 修改备注： 
 * @version
 */
public class SqlServerDialect  implements Dialect {

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
		StringBuilder stringBuilder = new StringBuilder("WITH ims_query_ AS (");
		stringBuilder.append("SELECT TOP 100 PERCENT ROW_NUMBER () OVER (ORDER BY CURRENT_TIMESTAMP) AS ims_rn_,");
		pSql = StringUtils.replace(pSql, "select", "SELECT");
		stringBuilder.append(StringUtils.substringAfter(pSql, "SELECT"));
		stringBuilder.append(") SELECT * FROM ims_query_ WHERE ims_rn_ BETWEEN ");
		stringBuilder.append(start).append(" AND ").append(end).append(" ORDER BY ims_rn_");
		return stringBuilder.toString();
	}

	@Override
	public String getOrderSql(String pSql, String order) {
		//用大写ORDER来匹配，约定SQL关键字都用大写。
		if (StringUtils.contains(pSql, "ORDER")) {
			pSql  = pSql+ ", " + order;
		}else {
			pSql  = pSql+ " ORDER BY  " + order;
		}
		return pSql;
	}

	@Override
	public String getCountSql(String pSql) {
		StringBuilder stringBuilder = new StringBuilder("WITH ims_query_ AS (");
		stringBuilder.append(pSql).append(")SELECT COUNT (0) from ims_query_ AS ims_count_");
		return stringBuilder.toString();
	}

}
