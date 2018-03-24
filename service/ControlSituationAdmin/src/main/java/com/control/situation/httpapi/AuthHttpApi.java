package com.control.situation.httpapi;

import com.control.situation.api.AuthApi;
import com.control.situation.api.RedisApi;
import com.control.situation.config.Env;
import com.control.situation.config.SysContants;
import com.control.situation.entity.UserInfo;
import com.control.situation.utils.RandomUtil;
import com.control.situation.utils.ValidateUtils;
import com.control.situation.utils.http.CookieUtils;
import com.control.situation.utils.returns.ClientResult;
import com.control.situation.utils.returns.RetCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 登录认证
 *
 * Created by Demon-Coffee on 2018/2/5 0005.
 */
@RestController
@RequestMapping("/api/auth")
public class AuthHttpApi {

	@Autowired
	private RedisApi redisApi;
	@Autowired
	private AuthApi authApi;

	/**
	 * 验证登录
	 */
	@RequestMapping("/checkLogin")
	public ClientResult checkLogin(HttpServletRequest req) {
		Env env = (Env) req.getAttribute("env");
		ClientResult c = env.getClientResult();

		if (ValidateUtils.isEmpty(env.getUserId())) {
			return c.setCode(RetCode.ERR_USER_NOT_LOGIN)
					.setMessage("请登录");
		}
		String token = redisApi.get(env.getUserId());
		if (ValidateUtils.isEmpty(token)) {
			return c.setCode(RetCode.ERR_TOKEN_EXPIRE)
					.setMessage("登录信息失效");
		}
		// token 刷新有效时间
		redisApi.expire(token, 30 * 60);

		return c.setCode(RetCode.OK);
	}

	/**
	 * 登录
	 */
	@RequestMapping("/login")
	public ClientResult login(HttpServletRequest req, HttpServletResponse resp,
	                          String account, String password) {
		Env env = (Env) req.getAttribute("env");
		ClientResult c = env.getClientResult();

		if (ValidateUtils.isEmpty(account) || ValidateUtils.isEmpty(password)) {
			return c.setCode(RetCode.ERR_BAD_PARAMS);
		}

		c = authApi.login(env, account, password);
		if (!c.getCode().equals(RetCode.OK)) {
			return c;
		}
		UserInfo user = (UserInfo) c.getResult();
		// 创建用户唯一标识
		String token = RandomUtil.getRequestId();
		// 写入缓存
		boolean status = redisApi.set(token, String.valueOf(user.getId()), SysContants.COOKIE_EXPIRE);
		if (!status) {
			return c.setCode(RetCode.ERR_WRITE_REDIS_FAILED);
		}
		CookieUtils.addCookie(resp, "token", token, SysContants.COOKIE_EXPIRE);

		user.setPassword("");
		Map<String, Object> result = new LinkedHashMap<>();
		result.put("token", token);
		result.put("user", user);

		return c.setResult(result);
	}
}
