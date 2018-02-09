package com.control.situation.dao.impl;

import com.control.situation.dao.RoleDao;
import com.control.situation.entity.RoleInfo;
import com.control.situation.mapper.RoleMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleDaoImpl implements RoleDao {

	@Autowired
	private RoleMapper roleMapper;

	@Override
	public RoleInfo findOne(Long id) {
		return roleMapper.findOne(id);
	}

	@Override
	public List<RoleInfo> findAll() {
	    return roleMapper.findAll();
    }

    @Override
    public List<RoleInfo> findByParams(RoleInfo role) {
		return roleMapper.findByParams(role);
	}

	@Override
	public int delete(Long id) {
		return roleMapper.delete(id);
	}

	@Override
	public int insert(RoleInfo role) {
		return roleMapper.insert(role);
	}

	@Override
	public int update(RoleInfo role) {
		return roleMapper.update(role);
	}
}
