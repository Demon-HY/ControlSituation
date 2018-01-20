package com.control.situation.httpapi;

import org.apache.log4j.Logger;

import com.control.situation.utils.ClientResult;
import com.control.situation.api.SysUserApi;
import com.control.situation.config.Env;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * SysUser 访问层
 * @author Demon-Coffee
 * @since 1.0
 */
@RestController
@RequestMapping("/sys_user/api")
public class SysUserHttpApi {

	private Logger logger = Logger.getLogger(SysUserHttpApi.class);

	@Autowired
	private SysUserApi sysUserApi;

	@RequestMapping("findList")
	public ClientResult findList(HttpServletRequest req) {
		Env env = (Env) req.getAttribute("env");
		ClientResult clientResult = sysUserApi.findList(env);
		return clientResult;
	}

	@RequestMapping("findDetail")
	public ClientResult findDetail(HttpServletRequest req){
		Env env = (Env) req.getAttribute("env");
		ClientResult clientResult = sysUserApi.findDetail(env);
		return clientResult;
	}

	@RequestMapping("save")
	public ClientResult save(HttpServletRequest req){
		Env env = (Env) req.getAttribute("env");
		ClientResult clientResult = sysUserApi.save(env);
		return clientResult;
	}

	@RequestMapping("delete")
	public ClientResult delete(HttpServletRequest req){
		Env env = (Env) req.getAttribute("env");
		ClientResult clientResult = sysUserApi.delete(env);
		return clientResult;
	}

	@RequestMapping("update")
	public ClientResult update(HttpServletRequest req){
		Env env = (Env) req.getAttribute("env");
		ClientResult clientResult = sysUserApi.update(env);
		return clientResult;
	}
}
