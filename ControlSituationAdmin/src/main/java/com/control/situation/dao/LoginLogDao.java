package com.control.situation.dao;

import com.control.situation.entity.LoginLogInfo;

import java.util.List;

/**
 * 数据库层封装
 *
 * @author Demon-Coffee
 * @since 1.0
 */
public interface LoginLogDao {

	LoginLogInfo findOne(Long id);

	List<LoginLogInfo> findAll();

    List<LoginLogInfo> findByParams(LoginLogInfo loginLog);

    int delete(Long id);

    int insert(LoginLogInfo loginLog);

    int update(LoginLogInfo loginLog);
}
