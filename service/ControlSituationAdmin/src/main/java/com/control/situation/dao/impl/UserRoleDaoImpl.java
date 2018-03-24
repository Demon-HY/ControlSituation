package com.control.situation.dao.impl;

import com.control.situation.common.jdbc.CommonDaoImpl;
import com.control.situation.dao.UserRoleDao;
import com.control.situation.entity.UserRoleInfo;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class UserRoleDaoImpl extends CommonDaoImpl<UserRoleInfo> implements UserRoleDao {

	@Resource
	private JdbcTemplate jdbcTemplate;

	@Override
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

}
