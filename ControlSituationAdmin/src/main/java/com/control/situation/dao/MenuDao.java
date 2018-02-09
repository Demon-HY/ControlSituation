package com.control.situation.dao;

import com.control.situation.entity.MenuInfo;

import java.util.List;

/**
 * 数据库层封装
 *
 * @author Demon-Coffee
 * @since 1.0
 */
public interface MenuDao {

	MenuInfo findOne(Long id);

	List<MenuInfo> findAll();

    List<MenuInfo> findByParams(MenuInfo menu);

    int delete(Long id);

    int insert(MenuInfo menu);

    int update(MenuInfo menu);
}
