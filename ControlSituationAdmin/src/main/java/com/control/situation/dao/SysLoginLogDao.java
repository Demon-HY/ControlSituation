package com.control.situation.dao;

import com.control.situation.entity.SysLoginLogInfo;

import java.util.List;

/**
* 数据库层封装
*
* @author Demon-Coffee
* @since 1.0
*/
public interface SysLoginLogDao {

	SysLoginLogInfo findOne(Long id);

	List<SysLoginLogInfo> findAll();

    List<SysLoginLogInfo> findByParams(SysLoginLogInfo sysLoginLog);

    int delete(Long id);

    int insert(SysLoginLogInfo sysLoginLog);

    int update(SysLoginLogInfo sysLoginLog);
}
