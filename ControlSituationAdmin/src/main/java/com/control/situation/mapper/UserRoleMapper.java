package com.control.situation.mapper;

import com.control.situation.entity.UserRoleInfo;

import java.util.List;

public interface UserRoleMapper {

	UserRoleInfo findOne(Long id);

	List<UserRoleInfo> findAll();

	List<UserRoleInfo> findByParams(UserRoleInfo userRole);

	int delete(Long id);

	int insert(UserRoleInfo userRole);

	int update(UserRoleInfo record);
}