package com.control.situation.api;

import com.control.situation.config.Env;
import com.control.situation.utils.ClientResult;

/**
 * Created by Demon-Coffee on 2018/2/7 0007.
 */
public interface AuthApi {

	/**
	 * 登录接口
	 * @param env 上下文变量
	 * @param account 用户名
	 * @param password 密码
	 * @return 用户信息
	 */
	ClientResult login(Env env, String account, String password);
}
