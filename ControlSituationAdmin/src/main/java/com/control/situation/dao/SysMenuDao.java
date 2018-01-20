package com.control.situation.dao;

import com.control.situation.entity.SysMenuInfo;

import java.util.List;

/**
* 数据库层封装
*
* @author Demon-Coffee
* @since 1.0
*/
public interface SysMenuDao {

	SysMenuInfo findOne(Long id);

	List<SysMenuInfo> findAll();

    List<SysMenuInfo> findByParams(SysMenuInfo sysMenu);

    int delete(Long id);

    int insert(SysMenuInfo sysMenu);

    int update(SysMenuInfo sysMenu);
}
