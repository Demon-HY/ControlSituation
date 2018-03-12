package com.control.situation.utils;

import java.io.Serializable;

public class ClientResult implements Serializable {

	private String code;
	
	private String message;
	
	private Object result;

	public String getCode() {
		return code;
	}

	/**
	 * 设置 code 时，会同时设置默认的 message
	 * message = RetCode.getMsgByStat(stat)
	 */
	public ClientResult setCode(String code) {
		this.code = code;
		this.message = RetCode.getMsgByStat(code);
		return this;
	}

	public String getMessage() {
		return message;
	}

	public ClientResult setMessage(String message) {
		this.message = message;
		return this;
	}

	public Object getResult() {
		return result;
	}

	public ClientResult setResult(Object result) {
		this.result = result;
		return this;
	}


}
