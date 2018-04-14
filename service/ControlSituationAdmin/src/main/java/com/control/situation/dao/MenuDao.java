package com.control.situation.dao;

import com.control.situation.entity.MenuInfo;

import java.util.List;

/**
 * 数据库层封装
 *
 * @author Demon-Coffee
 * @since 1.0
 */
public interface MenuDao {

    /**
     * 获取当前用户拥有的所有菜单
     * @param roleId 角色 ID
     * @return 菜单列表
     */
    List<MenuInfo> findListByRoleId(Integer roleId);
}
