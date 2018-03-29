package com.control.situation.entity.vo;

import com.control.situation.entity.UserInfo;

import java.io.Serializable;

/**
 * 登录信息
 *
 * Created by Administrator on 2018/3/29 0029.
 */
public class LoginInfo implements Serializable {

	private UserInfo userInfo;

	private TokenInfo tokenInfo;

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public TokenInfo getTokenInfo() {
		return tokenInfo;
	}

	public void setTokenInfo(TokenInfo tokenInfo) {
		this.tokenInfo = tokenInfo;
	}
}
