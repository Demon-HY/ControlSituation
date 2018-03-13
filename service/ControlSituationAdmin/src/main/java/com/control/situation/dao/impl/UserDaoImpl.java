package com.control.situation.dao.impl;

import com.control.situation.dao.UserDao;
import com.control.situation.entity.UserInfo;
import com.demon.utils.db.CommonDao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class UserDaoImpl extends CommonDao implements UserDao {

	@Resource
	private JdbcTemplate jdbcTemplate;

	@Override
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	@Override
	public UserInfo findByAccount(String account) {
		String sql = " SELECT ";
	}
}
