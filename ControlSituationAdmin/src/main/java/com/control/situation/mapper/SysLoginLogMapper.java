package com.control.situation.mapper;

import com.control.situation.entity.SysLoginLogInfo;

import java.util.List;

public interface SysLoginLogMapper {

	SysLoginLogInfo findOne(Long id);

	List<SysLoginLogInfo> findAll();

	List<SysLoginLogInfo> findByParams(SysLoginLogInfo sysLoginLog);

	int delete(Long id);

	int insert(SysLoginLogInfo sysLoginLog);

	int update(SysLoginLogInfo record);
}