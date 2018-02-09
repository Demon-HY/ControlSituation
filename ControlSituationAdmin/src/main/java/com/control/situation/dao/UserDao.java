package com.control.situation.dao;

import com.control.situation.entity.UserInfo;

import java.util.List;

/**
 * 数据库层封装
 *
 * @author Demon-Coffee
 * @since 1.0
 */
public interface UserDao {

	UserInfo findOne(Long id);

	List<UserInfo> findAll();

    List<UserInfo> findByParams(UserInfo user);

    int delete(Long id);

    int insert(UserInfo user);

    int update(UserInfo user);

	/**
	 * 通过账号查询用户
	 * @param account
	 * @return
	 */
	UserInfo findByAccount(String account);
}
