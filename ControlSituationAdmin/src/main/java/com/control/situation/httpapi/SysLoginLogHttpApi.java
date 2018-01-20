package com.control.situation.httpapi;

import org.apache.log4j.Logger;
import com.control.situation.api.SysLoginLogApi;
import com.control.situation.config.Env;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * SysLoginLog 访问层
 * @author Demon-Coffee
 * @since 1.0
 */
@RestController
@RequestMapping("/sys_login_log/api")
public class SysLoginLogHttpApi {

	private Logger logger = Logger.getLogger(SysLoginLogHttpApi.class);

	@Autowired
	private SysLoginLogApi sysLoginLogApi;

	@RequestMapping("findList")
	public ClientResult findList(HttpServletRequest req) {
		Env env = (Env) req.getAttribute("env");
		ClientResult clientResult = sysLoginLogApi.findList(Env env);
		return env.getClientResult();
	}

	@RequestMapping("findDetail")
	public ClientResult findDetail(HttpServletRequest req){
		Env env = (Env) req.getAttribute("env");
		ClientResult clientResult = sysLoginLogApi.findDetail(Env env);
		return env.getClientResult();
	}

	@RequestMapping("save")
	public ClientResult save(HttpServletRequest req){
		Env env = (Env) req.getAttribute("env");
		ClientResult clientResult = sysLoginLogApi.save(Env env);
		return env.getClientResult();
	}

	@RequestMapping("delete")
	public ClientResult delete(HttpServletRequest req){
		Env env = (Env) req.getAttribute("env");
		ClientResult clientResult = sysLoginLogApi.delete(Env env);
		return env.getClientResult();
	}

	@RequestMapping("update")
	public ClientResult update(HttpServletRequest req){
		Env env = (Env) req.getAttribute("env");
		ClientResult clientResult = sysLoginLogApi.update(Env env);
		return env.getClientResult();
	}
}
