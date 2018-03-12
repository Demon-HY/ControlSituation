package com.control.situation.httpapi;

import com.control.situation.api.RoleMenuApi;
import com.control.situation.config.Env;
import com.control.situation.utils.ClientResult;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * RoleMenu 访问层
 * @author Demon-Coffee
 * @since 1.0
 */
@RestController
@RequestMapping("/api/roleMenu")
public class RoleMenuHttpApi {

	private Logger logger = Logger.getLogger(RoleMenuHttpApi.class);

	@Autowired
	private RoleMenuApi roleMenuApi;

	@RequestMapping("findList")
	public ClientResult findList(HttpServletRequest req) {
		Env env = (Env) req.getAttribute("env");
		ClientResult clientResult = roleMenuApi.findList(env);
		return clientResult;
	}

	@RequestMapping("findDetail")
	public ClientResult findDetail(HttpServletRequest req){
		Env env = (Env) req.getAttribute("env");
		ClientResult clientResult = roleMenuApi.findDetail(env);
		return clientResult;
	}

	@RequestMapping("save")
	public ClientResult save(HttpServletRequest req){
		Env env = (Env) req.getAttribute("env");
		ClientResult clientResult = roleMenuApi.save(env);
		return clientResult;
	}

	@RequestMapping("delete")
	public ClientResult delete(HttpServletRequest req){
		Env env = (Env) req.getAttribute("env");
		ClientResult clientResult = roleMenuApi.delete(env);
		return clientResult;
	}

	@RequestMapping("update")
	public ClientResult update(HttpServletRequest req){
		Env env = (Env) req.getAttribute("env");
		ClientResult clientResult = roleMenuApi.update(env);
		return clientResult;
	}
}
