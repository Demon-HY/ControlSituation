package com.control.situation.dao;

import com.control.situation.entity.UserInfo;

/**
 * 数据库层封装
 *
 * @author Demon-Coffee
 * @since 1.0
 */
public interface UserDao {

	// 通过账号查询用户信息
	UserInfo findByAccount(String account);
}
