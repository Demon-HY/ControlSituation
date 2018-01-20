package com.control.situation.api;

import com.control.situation.config.Env;
import com.control.situation.utils.ClientResult;

/**
 * 内部模块访问接口
 *
 * @author Demon-Coffee
 * @since 1.0
 */
public interface SysRelationApi {

	ClientResult findList(Env env);

	ClientResult update(Env env);

	ClientResult delete(Env env);

	ClientResult save(Env env);

	ClientResult findDetail(Env env);
}
