package com.control.situation.mapper;

import com.control.situation.entity.SysRoleInfo;

import java.util.List;

public interface SysRoleMapper {

	SysRoleInfo findOne(Long id);

	List<SysRoleInfo> findAll();

	List<SysRoleInfo> findByParams(SysRoleInfo sysRole);

	int delete(Long id);

	int insert(SysRoleInfo sysRole);

	int update(SysRoleInfo record);
}