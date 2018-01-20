package com.control.situation.utils;

import java.io.Serializable;

public class ClientResult implements Serializable {

	private String code;
	
	private String message;
	
	private Object result;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}
}
