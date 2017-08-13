package com.ims.common.builder;

import java.sql.Connection;
import java.sql.SQLException;

import com.ims.common.builder.asset.DriverManagerOpt;
import com.ims.common.builder.metainfo.DBMetaInfoUtils;
import com.ims.common.core.dao.plugin.DBType;
import com.ims.common.core.matatype.Dto;
import com.ims.common.core.matatype.Dtos;



/**
 * 数据访问层代码生成器
 * 
 * <p>
 * 提示：已经支持可视化生成。请访问：开发工具箱->WebIDE集成开发。
 * 
 * @author OSWorks-XC
 * @throws SQLException 
 */
public class DaoBuilderConsole {

	public static void main(String[] args) throws SQLException {
		//===================
		DriverManagerOpt driverOpt = new DriverManagerOpt();
		//当前版本支持mysql、oracle、sqlserver2005+、H2
		driverOpt.setDataBaseType(DBType.MYSQL); 
		driverOpt.setIp("127.0.0.1");
		driverOpt.setPort("3306");
		//数据库名或数据库实例名
		//driverOpt.setCatalog("./webapp/WEB-INF/db/aos");
		driverOpt.setCatalog("msrootx");
		driverOpt.setUserName("root");
		driverOpt.setPassword("root");
		//===================
		Dto dto = Dtos.newDto();
		//改为自己存放Dao相关文件的磁盘文件路径
		
		//dto.put("outPath", "D:\\Tunanworkspace\\IMS\\src\\com\\ims\\common\\system\\modules");
		dto.put("outPath", "E:\\dao");
		//改为自己Dao相关文件的包路径
		dto.put("package", "com.beauty.common");
		dto.put("author", "陈骑元");
		//指定多张表请用逗号分隔；
		//!!表名区分大小写的喔
		dto.put("tables", "bis_beauty_config");
		dto.put("className", "BeautyConfig");
		//===================
		Connection connection = DBMetaInfoUtils.newConnection(driverOpt);
		DaoBuilder.build(connection, dto);
		connection.close();
	}

}
