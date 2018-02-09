package com.control.situation.dao.impl;

import com.control.situation.dao.UserDao;
import com.control.situation.entity.UserInfo;
import com.control.situation.mapper.UserMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDaoImpl implements UserDao {

	@Autowired
	private UserMapper userMapper;

	@Override
	public UserInfo findOne(Long id) {
		return userMapper.findOne(id);
	}

	@Override
	public List<UserInfo> findAll() {
	    return userMapper.findAll();
    }

    @Override
    public List<UserInfo> findByParams(UserInfo user) {
		return userMapper.findByParams(user);
	}

	@Override
	public int delete(Long id) {
		return userMapper.delete(id);
	}

	@Override
	public int insert(UserInfo user) {
		return userMapper.insert(user);
	}

	@Override
	public int update(UserInfo user) {
		return userMapper.update(user);
	}

	@Override
	public UserInfo findByAccount(String account) {
		return userMapper.findByAccount(account);
	}
}
