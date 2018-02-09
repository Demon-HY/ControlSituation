package com.control.situation.dao;

import com.control.situation.entity.UserRoleInfo;

import java.util.List;

/**
 * 数据库层封装
 *
 * @author Demon-Coffee
 * @since 1.0
 */
public interface UserRoleDao {

	UserRoleInfo findOne(Long id);

	List<UserRoleInfo> findAll();

    List<UserRoleInfo> findByParams(UserRoleInfo userRole);

    int delete(Long id);

    int insert(UserRoleInfo userRole);

    int update(UserRoleInfo userRole);
}
