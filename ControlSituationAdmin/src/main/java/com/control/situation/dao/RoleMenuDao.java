package com.control.situation.dao;

import com.control.situation.entity.RoleMenuInfo;

import java.util.List;

/**
 * 数据库层封装
 *
 * @author Demon-Coffee
 * @since 1.0
 */
public interface RoleMenuDao {

	RoleMenuInfo findOne(Long id);

	List<RoleMenuInfo> findAll();

    List<RoleMenuInfo> findByParams(RoleMenuInfo roleMenu);

    int delete(Long id);

    int insert(RoleMenuInfo roleMenu);

    int update(RoleMenuInfo roleMenu);
}
