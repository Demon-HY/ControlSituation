package com.control.situation.config;

import com.control.situation.utils.ClientResult;

import java.io.Serializable;

/**
 * 上下文环境变量,将一些请求的常用参数封装进去
 *
 * Created by heyan on 2017/10/31 0031.
 */
public class Env implements Serializable {

	private String token; // 用户唯一标识

	private ClientResult clientResult; // 客户端返回数据

	/**
	 * 获取用户唯一标识
	 */
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public ClientResult getClientResult() {
		return clientResult;
	}

	public void setClientResult(ClientResult clientResult) {
		this.clientResult = clientResult;
	}
}