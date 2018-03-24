package com.control.situation.dao.impl;

import com.control.situation.common.jdbc.CommonDaoImpl;
import com.control.situation.dao.MenuDao;
import com.control.situation.entity.MenuInfo;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class MenuDaoImpl extends CommonDaoImpl<MenuInfo> implements MenuDao {

	@Resource
	private JdbcTemplate jdbcTemplate;

	@Override
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
}
