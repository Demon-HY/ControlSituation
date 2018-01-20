package com.control.situation.mapper;

import com.control.situation.entity.SysMenuInfo;

import java.util.List;

public interface SysMenuMapper {

	SysMenuInfo findOne(Long id);

	List<SysMenuInfo> findAll();

	List<SysMenuInfo> findByParams(SysMenuInfo sysMenu);

	int delete(Long id);

	int insert(SysMenuInfo sysMenu);

	int update(SysMenuInfo record);
}