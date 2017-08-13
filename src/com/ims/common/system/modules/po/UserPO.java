package com.ims.common.system.modules.po;


import com.ims.common.core.matatype.BasePO;
import java.util.Date;
/**
 * 
 * 类描述： <b>用户基本信息表[sys_user]数据持久化对象</b>
 * 创建人：陈骑元
 * 邮箱：240823329@qq.com
 * 创建时间：2016-12-18 08:25:38
 * 修改人：
 * 修改时间：
 * 修改备注： 
 * @version 1.0
 */

public class UserPO extends BasePO {

	private static final long serialVersionUID = 1L;

	/**
	 * 用户编号
	 */
	private String user_id;
	
	/**
	 * 用户登录帐号
	 */
	private String account;
	
	/**
	 * 密码
	 */
	private String password;
	
	/**
	 * 用户姓名
	 */
	private String username;
	
	/**
	 * 锁定次数 默认5次
	 */
	private Integer lock_num;
	
	/**
	 * 密码错误次数  如果等于锁定次数就自动锁定用户
	 */
	private Integer error_num;
	
	/**
	 * 性别  1:男2:女3:未知
	 */
	private String sex;
	
	/**
	 * 用户状态 1:有效2:停用 3:锁定
	 */
	private String status;
	
	/**
	 * 用户类型
	 */
	private String user_type;
	
	/**
	 * 所属部门流水号
	 */
	private String dept_id;
	/**
	 * 所属组织
	 */
	private String dept_name;
	/**
	 * 所属角色
	 */
	private String role_name;

	
	/**
	 * 联系电话
	 */
	private String mobile;
	
	/**
	 * QQ号码
	 */
	private String qq;
	
	/**
	 * 微信
	 */
	private String wechat;
	
	/**
	 * 电子邮件
	 */
	private String email;
	
	/**
	 * 身份证号
	 */
	private String idno;
	
	/**
	 * 界面风格
	 */
	private String style;
	
	/**
	 * 联系地址
	 */
	private String address;
	
	/**
	 * 备注
	 */
	private String remark;
	
	/**
	 * 是否已删除 0有效 1删除
	 */
	private String is_del;
	
	/**
	 * 创建时间
	 */
	private Date create_time;
	
	/**
	 * 创建人ID
	 */
	private String create_user_id;
	
	/**
	 * modify_time
	 */
	private Date modify_time;
	
	/**
	 * 修改用户编号
	 */
	private String modify_user_id;
	
	public String getDept_name() {
		return dept_name;
	}

	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
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
	 * 用户登录帐号
	 * 
	 * @return account
	 */
	public String getAccount() {
		return account;
	}
	
	/**
	 * 密码
	 * 
	 * @return password
	 */
	public String getPassword() {
		return password;
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
	 * 锁定次数 默认5次
	 * 
	 * @return lock_nun
	 */
	public Integer getLock_num() {
		return lock_num;
	}
	
	/**
	 * 密码错误次数  如果等于锁定次数就自动锁定用户
	 * 
	 * @return error_num
	 */
	public Integer getError_num() {
		return error_num;
	}
	
	/**
	 * 性别  1:男2:女3:未知
	 * 
	 * @return sex
	 */
	public String getSex() {
		return sex;
	}
	
	/**
	 * 用户状态 1:有效2:停用 3:锁定
	 * 
	 * @return status
	 */
	public String getStatus() {
		return status;
	}
	
	/**
	 * 用户类型
	 * 
	 * @return user_type
	 */
	public String getUser_type() {
		return user_type;
	}
	
	/**
	 * 所属部门流水号
	 * 
	 * @return dept_id
	 */
	public String getDept_id() {
		return dept_id;
	}
	
	/**
	 * 联系电话
	 * 
	 * @return mobile
	 */
	public String getMobile() {
		return mobile;
	}
	
	/**
	 * QQ号码
	 * 
	 * @return qq
	 */
	public String getQq() {
		return qq;
	}
	
	/**
	 * 微信
	 * 
	 * @return wechat
	 */
	public String getWechat() {
		return wechat;
	}
	
	/**
	 * 电子邮件
	 * 
	 * @return email
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * 身份证号
	 * 
	 * @return idno
	 */
	public String getIdno() {
		return idno;
	}
	
	/**
	 * 界面风格
	 * 
	 * @return style
	 */
	public String getStyle() {
		return style;
	}
	
	/**
	 * 联系地址
	 * 
	 * @return address
	 */
	public String getAddress() {
		return address;
	}
	
	/**
	 * 备注
	 * 
	 * @return remark
	 */
	public String getRemark() {
		return remark;
	}
	
	/**
	 * 是否已删除 0有效 1删除
	 * 
	 * @return is_del
	 */
	public String getIs_del() {
		return is_del;
	}
	
	/**
	 * 创建时间
	 * 
	 * @return create_time
	 */
	public Date getCreate_time() {
		return create_time;
	}
	
	/**
	 * 创建人ID
	 * 
	 * @return create_user_id
	 */
	public String getCreate_user_id() {
		return create_user_id;
	}
	
	/**
	 * modify_time
	 * 
	 * @return modify_time
	 */
	public Date getModify_time() {
		return modify_time;
	}
	
	/**
	 * 修改用户编号
	 * 
	 * @return modify_user_id
	 */
	public String getModify_user_id() {
		return modify_user_id;
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
	 * 用户登录帐号
	 * 
	 * @param account
	 */
	public void setAccount(String account) {
		this.account = account;
	}
	
	/**
	 * 密码
	 * 
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
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
	 * 锁定次数 默认5次
	 * 
	 * @param lock_nun
	 */
	public void setLock_num(Integer lock_num) {
		this.lock_num = lock_num;
	}
	
	/**
	 * 密码错误次数  如果等于锁定次数就自动锁定用户
	 * 
	 * @param error_num
	 */
	public void setError_num(Integer error_num) {
		this.error_num = error_num;
	}
	
	/**
	 * 性别  1:男2:女3:未知
	 * 
	 * @param sex
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	/**
	 * 用户状态 1:有效2:停用 3:锁定
	 * 
	 * @param status
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	
	/**
	 * 用户类型
	 * 
	 * @param user_type
	 */
	public void setUser_type(String user_type) {
		this.user_type = user_type;
	}
	
	/**
	 * 所属部门流水号
	 * 
	 * @param dept_id
	 */
	public void setDept_id(String dept_id) {
		this.dept_id = dept_id;
	}
	
	/**
	 * 联系电话
	 * 
	 * @param mobile
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	/**
	 * QQ号码
	 * 
	 * @param qq
	 */
	public void setQq(String qq) {
		this.qq = qq;
	}
	
	/**
	 * 微信
	 * 
	 * @param wechat
	 */
	public void setWechat(String wechat) {
		this.wechat = wechat;
	}
	
	/**
	 * 电子邮件
	 * 
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * 身份证号
	 * 
	 * @param idno
	 */
	public void setIdno(String idno) {
		this.idno = idno;
	}
	
	/**
	 * 界面风格
	 * 
	 * @param style
	 */
	public void setStyle(String style) {
		this.style = style;
	}
	
	/**
	 * 联系地址
	 * 
	 * @param address
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	
	/**
	 * 备注
	 * 
	 * @param remark
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	/**
	 * 是否已删除 0有效 1删除
	 * 
	 * @param is_del
	 */
	public void setIs_del(String is_del) {
		this.is_del = is_del;
	}
	
	/**
	 * 创建时间
	 * 
	 * @param create_time
	 */
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	
	/**
	 * 创建人ID
	 * 
	 * @param create_user_id
	 */
	public void setCreate_user_id(String create_user_id) {
		this.create_user_id = create_user_id;
	}
	
	/**
	 * modify_time
	 * 
	 * @param modify_time
	 */
	public void setModify_time(Date modify_time) {
		this.modify_time = modify_time;
	}
	
	/**
	 * 修改用户编号
	 * 
	 * @param modify_user_id
	 */
	public void setModify_user_id(String modify_user_id) {
		this.modify_user_id = modify_user_id;
	}
	
	public String getRole_name() {
		return role_name;
	}

	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}


}