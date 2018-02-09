package com.control.situation.dao.impl;

import com.control.situation.dao.MenuDao;
import com.control.situation.entity.MenuInfo;
import com.control.situation.mapper.MenuMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuDaoImpl implements MenuDao {

	@Autowired
	private MenuMapper menuMapper;

	@Override
	public MenuInfo findOne(Long id) {
		return menuMapper.findOne(id);
	}

	@Override
	public List<MenuInfo> findAll() {
	    return menuMapper.findAll();
    }

    @Override
    public List<MenuInfo> findByParams(MenuInfo menu) {
		return menuMapper.findByParams(menu);
	}

	@Override
	public int delete(Long id) {
		return menuMapper.delete(id);
	}

	@Override
	public int insert(MenuInfo menu) {
		return menuMapper.insert(menu);
	}

	@Override
	public int update(MenuInfo menu) {
		return menuMapper.update(menu);
	}
}
