package com.beauty.common.po;


import com.ims.common.core.matatype.BasePO;
import java.util.Date;
/**
 * 
 * 类描述： <b>商家登陆日志[bis_shop_loginlog]数据持久化对象</b>
 * 创建人：陈骑元
 * 邮箱：240823329@qq.com
 * 创建时间：2017-05-07 01:03:05
 * 修改人：
 * 修改时间：
 * 修改备注： 
 * @version 1.0
 */

public class ShopLoginLogPO extends BasePO {

	private static final long serialVersionUID = 1L;

	/**
	 * 日志编号
	 */
	private String log_id;
	
	/**
	 * 会话ID
	 */
	private String session_id;
	
	/**
	 * 用户姓名
	 */
	private String username;
	
	/**
	 * 用户账号
	 */
	private String account;
	
	/**
	 * 登陆用户时间
	 */
	private Date login_time;
	
	/**
	 * 用户编号
	 */
	private String user_id;
	
	/**
	 * 登陆IP
	 */
	private String login_ip;
	
	/**
	 * 登陆浏览器
	 */
	private String explorer;
	
	/**
	 * 退出时间
	 */
	private Date exit_time;
	
	/**
	 * 退出类型 1超时退出 2自动退出
	 */
	private String exit_type;
	

	/**
	 * 日志编号
	 * 
	 * @return log_id
	 */
	public String getLog_id() {
		return log_id;
	}
	
	/**
	 * 会话ID
	 * 
	 * @return session_id
	 */
	public String getSession_id() {
		return session_id;
	}
	
	/**
	 * 用户姓名
	 * 
	 * @return username
	 */
	public String getUsername() {
		return username;
	}
	
	/**
	 * 用户账号
	 * 
	 * @return account
	 */
	public String getAccount() {
		return account;
	}
	
	/**
	 * 登陆用户时间
	 * 
	 * @return login_time
	 */
	public Date getLogin_time() {
		return login_time;
	}
	
	/**
	 * 用户编号
	 * 
	 * @return user_id
	 */
	public String getUser_id() {
		return user_id;
	}
	
	/**
	 * 登陆IP
	 * 
	 * @return login_ip
	 */
	public String getLogin_ip() {
		return login_ip;
	}
	
	/**
	 * 登陆浏览器
	 * 
	 * @return explorer
	 */
	public String getExplorer() {
		return explorer;
	}
	
	/**
	 * 退出时间
	 * 
	 * @return exit_time
	 */
	public Date getExit_time() {
		return exit_time;
	}
	
	/**
	 * 退出类型 1超时退出 2自动退出
	 * 
	 * @return exit_type
	 */
	public String getExit_type() {
		return exit_type;
	}
	

	/**
	 * 日志编号
	 * 
	 * @param log_id
	 */
	public void setLog_id(String log_id) {
		this.log_id = log_id;
	}
	
	/**
	 * 会话ID
	 * 
	 * @param session_id
	 */
	public void setSession_id(String session_id) {
		this.session_id = session_id;
	}
	
	/**
	 * 用户姓名
	 * 
	 * @param username
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	
	/**
	 * 用户账号
	 * 
	 * @param account
	 */
	public void setAccount(String account) {
		this.account = account;
	}
	
	/**
	 * 登陆用户时间
	 * 
	 * @param login_time
	 */
	public void setLogin_time(Date login_time) {
		this.login_time = login_time;
	}
	
	/**
	 * 用户编号
	 * 
	 * @param user_id
	 */
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
	/**
	 * 登陆IP
	 * 
	 * @param login_ip
	 */
	public void setLogin_ip(String login_ip) {
		this.login_ip = login_ip;
	}
	
	/**
	 * 登陆浏览器
	 * 
	 * @param explorer
	 */
	public void setExplorer(String explorer) {
		this.explorer = explorer;
	}
	
	/**
	 * 退出时间
	 * 
	 * @param exit_time
	 */
	public void setExit_time(Date exit_time) {
		this.exit_time = exit_time;
	}
	
	/**
	 * 退出类型 1超时退出 2自动退出
	 * 
	 * @param exit_type
	 */
	public void setExit_type(String exit_type) {
		this.exit_type = exit_type;
	}
	

}