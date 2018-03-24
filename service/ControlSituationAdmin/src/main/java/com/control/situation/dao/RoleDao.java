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
}
