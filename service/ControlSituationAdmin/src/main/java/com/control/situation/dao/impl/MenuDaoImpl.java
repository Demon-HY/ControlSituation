package com.control.situation.dao.impl;

import com.control.situation.common.jdbc.CommonDaoImpl;
import com.control.situation.dao.MenuDao;
import com.control.situation.entity.MenuInfo;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class MenuDaoImpl extends CommonDaoImpl<MenuInfo> implements MenuDao {

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Override
    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    @Override
    public List<MenuInfo> findListByRoleId(Integer roleId) {
        String sql = String.format(" SELECT %s FROM menu WHERE id IN ( SELECT menu_id FROM role_menu WHERE role_id=? )",
                this.getFields(MenuInfo.class));

        return getJdbcTemplate().query(sql, new BeanPropertyRowMapper<>(MenuInfo.class), roleId);
    }
}
