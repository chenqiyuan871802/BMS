package com.ims.common.core.dao.plugin;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.executor.resultset.FastResultSetHandler;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ResultMap;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;

import com.ims.common.core.asset.IMSReflector;
import com.ims.common.core.matatype.Dto;
import com.ims.common.core.matatype.Dtos;
import com.ims.common.core.matatype.impl.HashDto;
/**
 * 
 * 类描述：  * ResultSet拦截器
 * <p>
 * 用于处理Oracle等DB当resultType映射为Map及其子类时的大小写敏感问题。<br>
 * 只会拦截select语句。缺陷：调用存储过程返回游标结果集的时候，如果结果集里为Dto对象的话，其Key不能转换。
 * 创建人：陈骑元
 * 创建时间：2016-6-3 上午12:30:09
 * 修改人：蓝枫 
 * 修改时间：2016-6-3 上午12:30:09
 * 修改备注： 
 * @version
 */
@Intercepts(@Signature(method = "handleResultSets", type = ResultSetHandler.class, args = { Statement.class }))
public class ResultSetHandlerPlugin implements Interceptor {

	

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		// 其实现在ResultSetHandler只有FastResultSetHandler这一种实现
		FastResultSetHandler fastResultSetHandler = (FastResultSetHandler) invocation.getTarget();
		// 可以动态获取实例对象的变量
		// ParameterHandler parameterHandler = (ParameterHandler)
		// AOSReflector.getFieldValue(fastResultSetHandler,"parameterHandler");
		// Object parameterObj = parameterHandler.getParameterObject();
		MappedStatement mappedStatement = (MappedStatement) IMSReflector.getFieldValue(fastResultSetHandler,
				"mappedStatement");
		String dataBaseID = mappedStatement.getConfiguration().getDatabaseId();
		// 只对Oracle、H2进行拦截处理，目前已知需要处理字段大小写的数据库
		if (StringUtils.equalsIgnoreCase(dataBaseID,DBType.ORACLE) || StringUtils.equalsIgnoreCase(dataBaseID, DBType.H2)) {
			List<Dto> outDtos = new ArrayList<Dto>();
			List<ResultMap> rms = mappedStatement.getResultMaps();
			ResultMap rm = rms != null && rms.size() > 0 ? rms.get(0) : null;
			String type = rm != null && rm.getType() != null ? rm.getType().getName() : "";
			if (StringUtils.equalsIgnoreCase(type, HashDto.class.getName())) {
				// 只对resultType="Dto"的SQL映射做结果集的处理
				// ResultSetHandler.class.handleResultSets()的第一个参数。看注解。
				Statement stmt = (Statement) invocation.getArgs()[0];
				ResultSet rs = stmt.getResultSet();
				ResultSetMetaData rsmd = rs.getMetaData();
				int columns = rsmd.getColumnCount();
				while (rs.next()) {
					Dto rowDto = Dtos.newDto();
					for (int i = 1; i <= columns; i++) {
						rowDto.put(StringUtils.lowerCase(rsmd.getColumnLabel(i)), rs.getObject(i));
					}
					outDtos.add(rowDto);
				}
				if (rs != null) {
					rs.close();
				}
				// 这里返回的就是Dao接口调用得到的结果
				return outDtos;
			}
		}
		// 如果没有进行拦截处理，则执行默认逻辑
		return invocation.proceed();
	}

	@Override
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(Properties properties) {
		//properties可以接收配置文件传过来的参数
	}

}
