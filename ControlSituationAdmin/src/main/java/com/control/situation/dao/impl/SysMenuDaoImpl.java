package com.control.situation.dao.impl;

import com.control.situation.dao.SysMenuDao;
import com.control.situation.entity.SysMenuInfo;
import com.control.situation.mapper.SysMenuMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysMenuDaoImpl implements SysMenuDao {

	@Autowired
	private SysMenuMapper sysMenuMapper;

	@Override
	public SysMenuInfo findOne(Long id) {
		return sysMenuMapper.findOne(id);
	}

	@Override
	public List<SysMenuInfo> findAll() {
	    return sysMenuMapper.findAll();
    }

    @Override
    public List<SysMenuInfo> findByParams(SysMenuInfo sysMenu) {
		return sysMenuMapper.findByParams(sysMenu);
	}

	@Override
	public int delete(Long id) {
		return sysMenuMapper.delete(id);
	}

	@Override
	public int insert(SysMenuInfo sysMenu) {
		return sysMenuMapper.insert(sysMenu);
	}

	@Override
	public int update(SysMenuInfo sysMenu) {
		return sysMenuMapper.update(sysMenu);
	}
}
