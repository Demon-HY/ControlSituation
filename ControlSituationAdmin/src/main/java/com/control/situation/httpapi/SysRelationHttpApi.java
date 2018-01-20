package com.control.situation.httpapi;

import org.apache.log4j.Logger;
import com.control.situation.api.SysRelationApi;
import com.control.situation.config.Env;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * SysRelation 访问层
 * @author Demon-Coffee
 * @since 1.0
 */
@RestController
@RequestMapping("/sys_relation/api")
public class SysRelationHttpApi {

	private Logger logger = Logger.getLogger(SysRelationHttpApi.class);

	@Autowired
	private SysRelationApi sysRelationApi;

	@RequestMapping("findList")
	public ClientResult findList(HttpServletRequest req) {
		Env env = (Env) req.getAttribute("env");
		ClientResult clientResult = sysRelationApi.findList(Env env);
		return env.getClientResult();
	}

	@RequestMapping("findDetail")
	public ClientResult findDetail(HttpServletRequest req){
		Env env = (Env) req.getAttribute("env");
		ClientResult clientResult = sysRelationApi.findDetail(Env env);
		return env.getClientResult();
	}

	@RequestMapping("save")
	public ClientResult save(HttpServletRequest req){
		Env env = (Env) req.getAttribute("env");
		ClientResult clientResult = sysRelationApi.save(Env env);
		return env.getClientResult();
	}

	@RequestMapping("delete")
	public ClientResult delete(HttpServletRequest req){
		Env env = (Env) req.getAttribute("env");
		ClientResult clientResult = sysRelationApi.delete(Env env);
		return env.getClientResult();
	}

	@RequestMapping("update")
	public ClientResult update(HttpServletRequest req){
		Env env = (Env) req.getAttribute("env");
		ClientResult clientResult = sysRelationApi.update(Env env);
		return env.getClientResult();
	}
}
