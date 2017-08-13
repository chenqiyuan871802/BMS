package com.ims.common.core.dao.plugin;

import java.sql.DatabaseMetaData;
import java.sql.SQLException;

import org.apache.commons.lang3.StringUtils;
/**
 * 
 * 类描述：  数据库类型
 * 创建人：陈骑元
 * 创建时间：2016-6-3 上午12:29:08
 * 修改人：蓝枫 
 * 修改时间：2016-6-3 上午12:29:08
 * 修改备注： 
 * @version
 */
public class DBType {

	public static final String ORACLE = "oracle";

	public static final String MYSQL = "mysql";

	public static final String SQLSERVER = "microsoft sql server";

	public static final String POSTGRESQL = "postgresql";
	
	public static final String H2 = "h2";

	/**
	 * 获取数据库产品类型标识
	 * 
	 * @param sysDao
	 * @return
	 * @throws SQLException
	 */
	public static String getDatabaseId(DatabaseMetaData databaseMetaData) throws SQLException {
		String dataBaseID = null;
		String databaseProductName = databaseMetaData.getDatabaseProductName();
		//是否需要处理?
		dataBaseID = databaseProductName;
		return dataBaseID;
	}
	
	/**
	 * 判断是否为Oracle
	 * 
	 * @param databaseMetaData
	 * @return
	 * @throws SQLException 
	 */
	public static Boolean isOracle(DatabaseMetaData databaseMetaData) throws SQLException{
		Boolean flag = false;
		if (StringUtils.containsIgnoreCase(databaseMetaData.getDatabaseProductName(), ORACLE)) {
			flag = true;
		}
		return flag;
	}
	
	/**
	 * 判断是否为SqlServer
	 * 
	 * @param databaseMetaData
	 * @return
	 * @throws SQLException 
	 */
	public static Boolean isSqlServer(DatabaseMetaData databaseMetaData) throws SQLException{
		Boolean flag = false;
		if (StringUtils.containsIgnoreCase(databaseMetaData.getDatabaseProductName(), SQLSERVER)) {
			flag = true;
		}
		return flag;
	}
}
