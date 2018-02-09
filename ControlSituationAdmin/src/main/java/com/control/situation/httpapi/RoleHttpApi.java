package com.control.situation.httpapi;

import org.apache.log4j.Logger;

import com.control.situation.utils.ClientResult;
import com.control.situation.api.RoleApi;
import com.control.situation.config.Env;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Role 访问层
 * @author Demon-Coffee
 * @since 1.0
 */
@RestController
@RequestMapping("/api/role")
public class RoleHttpApi {

	private Logger logger = Logger.getLogger(RoleHttpApi.class);

	@Autowired
	private RoleApi roleApi;

	@RequestMapping("findList")
	public ClientResult findList(HttpServletRequest req) {
		Env env = (Env) req.getAttribute("env");
		ClientResult clientResult = roleApi.findList(env);
		return clientResult;
	}

	@RequestMapping("findDetail")
	public ClientResult findDetail(HttpServletRequest req){
		Env env = (Env) req.getAttribute("env");
		ClientResult clientResult = roleApi.findDetail(env);
		return clientResult;
	}

	@RequestMapping("save")
	public ClientResult save(HttpServletRequest req){
		Env env = (Env) req.getAttribute("env");
		ClientResult clientResult = roleApi.save(env);
		return clientResult;
	}

	@RequestMapping("delete")
	public ClientResult delete(HttpServletRequest req){
		Env env = (Env) req.getAttribute("env");
		ClientResult clientResult = roleApi.delete(env);
		return clientResult;
	}

	@RequestMapping("update")
	public ClientResult update(HttpServletRequest req){
		Env env = (Env) req.getAttribute("env");
		ClientResult clientResult = roleApi.update(env);
		return clientResult;
	}
}
