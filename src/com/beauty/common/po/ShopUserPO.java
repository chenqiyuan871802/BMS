package com.beauty.common.po;


import com.ims.common.core.matatype.BasePO;
import java.util.Date;
/**
 * 
 * 类描述： <b>店铺员工信息表[bis_shop_user]数据持久化对象</b>
 * 创建人：陈骑元
 * 邮箱：240823329@qq.com
 * 创建时间：2017-04-17 12:09:21
 * 修改人：
 * 修改时间：
 * 修改备注： 
 * @version 1.0
 */

public class ShopUserPO extends BasePO {

	private static final long serialVersionUID = 1L;

	/**
	 * 用户编号
	 */
	private String shop_user_id;
	
	/**
	 * 用户类型 1 店主 2员工
	 */
	private String user_type;
	
	/**
	 * 店铺编号
	 */
	private String shop_id;
	
	/**
	 * 账号
	 */
	private String account;
	
	/**
	 * 姓名
	 */
	private String username;
	
	/**
	 * 密码
	 */
	private String password;
	
	/**
	 * 头像照片
	 */
	private String photo;
	
	/**
	 * 工号
	 */
	private String work_number;
	
	/**
	 * 性别1:男2:女3:保密
	 */
	private String sex;
	
	/**
	 * 职位编码
	 */
	private String post_code;
	
	/**
	 * 入职日期
	 */
	private Date entry_date;
	
	/**
	 * 手机号码
	 */
	private String mobile;
	
	/**
	 * 电话
	 */
	private String phone;
	
	/**
	 * 邮箱
	 */
	private String email;
	
	/**
	 * 出生日期
	 */
	private Date born_date;
	
	/**
	 * 身份证
	 */
	private String idno;
	
	/**
	 * 居住地址
	 */
	private String address;
	
	/**
	 * 紧急联系人
	 */
	private String linkman;
	
	/**
	 * 紧急联系电话
	 */
	private String linkphone;
	
	/**
	 * 状态0:禁用1:启用 0:离职 1:在职
	 */
	private String status;
	
	/**
	 * 是否删除0有效1删除
	 */
	private String is_del;
	
	/**
	 * 备注
	 */
	private String remark;
	
	/**
	 * 创建时间
	 */
	private Date create_time;
	
	/**
	 * 创建人ID
	 */
	private String create_user_id;
	
	/**
	 * 修改时间
	 */
	private Date modify_time;
	
	/**
	 * 修改用户ID
	 */
	private String modify_user_id;
	/**
	 * 岗位名称
	 */
	private String post_name;
	/**
	 * 上一次登陆时间
	 */
	private String  last_login_time;
	/**
	 * 岗位编码
	 */
	private String post_id;
	

	public String getPost_id() {
		return post_id;
	}

	public void setPost_id(String post_id) {
		this.post_id = post_id;
	}

	

	public String getLast_login_time() {
		return last_login_time;
	}

	public void setLast_login_time(String last_login_time) {
		this.last_login_time = last_login_time;
	}

	public String getPost_name() {
		return post_name;
	}

	public void setPost_name(String post_name) {
		this.post_name = post_name;
	}

	/**
	 * 用户编号
	 * 
	 * @return shop_user_id
	 */
	public String getShop_user_id() {
		return shop_user_id;
	}
	
	/**
	 * 用户类型 1 店主 2员工
	 * 
	 * @return user_type
	 */
	public String getUser_type() {
		return user_type;
	}
	
	/**
	 * 店铺编号
	 * 
	 * @return shop_id
	 */
	public String getShop_id() {
		return shop_id;
	}
	
	/**
	 * 账号
	 * 
	 * @return account
	 */
	public String getAccount() {
		return account;
	}
	
	/**
	 * 姓名
	 * 
	 * @return username
	 */
	public String getUsername() {
		return username;
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
	 * 头像照片
	 * 
	 * @return photo
	 */
	public String getPhoto() {
		return photo;
	}
	
	/**
	 * 工号
	 * 
	 * @return work_number
	 */
	public String getWork_number() {
		return work_number;
	}
	
	/**
	 * 性别1:男2:女3:保密
	 * 
	 * @return sex
	 */
	public String getSex() {
		return sex;
	}
	
	/**
	 * 职位编码
	 * 
	 * @return post_code
	 */
	public String getPost_code() {
		return post_code;
	}
	
	/**
	 * 入职日期
	 * 
	 * @return entry_date
	 */
	public Date getEntry_date() {
		return entry_date;
	}
	
	/**
	 * 手机号码
	 * 
	 * @return mobile
	 */
	public String getMobile() {
		return mobile;
	}
	
	/**
	 * 电话
	 * 
	 * @return phone
	 */
	public String getPhone() {
		return phone;
	}
	
	/**
	 * 邮箱
	 * 
	 * @return email
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * 出生日期
	 * 
	 * @return born_date
	 */
	public Date getBorn_date() {
		return born_date;
	}
	
	/**
	 * 身份证
	 * 
	 * @return idno
	 */
	public String getIdno() {
		return idno;
	}
	
	/**
	 * 居住地址
	 * 
	 * @return address
	 */
	public String getAddress() {
		return address;
	}
	
	/**
	 * 紧急联系人
	 * 
	 * @return linkman
	 */
	public String getLinkman() {
		return linkman;
	}
	
	/**
	 * 紧急联系电话
	 * 
	 * @return linkphone
	 */
	public String getLinkphone() {
		return linkphone;
	}
	
	/**
	 * 状态0:禁用1:启用 0:离职 1:在职
	 * 
	 * @return status
	 */
	public String getStatus() {
		return status;
	}
	
	/**
	 * 是否删除0有效1删除
	 * 
	 * @return is_del
	 */
	public String getIs_del() {
		return is_del;
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
	 * 修改时间
	 * 
	 * @return modify_time
	 */
	public Date getModify_time() {
		return modify_time;
	}
	
	/**
	 * 修改用户ID
	 * 
	 * @return modify_user_id
	 */
	public String getModify_user_id() {
		return modify_user_id;
	}
	

	/**
	 * 用户编号
	 * 
	 * @param shop_user_id
	 */
	public void setShop_user_id(String shop_user_id) {
		this.shop_user_id = shop_user_id;
	}
	
	/**
	 * 用户类型 1 店主 2员工
	 * 
	 * @param user_type
	 */
	public void setUser_type(String user_type) {
		this.user_type = user_type;
	}
	
	/**
	 * 店铺编号
	 * 
	 * @param shop_id
	 */
	public void setShop_id(String shop_id) {
		this.shop_id = shop_id;
	}
	
	/**
	 * 账号
	 * 
	 * @param account
	 */
	public void setAccount(String account) {
		this.account = account;
	}
	
	/**
	 * 姓名
	 * 
	 * @param username
	 */
	public void setUsername(String username) {
		this.username = username;
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
	 * 头像照片
	 * 
	 * @param photo
	 */
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
	/**
	 * 工号
	 * 
	 * @param work_number
	 */
	public void setWork_number(String work_number) {
		this.work_number = work_number;
	}
	
	/**
	 * 性别1:男2:女3:保密
	 * 
	 * @param sex
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	/**
	 * 职位编码
	 * 
	 * @param post_code
	 */
	public void setPost_code(String post_code) {
		this.post_code = post_code;
	}
	
	/**
	 * 入职日期
	 * 
	 * @param entry_date
	 */
	public void setEntry_date(Date entry_date) {
		this.entry_date = entry_date;
	}
	
	/**
	 * 手机号码
	 * 
	 * @param mobile
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	/**
	 * 电话
	 * 
	 * @param phone
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	/**
	 * 邮箱
	 * 
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * 出生日期
	 * 
	 * @param born_date
	 */
	public void setBorn_date(Date born_date) {
		this.born_date = born_date;
	}
	
	/**
	 * 身份证
	 * 
	 * @param idno
	 */
	public void setIdno(String idno) {
		this.idno = idno;
	}
	
	/**
	 * 居住地址
	 * 
	 * @param address
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	
	/**
	 * 紧急联系人
	 * 
	 * @param linkman
	 */
	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}
	
	/**
	 * 紧急联系电话
	 * 
	 * @param linkphone
	 */
	public void setLinkphone(String linkphone) {
		this.linkphone = linkphone;
	}
	
	/**
	 * 状态0:禁用1:启用 0:离职 1:在职
	 * 
	 * @param status
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	
	/**
	 * 是否删除0有效1删除
	 * 
	 * @param is_del
	 */
	public void setIs_del(String is_del) {
		this.is_del = is_del;
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
	 * 修改时间
	 * 
	 * @param modify_time
	 */
	public void setModify_time(Date modify_time) {
		this.modify_time = modify_time;
	}
	
	/**
	 * 修改用户ID
	 * 
	 * @param modify_user_id
	 */
	public void setModify_user_id(String modify_user_id) {
		this.modify_user_id = modify_user_id;
	}
	

}