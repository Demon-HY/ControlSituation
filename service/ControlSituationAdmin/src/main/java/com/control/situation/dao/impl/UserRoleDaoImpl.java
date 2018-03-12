package com.control.situation.dao.impl;

import com.control.situation.dao.UserRoleDao;
import com.demon.utils.db.CommonDao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class UserRoleDaoImpl extends CommonDao implements UserRoleDao {

	@Resource
	private JdbcTemplate jdbcTemplate;

	@Override
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
}
