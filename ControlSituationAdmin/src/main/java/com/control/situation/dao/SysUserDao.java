package com.control.situation.dao;

import com.control.situation.entity.SysUserInfo;

import java.util.List;

/**
* 数据库层封装
*
* @author Demon-Coffee
* @since 1.0
*/
public interface SysUserDao {

	SysUserInfo findOne(Long id);

	List<SysUserInfo> findAll();

    List<SysUserInfo> findByParams(SysUserInfo sysUser);

    int delete(Long id);

    int insert(SysUserInfo sysUser);

    int update(SysUserInfo sysUser);
}
