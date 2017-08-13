package com.ims.common.builder.asset;
/**
 * 
 * 类描述：<b>JDBC驱动配置对象</b> 
 * 创建人：陈骑元
 * 邮箱：240823329@qq.com
 * 创建时间：2016-6-10 上午10:58:48
 * 修改人：
 * 修改时间：
 * 修改备注： 
 * @version 1.0
 */
public class DriverManagerOpt {

	//数据库类型
	private String dataBaseType;
	// 数据库主机IP
	private String ip;
	// 数据库主机端口
	private String port;
	// 数据库名/实例名
	private String catalog;
	// 数据库用户名
	private String userName;
	// 数据库密码
	private String password;

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDataBaseType() {
		return dataBaseType;
	}

	public void setDataBaseType(String dataBaseType) {
		this.dataBaseType = dataBaseType;
	}

	public String getCatalog() {
		return catalog;
	}

	public void setCatalog(String catalog) {
		this.catalog = catalog;
	}

}
