package com.control.situation.dao;

import com.control.situation.entity.SysOperationLogInfo;

import java.util.List;

/**
* 数据库层封装
*
* @author Demon-Coffee
* @since 1.0
*/
public interface SysOperationLogDao {

	SysOperationLogInfo findOne(Long id);

	List<SysOperationLogInfo> findAll();

    List<SysOperationLogInfo> findByParams(SysOperationLogInfo sysOperationLog);

    int delete(Long id);

    int insert(SysOperationLogInfo sysOperationLog);

    int update(SysOperationLogInfo sysOperationLog);
}
