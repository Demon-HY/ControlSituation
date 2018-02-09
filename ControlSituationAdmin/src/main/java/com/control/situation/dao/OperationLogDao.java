package com.control.situation.dao;

import com.control.situation.entity.OperationLogInfo;

import java.util.List;

/**
 * 数据库层封装
 *
 * @author Demon-Coffee
 * @since 1.0
 */
public interface OperationLogDao {

	OperationLogInfo findOne(Long id);

	List<OperationLogInfo> findAll();

    List<OperationLogInfo> findByParams(OperationLogInfo operationLog);

    int delete(Long id);

    int insert(OperationLogInfo operationLog);

    int update(OperationLogInfo operationLog);
}
