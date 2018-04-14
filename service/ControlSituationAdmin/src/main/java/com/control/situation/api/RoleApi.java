package com.control.situation.api;

import com.control.situation.config.Env;
import com.control.situation.entity.RoleInfo;
import com.control.situation.utils.returns.ClientResult;

import java.util.List;

/**
 * 内部模块访问接口
 *
 * @author Demon-Coffee
 * @since 1.0
 */
public interface RoleApi {

	ClientResult findList(Env env);

	ClientResult update(Env env);

	ClientResult delete(Env env);

	ClientResult save(Env env);

	ClientResult findDetail(Env env);

    /**
     * 获取当前用户拥有的所有角色
     * @param userId 用户ID
     */
    List<RoleInfo> findListByUserId(Long userId);
}
