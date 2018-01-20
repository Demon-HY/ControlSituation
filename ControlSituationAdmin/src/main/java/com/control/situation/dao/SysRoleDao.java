package com.control.situation.dao;

import com.control.situation.entity.SysRoleInfo;

import java.util.List;

/**
* 数据库层封装
*
* @author Demon-Coffee
* @since 1.0
*/
public interface SysRoleDao {

	SysRoleInfo findOne(Long id);

	List<SysRoleInfo> findAll();

    List<SysRoleInfo> findByParams(SysRoleInfo sysRole);

    int delete(Long id);

    int insert(SysRoleInfo sysRole);

    int update(SysRoleInfo sysRole);
}
