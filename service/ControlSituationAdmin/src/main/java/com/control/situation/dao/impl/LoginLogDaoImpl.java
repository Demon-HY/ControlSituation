package com.control.situation.dao.impl;

import com.control.situation.common.jdbc.CommonDaoImpl;
import com.control.situation.dao.LoginLogDao;
import com.control.situation.entity.LoginLogInfo;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class LoginLogDaoImpl extends CommonDaoImpl<LoginLogInfo> implements LoginLogDao {

	@Resource
	private JdbcTemplate jdbcTemplate;

	@Override
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}


}
