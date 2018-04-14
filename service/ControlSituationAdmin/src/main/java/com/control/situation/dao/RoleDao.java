package com.control.situation.dao;

import com.control.situation.entity.RoleInfo;

import java.util.List;

/**
 * 数据库层封装
 *
 * @author Demon-Coffee
 * @since 1.0
 */
public interface RoleDao {
    /**
     * 查询角色信息
     *
     * @param roleName 角色名
     */
    RoleInfo findByRoleName(String roleName);

    /**
     * 获取当前用户拥有的所有角色
     * @param userId 用户 ID
     * @return 角色集合
     */
    List<RoleInfo> findListByUserId(Long userId);
}
