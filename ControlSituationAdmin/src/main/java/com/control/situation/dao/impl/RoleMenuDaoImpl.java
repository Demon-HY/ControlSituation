package com.control.situation.dao.impl;

import com.control.situation.dao.RoleMenuDao;
import com.control.situation.entity.RoleMenuInfo;
import com.control.situation.mapper.RoleMenuMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleMenuDaoImpl implements RoleMenuDao {

	@Autowired
	private RoleMenuMapper roleMenuMapper;

	@Override
	public RoleMenuInfo findOne(Long id) {
		return roleMenuMapper.findOne(id);
	}

	@Override
	public List<RoleMenuInfo> findAll() {
	    return roleMenuMapper.findAll();
    }

    @Override
    public List<RoleMenuInfo> findByParams(RoleMenuInfo roleMenu) {
		return roleMenuMapper.findByParams(roleMenu);
	}

	@Override
	public int delete(Long id) {
		return roleMenuMapper.delete(id);
	}

	@Override
	public int insert(RoleMenuInfo roleMenu) {
		return roleMenuMapper.insert(roleMenu);
	}

	@Override
	public int update(RoleMenuInfo roleMenu) {
		return roleMenuMapper.update(roleMenu);
	}
}
