package com.control.situation.dao.impl;

import com.control.situation.common.jdbc.CommonDaoImpl;
import com.control.situation.dao.RoleDao;
import com.control.situation.entity.RoleInfo;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class RoleDaoImpl extends CommonDaoImpl<RoleInfo> implements RoleDao {

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Override
    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    @Override
    public RoleInfo findByRoleName(String roleName) {
        return null;
    }
}
