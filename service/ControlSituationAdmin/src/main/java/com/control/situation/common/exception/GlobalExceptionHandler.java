package com.control.situation.common.exception;

import com.control.situation.utils.returns.ClientResult;
import com.control.situation.utils.returns.RetCode;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常处理,在这里捕获的是将要抛给 JVM 的异常，这些异常会返回给调用者，而我们不希望调用者看到这些数据，在这里屏蔽
 * 这里没有对异常做打印记录，异常的打印交给拦截器 WebLoggerAspect 处理，这样方便日志的查看
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ClientResult handleException(Exception e) {
		ClientResult c = new ClientResult();
		c.setCode(RetCode.ERR_SERVER_EXCEPTION);
		return c;
	}
}
