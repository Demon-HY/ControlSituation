package com.control.situation.dao.impl;

import com.control.situation.dao.UserRoleDao;
import com.control.situation.entity.UserRoleInfo;
import com.control.situation.mapper.UserRoleMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleDaoImpl implements UserRoleDao {

	@Autowired
	private UserRoleMapper userRoleMapper;

	@Override
	public UserRoleInfo findOne(Long id) {
		return userRoleMapper.findOne(id);
	}

	@Override
	public List<UserRoleInfo> findAll() {
	    return userRoleMapper.findAll();
    }

    @Override
    public List<UserRoleInfo> findByParams(UserRoleInfo userRole) {
		return userRoleMapper.findByParams(userRole);
	}

	@Override
	public int delete(Long id) {
		return userRoleMapper.delete(id);
	}

	@Override
	public int insert(UserRoleInfo userRole) {
		return userRoleMapper.insert(userRole);
	}

	@Override
	public int update(UserRoleInfo userRole) {
		return userRoleMapper.update(userRole);
	}
}
