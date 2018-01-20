package com.control.situation.mapper;

import com.control.situation.entity.SysUserInfo;

import java.util.List;

public interface SysUserMapper {

	SysUserInfo findOne(Long id);

	List<SysUserInfo> findAll();

	List<SysUserInfo> findByParams(SysUserInfo sysUser);

	int delete(Long id);

	int insert(SysUserInfo sysUser);

	int update(SysUserInfo record);
}