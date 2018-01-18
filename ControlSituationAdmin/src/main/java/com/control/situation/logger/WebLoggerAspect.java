package com.control.situation.logger;

import com.alibaba.fastjson.JSONObject;
import com.xqiapp.utils.CodeGeneratorUtil;
import com.xqiapp.utils.JsonUtil;
import com.xqiapp.utils.StringUtils;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * Web 请求信息记录
 *
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

	// 记录一个全局的唯一标识，可以通过它找到请求和响应
	private ThreadLocal<String> requestCode = new ThreadLocal<>();

	/**
	 * 定义一个切入点
	 */
	@Pointcut("execution(* com.xqiapp.http..*.*(..))")
	public void webLog(){}

	@Before("webLog()")
	public void doBefore(JoinPoint joinPoint){
		requestCode.set(CodeGeneratorUtil.createOrderNo(""));

		// 接收到请求，记录请求内容
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		// 记录下请求内容
		String url = request.getRequestURL().toString();
		if (StringUtils.notEmpty(url) && url.contains("hello")) { // 屏蔽阿里的健康检查
			return;
		}
		String httpMethod = request.getMethod();
//		String args = Arrays.toString(joinPoint.getArgs());
		JSONObject obj = new JSONObject(); // 请求参数
		//获取所有参数
		Enumeration<String> enu=request.getParameterNames();
		while(enu.hasMoreElements()){
			String paraName= enu.nextElement();
			obj.put(paraName, request.getParameter(paraName));
		}
		logger.info(String.format("%s    REQ    %s    %s    %s", requestCode.get(), url, httpMethod, obj.toString()));

//		logger.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
	}

	@AfterReturning(value = "webLog()", returning = "result")
	public void  doAfterReturning(JoinPoint joinPoint, Object result) {
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		String url = request.getRequestURL().toString();
		String httpMethod = request.getMethod();

		String resultObj = "";
		try {
			if (StringUtils.notEmpty(url) && url.contains("hello")) { // 屏蔽阿里的健康检查
				return;
			}

			resultObj = JsonUtil.convertToJSON(result);
			resultObj = resultObj.length() > 64 ? resultObj.substring(0, 63) : resultObj;
		} catch (Exception e) {
			e.printStackTrace();
		}


		// 处理完请求，返回内容
		logger.info(String.format("%s    RESP    %s    %s    %s", requestCode.get(), url, httpMethod, resultObj));
	}

}
