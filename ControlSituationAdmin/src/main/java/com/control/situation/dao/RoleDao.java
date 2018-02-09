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

	RoleInfo findOne(Long id);

	List<RoleInfo> findAll();

    List<RoleInfo> findByParams(RoleInfo role);

    int delete(Long id);

    int insert(RoleInfo role);

    int update(RoleInfo role);
}
