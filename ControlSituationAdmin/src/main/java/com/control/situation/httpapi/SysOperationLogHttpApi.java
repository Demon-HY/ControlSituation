package com.control.situation.httpapi;

import org.apache.log4j.Logger;
import com.control.situation.api.SysOperationLogApi;
import com.control.situation.config.Env;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * SysOperationLog 访问层
 * @author Demon-Coffee
 * @since 1.0
 */
@RestController
@RequestMapping("/sys_operation_log/api")
public class SysOperationLogHttpApi {

	private Logger logger = Logger.getLogger(SysOperationLogHttpApi.class);

	@Autowired
	private SysOperationLogApi sysOperationLogApi;

	@RequestMapping("findList")
	public ClientResult findList(HttpServletRequest req) {
		Env env = (Env) req.getAttribute("env");
		ClientResult clientResult = sysOperationLogApi.findList(Env env);
		return env.getClientResult();
	}

	@RequestMapping("findDetail")
	public ClientResult findDetail(HttpServletRequest req){
		Env env = (Env) req.getAttribute("env");
		ClientResult clientResult = sysOperationLogApi.findDetail(Env env);
		return env.getClientResult();
	}

	@RequestMapping("save")
	public ClientResult save(HttpServletRequest req){
		Env env = (Env) req.getAttribute("env");
		ClientResult clientResult = sysOperationLogApi.save(Env env);
		return env.getClientResult();
	}

	@RequestMapping("delete")
	public ClientResult delete(HttpServletRequest req){
		Env env = (Env) req.getAttribute("env");
		ClientResult clientResult = sysOperationLogApi.delete(Env env);
		return env.getClientResult();
	}

	@RequestMapping("update")
	public ClientResult update(HttpServletRequest req){
		Env env = (Env) req.getAttribute("env");
		ClientResult clientResult = sysOperationLogApi.update(Env env);
		return env.getClientResult();
	}
}
