package com.control.situation.mapper;

import com.control.situation.entity.OperationLogInfo;

import java.util.List;

public interface OperationLogMapper {

	OperationLogInfo findOne(Long id);

	List<OperationLogInfo> findAll();

	List<OperationLogInfo> findByParams(OperationLogInfo operationLog);

	int delete(Long id);

	int insert(OperationLogInfo operationLog);

	int update(OperationLogInfo record);
}