package com.control.situation.utils;

import java.io.Serializable;

public class JsonResult implements Serializable {

	private String code="0";
	
	private String message;
	
	private Object result;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
		this.message = "操作成功";
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
		this.code = "1";
		this.message = "操作成功";
		this.result = result;
	}
}
