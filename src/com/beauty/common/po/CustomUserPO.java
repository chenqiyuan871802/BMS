package com.beauty.common.po;


import com.ims.common.core.matatype.BasePO;
import java.util.Date;
/**
 * 
 * 类描述： <b>顾客用户信息表[bis_custom_user]数据持久化对象</b>
 * 创建人：陈骑元
 * 邮箱：240823329@qq.com
 * 创建时间：2017-05-28 21:28:01
 * 修改人：
 * 修改时间：
 * 修改备注： 
 * @version 1.0
 */

public class CustomUserPO extends BasePO {

	private static final long serialVersionUID = 1L;

	/**
	 * 顾客编号
	 */
	private String custom_user_id;
	
	/**
	 * 手机
	 */
	private String mobile;
	
	/**
	 * 姓名
	 */
	private String username;
	
	/**
	 * 密码
	 */
	private String password;
	
	/**
	 * 昵称
	 */
	private String nikename;
	
	/**
	 * 微信openid
	 */
	private String openid;
	
	/**
	 * 微信唯一编号
	 */
	private String unionid;
	
	/**
	 * 微信号
	 */
	private String wechat;
	
	/**
	 * 头像
	 */
	private String photo;
	
	/**
	 * 性别1:男2:女3:保密
	 */
	private String sex;
	
	/**
	 * 电话
	 */
	private String phone;
	
	/**
	 * qq
	 */
	private String qq;
	
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
	 * 备注
	 */
	private String remark;
	
	/**
	 * 注册方式1微信2商家3后台
	 */
	private String enroll_mode;
	
	/**
	 * 注册时间
	 */
	private Date enroll_time;
	
	/**
	 * 是否删除 0 有效 1删除
	 */
	private String is_del;
	
	/**
	 * 微信状态 1 未订阅 2已订阅 3已退订
	 */
	private String wechat_status;
	
	/**
	 * 颜值数量
	 */
	private Integer beauty_num;
	
	/**
	 *  礼包数量
	 */
	private Integer bag_num;
	

	/**
	 * 顾客编号
	 * 
	 * @return custom_user_id
	 */
	public String getCustom_user_id() {
		return custom_user_id;
	}
	
	/**
	 * 手机
	 * 
	 * @return mobile
	 */
	public String getMobile() {
		return mobile;
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
	 * 昵称
	 * 
	 * @return nikename
	 */
	public String getNikename() {
		return nikename;
	}
	
	/**
	 * 微信openid
	 * 
	 * @return openid
	 */
	public String getOpenid() {
		return openid;
	}
	
	/**
	 * 微信唯一编号
	 * 
	 * @return unionid
	 */
	public String getUnionid() {
		return unionid;
	}
	
	/**
	 * 微信号
	 * 
	 * @return wechat
	 */
	public String getWechat() {
		return wechat;
	}
	
	/**
	 * 头像
	 * 
	 * @return photo
	 */
	public String getPhoto() {
		return photo;
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
	 * 电话
	 * 
	 * @return phone
	 */
	public String getPhone() {
		return phone;
	}
	
	/**
	 * qq
	 * 
	 * @return qq
	 */
	public String getQq() {
		return qq;
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
	 * 备注
	 * 
	 * @return remark
	 */
	public String getRemark() {
		return remark;
	}
	
	/**
	 * 注册方式1微信2商家3后台
	 * 
	 * @return enroll_mode
	 */
	public String getEnroll_mode() {
		return enroll_mode;
	}
	
	/**
	 * 注册时间
	 * 
	 * @return enroll_time
	 */
	public Date getEnroll_time() {
		return enroll_time;
	}
	
	/**
	 * 是否删除 0 有效 1删除
	 * 
	 * @return is_del
	 */
	public String getIs_del() {
		return is_del;
	}
	
	/**
	 * 微信状态 1 未订阅 2已订阅 3已退订
	 * 
	 * @return wechat_status
	 */
	public String getWechat_status() {
		return wechat_status;
	}
	
	/**
	 * 颜值数量
	 * 
	 * @return beauty_num
	 */
	public Integer getBeauty_num() {
		return beauty_num;
	}
	
	/**
	 *  礼包数量
	 * 
	 * @return bag_num
	 */
	public Integer getBag_num() {
		return bag_num;
	}
	

	/**
	 * 顾客编号
	 * 
	 * @param custom_user_id
	 */
	public void setCustom_user_id(String custom_user_id) {
		this.custom_user_id = custom_user_id;
	}
	
	/**
	 * 手机
	 * 
	 * @param mobile
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
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
	 * 昵称
	 * 
	 * @param nikename
	 */
	public void setNikename(String nikename) {
		this.nikename = nikename;
	}
	
	/**
	 * 微信openid
	 * 
	 * @param openid
	 */
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	
	/**
	 * 微信唯一编号
	 * 
	 * @param unionid
	 */
	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}
	
	/**
	 * 微信号
	 * 
	 * @param wechat
	 */
	public void setWechat(String wechat) {
		this.wechat = wechat;
	}
	
	/**
	 * 头像
	 * 
	 * @param photo
	 */
	public void setPhoto(String photo) {
		this.photo = photo;
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
	 * 电话
	 * 
	 * @param phone
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	/**
	 * qq
	 * 
	 * @param qq
	 */
	public void setQq(String qq) {
		this.qq = qq;
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
	 * 备注
	 * 
	 * @param remark
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	/**
	 * 注册方式1微信2商家3后台
	 * 
	 * @param enroll_mode
	 */
	public void setEnroll_mode(String enroll_mode) {
		this.enroll_mode = enroll_mode;
	}
	
	/**
	 * 注册时间
	 * 
	 * @param enroll_time
	 */
	public void setEnroll_time(Date enroll_time) {
		this.enroll_time = enroll_time;
	}
	
	/**
	 * 是否删除 0 有效 1删除
	 * 
	 * @param is_del
	 */
	public void setIs_del(String is_del) {
		this.is_del = is_del;
	}
	
	/**
	 * 微信状态 1 未订阅 2已订阅 3已退订
	 * 
	 * @param wechat_status
	 */
	public void setWechat_status(String wechat_status) {
		this.wechat_status = wechat_status;
	}
	
	/**
	 * 颜值数量
	 * 
	 * @param beauty_num
	 */
	public void setBeauty_num(Integer beauty_num) {
		this.beauty_num = beauty_num;
	}
	
	/**
	 *  礼包数量
	 * 
	 * @param bag_num
	 */
	public void setBag_num(Integer bag_num) {
		this.bag_num = bag_num;
	}
	

}