package com.beauty.wechat.model;
/**
 * 
 * 类名:com.beauty.wechat.model.WechatUser
 * 描述:用户对象实体类
 * 编写者:陈骑元
 * 创建时间:2017年4月30日 上午1:18:54
 * 修改说明:
 */
public class WechatUser {
	/**用户是否订阅*/
	private  String subscribe;
	/**用户的标识*/
	private String openid;
	/**用户的昵称*/
	private String nickname;
	/**性别*/
	private int sex;
	/**用户所在城市*/
	private String city;
	/**用户所在国家*/
	private String country;
	/**用户所在省份*/
	private String province;
	/**用户的语言zh_CN*/
	private String language;
	/**用户头像*/
	private String headimgurl;
	/**用户关注时间*/
	private String subscribe_time;
	public String getSubscribe() {
		return subscribe;
	}
	public void setSubscribe(String subscribe) {
		this.subscribe = subscribe;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getHeadimgurl() {
		return headimgurl;
	}
	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}
	public String getSubscribe_time() {
		return subscribe_time;
	}
	public void setSubscribe_time(String subscribe_time) {
		this.subscribe_time = subscribe_time;
	}
	public String getUnionid() {
		return unionid;
	}
	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**公众号*/
	private String unionid;
	/**备注*/
	private String remark;

}
