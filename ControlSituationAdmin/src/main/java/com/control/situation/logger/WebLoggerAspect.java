package com.control.situation.logger;

import com.alibaba.fastjson.JSONObject;
import com.control.situation.utils.ClientResult;
import com.control.situation.utils.JsonUtil;
import com.demon.utils.RandomUtil;
import com.demon.utils.ValidateUtils;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

/**
 * Web 请求信息记录
 * <p>
 * 日志格式：
 * TIME:%s    REQUEST_CODE=%s    URL:%s    HTTP_METHOD:%s    PARAMS=%s
 * TIME 时间
 * REQUEST_CODE 请求标识，可以通过请求CODE找到返回结果，返回结果截取了63位数据
 * URL 请求URL
 * HTTP_METHOD 请求类型：GET/POST
 * PARAMS 请求参数
 */
@Aspect
@Component
public class WebLoggerAspect {

	private Logger logger = Logger.getLogger(WebLoggerAspect.class);

	// 记录一个全局的唯一标识，可以通过它找到请求和响应,该值会被写入 Response 的头部，RequestId
	private ThreadLocal<String> requestCode = new ThreadLocal<>();

	/**
	 * 定义一个切入点
	 */
	@Pointcut("execution(* com.control.situation.httpapi..*.*(..))")
	public void webLog() {
	}

	@Before("webLog()")
	public void doBefore(JoinPoint joinPoint) {
		requestCode.set(RandomUtil.getRequestId());

		// 接收到请求，记录请求内容
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		// 记录下请求内容
		String url = request.getRequestURL().toString();
		String httpMethod = request.getMethod();
		JSONObject obj = new JSONObject(); // 请求参数
		//获取所有参数
		Enumeration<String> enu = request.getParameterNames();
		while (enu.hasMoreElements()) {
			String paraName = enu.nextElement();
			obj.put(paraName, request.getParameter(paraName));
		}

		logger.info(String.format("%s  OK  REQ   %s  %s  %s", requestCode.get(), url, httpMethod, obj.toString()));
	}

	@AfterReturning(value = "webLog()", returning = "result")
	public void doAfterReturning(JoinPoint joinPoint, Object result) {
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		HttpServletResponse response = attributes.getResponse();
		response.setHeader("RequestId", requestCode.get());

		String url = request.getRequestURL().toString();
		String httpMethod = request.getMethod();

		String resultObj = "";
		try {
			if (ValidateUtils.notEmpty(url) && url.contains("hello")) { // 屏蔽阿里的健康检查
				return;
			}

			resultObj = JsonUtil.convertToJSON(result);
			resultObj = resultObj.length() > 64 ? resultObj.substring(0, 63) : resultObj;
		} catch (Exception e) {
			e.printStackTrace();
		}


		// 处理完请求，返回内容
		logger.info(String.format("%s  OK  RESP  %s  %s  %s", requestCode.get(), url, httpMethod, resultObj));
	}

	@AfterThrowing(value = "webLog()", throwing = "e")
	public void doAfterThrowing(Throwable e) throws Exception {
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		HttpServletResponse response = attributes.getResponse();
		response.setHeader("RequestId", requestCode.get());

		// 这里可以捕获异常，但无法处理异常，异常还是会抛给 JVM
//        ClientResult c = new ClientResult();
//        c.setMessage("系统繁忙，请稍后重试");
//        JsonUtil.sendJsonResponse(response, c);

		String url = request.getRequestURL().toString();
		String httpMethod = request.getMethod();

		// 处理完请求，返回内容
		logger.error(String.format("%s  ERROR  RESP  %s  %s  %s", requestCode.get(), url, httpMethod, e.getMessage()), e);
	}

}
