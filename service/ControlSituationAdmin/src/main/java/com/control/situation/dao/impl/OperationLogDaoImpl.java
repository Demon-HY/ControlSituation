package com.control.situation.dao.impl;

import com.control.situation.dao.OperationLogDao;
import com.demon.utils.db.CommonDao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class OperationLogDaoImpl extends CommonDao implements OperationLogDao {

	@Resource
	private JdbcTemplate jdbcTemplate;

	@Override
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
}
