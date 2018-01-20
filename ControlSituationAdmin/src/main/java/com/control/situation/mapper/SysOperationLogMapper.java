package com.control.situation.mapper;

import com.control.situation.entity.SysOperationLogInfo;

import java.util.List;

public interface SysOperationLogMapper {

	SysOperationLogInfo findOne(Long id);

	List<SysOperationLogInfo> findAll();

	List<SysOperationLogInfo> findByParams(SysOperationLogInfo sysOperationLog);

	int delete(Long id);

	int insert(SysOperationLogInfo sysOperationLog);

	int update(SysOperationLogInfo record);
}