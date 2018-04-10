package com.control.situation.config;

import com.control.situation.utils.returns.ClientResult;

import java.io.Serializable;

/**
 * 上下文环境变量,将一些请求的常用参数封装进去
 *
 * Created by heyan on 2017/10/31 0031.
 */
public class Env implements Serializable {

	/**
	 * 用户ID
	 */
	public Long userId; // 用户唯一标识
	/**
	 * 用户登录凭证
	 */
	public String token;
	/**
	 * 客户端IP
	 */
	public String clientIP;


	public ClientResult cr; // 客户端返回数据

	/**
	 * 获取用户唯一标识
	 */
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * 获取用户 ID
	 */
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public ClientResult getCr() {
		return cr;
	}

	public void setCr(ClientResult cr) {
		this.cr = cr;
	}

	public String getClientIP() {
		return clientIP;
	}

	public void setClientIP(String clientIP) {
		this.clientIP = clientIP;
	}
}