package com.beauty.wechat.model;

/**
 * 
 * 类名:com.beauty.wechat.model.Token
 * 描述:Token实体类
 * 编写者:陈骑元
 * 创建时间:2017年4月29日 下午3:33:44
 * 修改说明:
 */
public class Token {
	// 接口访问凭证
	private String accessToken;
	// 凭证有效期，单位：秒
	private int expiresIn;
	// 用户刷新凭证
	private String refreshToken;
	//用户唯一标识
	private String openid;
	//用户授权的作用域
	private String scope;
	//获取用户个人信息（UnionID机制）
	private String unionid;

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public int getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(int expiresIn) {
		this.expiresIn = expiresIn;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getUnionid() {
		return unionid;
	}

	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}
}