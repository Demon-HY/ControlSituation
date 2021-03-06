package com.control.situation.api;

import com.control.situation.config.Env;
import com.control.situation.utils.returns.ClientResult;

/**
 * 内部模块访问接口
 *
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

    /**
     * 退出登录
     * @param env 上下文变量
     */
    void logout(Env env);
}
