package com.control.situation.dao.impl;

import com.control.situation.dao.SysUserDao;
import com.control.situation.entity.SysUserInfo;
import com.control.situation.mapper.SysUserMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysUserDaoImpl implements SysUserDao {

	@Autowired
	private SysUserMapper sysUserMapper;

	@Override
	public SysUserInfo findOne(Long id) {
		return sysUserMapper.findOne(id);
	}

	@Override
	public List<SysUserInfo> findAll() {
	    return sysUserMapper.findAll();
    }

    @Override
    public List<SysUserInfo> findByParams(SysUserInfo sysUser) {
		return sysUserMapper.findByParams(sysUser);
	}

	@Override
	public int delete(Long id) {
		return sysUserMapper.delete(id);
	}

	@Override
	public int insert(SysUserInfo sysUser) {
		return sysUserMapper.insert(sysUser);
	}

	@Override
	public int update(SysUserInfo sysUser) {
		return sysUserMapper.update(sysUser);
	}
}
