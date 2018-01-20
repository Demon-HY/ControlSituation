package com.control.situation.exception;

import com.control.situation.utils.ClientResult;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常处理
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

	private Logger logger = Logger.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(Exception.class)
	public ClientResult handleException(Exception e) {
		logger.error("Global Exception", e);
		e.printStackTrace();
		ClientResult c = new ClientResult();
		c.setMessage("系统繁忙，请稍后重试");
		return c;
	}
}
