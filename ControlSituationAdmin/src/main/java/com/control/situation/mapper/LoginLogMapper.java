package com.control.situation.mapper;

import com.control.situation.entity.LoginLogInfo;

import java.util.List;

public interface LoginLogMapper {

	LoginLogInfo findOne(Long id);

	List<LoginLogInfo> findAll();

	List<LoginLogInfo> findByParams(LoginLogInfo loginLog);

	int delete(Long id);

	int insert(LoginLogInfo loginLog);

	int update(LoginLogInfo record);
}