package com.control.situation.dao.impl;

import com.control.situation.common.jdbc.CommonDao;
import com.control.situation.common.jdbc.CommonDaoImpl;
import com.control.situation.dao.UserDao;
import com.control.situation.entity.UserInfo;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class UserDaoImpl extends CommonDaoImpl<UserInfo> implements UserDao {

	@Resource
	private JdbcTemplate jdbcTemplate;

	@Override
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	@Override
	public UserInfo findByAccount(String account) {
        CommonDao.Criteria criteria = this.createCriteria();
        criteria.eq("account", account);
        return selectOneByCriteria(criteria, UserInfo.class);
	}
}
