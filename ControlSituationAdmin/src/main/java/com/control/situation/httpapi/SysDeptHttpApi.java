package com.control.situation.httpapi;

import org.apache.log4j.Logger;
import com.control.situation.api.SysDeptApi;
import com.control.situation.config.Env;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * SysDept 访问层
 * @author Demon-Coffee
 * @since 1.0
 */
@RestController
@RequestMapping("/sys_dept/api")
public class SysDeptHttpApi {

	private Logger logger = Logger.getLogger(SysDeptHttpApi.class);

	@Autowired
	private SysDeptApi sysDeptApi;

	@RequestMapping("findList")
	public ClientResult findList(HttpServletRequest req) {
		Env env = (Env) req.getAttribute("env");
		ClientResult clientResult = sysDeptApi.findList(Env env);
		return env.getClientResult();
	}

	@RequestMapping("findDetail")
	public ClientResult findDetail(HttpServletRequest req){
		Env env = (Env) req.getAttribute("env");
		ClientResult clientResult = sysDeptApi.findDetail(Env env);
		return env.getClientResult();
	}

	@RequestMapping("save")
	public ClientResult save(HttpServletRequest req){
		Env env = (Env) req.getAttribute("env");
		ClientResult clientResult = sysDeptApi.save(Env env);
		return env.getClientResult();
	}

	@RequestMapping("delete")
	public ClientResult delete(HttpServletRequest req){
		Env env = (Env) req.getAttribute("env");
		ClientResult clientResult = sysDeptApi.delete(Env env);
		return env.getClientResult();
	}

	@RequestMapping("update")
	public ClientResult update(HttpServletRequest req){
		Env env = (Env) req.getAttribute("env");
		ClientResult clientResult = sysDeptApi.update(Env env);
		return env.getClientResult();
	}
}
