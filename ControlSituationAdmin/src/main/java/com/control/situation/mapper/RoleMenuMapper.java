package com.control.situation.mapper;

import com.control.situation.entity.RoleMenuInfo;

import java.util.List;

public interface RoleMenuMapper {

	RoleMenuInfo findOne(Long id);

	List<RoleMenuInfo> findAll();

	List<RoleMenuInfo> findByParams(RoleMenuInfo roleMenu);

	int delete(Long id);

	int insert(RoleMenuInfo roleMenu);

	int update(RoleMenuInfo record);
}