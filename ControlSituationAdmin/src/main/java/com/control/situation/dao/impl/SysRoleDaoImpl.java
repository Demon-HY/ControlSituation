package com.control.situation.dao.impl;

import com.control.situation.dao.SysRoleDao;
import com.control.situation.entity.SysRoleInfo;
import com.control.situation.mapper.SysRoleMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysRoleDaoImpl implements SysRoleDao {

	@Autowired
	private SysRoleMapper sysRoleMapper;

	@Override
	public SysRoleInfo findOne(Long id) {
		return sysRoleMapper.findOne(id);
	}

	@Override
	public List<SysRoleInfo> findAll() {
	    return sysRoleMapper.findAll();
    }

    @Override
    public List<SysRoleInfo> findByParams(SysRoleInfo sysRole) {
		return sysRoleMapper.findByParams(sysRole);
	}

	@Override
	public int delete(Long id) {
		return sysRoleMapper.delete(id);
	}

	@Override
	public int insert(SysRoleInfo sysRole) {
		return sysRoleMapper.insert(sysRole);
	}

	@Override
	public int update(SysRoleInfo sysRole) {
		return sysRoleMapper.update(sysRole);
	}
}
