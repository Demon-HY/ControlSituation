package com.control.situation.api;

import com.control.situation.config.Env;
import com.control.situation.utils.returns.ClientResult;

/**
 * 内部模块访问接口
 *
 * @author Demon-Coffee
 * @since 1.0
 */
public interface MenuApi {

    /**
     * 获取用户所拥有的所有菜单权限
     * @param env
     * @return
     */
	ClientResult findList(Env env);

	ClientResult update(Env env);

	ClientResult delete(Env env);

	ClientResult save(Env env);

	ClientResult findDetail(Env env);
}
