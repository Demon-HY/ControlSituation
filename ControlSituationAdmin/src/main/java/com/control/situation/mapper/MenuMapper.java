package com.control.situation.mapper;

import com.control.situation.entity.MenuInfo;

import java.util.List;

public interface MenuMapper {

	MenuInfo findOne(Long id);

	List<MenuInfo> findAll();

	List<MenuInfo> findByParams(MenuInfo menu);

	int delete(Long id);

	int insert(MenuInfo menu);

	int update(MenuInfo record);
}