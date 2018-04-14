package com.control.situation.httpapi.system;

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

import static com.control.situation.config.SysContants.COOKIE_EXPIRE;

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
	@RequestMapping("/checkLogin.do")
	public ClientResult checkLogin(HttpServletRequest req) {
		Env env = (Env) req.getAttribute("env");

		if (ValidateUtils.isEmpty(env.token)) {
			return env.cr.setCode(RetCode.ERR_USER_NOT_LOGIN);
		}
		String userId = redisApi.get(env.token);
		if (ValidateUtils.isEmpty(userId)) {
			return env.cr.setCode(RetCode.ERR_TOKEN_EXPIRE);
		}

		// token 刷新有效时间
		redisApi.expire(env.token, SysContants.COOKIE_EXPIRE);

		return env.cr.setCode(RetCode.OK);
	}

	/**
	 * 登录
	 */
	@RequestMapping("/login.do")
	public ClientResult login(HttpServletRequest req, HttpServletResponse resp,
	                          String account, String password) {
		Env env = (Env) req.getAttribute("env");

		if (ValidateUtils.isEmpty(account) || ValidateUtils.isEmpty(password)) {
			return env.cr.setCode(RetCode.ERR_BAD_PARAMS);
		}

		// 登录
		return authApi.login(env, account, password);
	}

    /**
     * 退出登录
     */
    @RequestMapping("/logout.do")
    public ClientResult logout(HttpServletRequest req) {
        Env env = (Env) req.getAttribute("env");

        if (ValidateUtils.isEmpty(env.token)) {
            return env.cr.setCode(RetCode.ERR_USER_NOT_LOGIN);
        }
        String userId = redisApi.get(env.token);
        if (ValidateUtils.isEmpty(userId)) {
            return env.cr.setCode(RetCode.ERR_TOKEN_EXPIRE);
        }

        // 清除token
        authApi.logout(env);

        return env.cr.setCode(RetCode.OK);
    }
}
