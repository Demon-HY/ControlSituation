package com.control.situation.mapper;

import com.control.situation.entity.SysDeptInfo;

import java.util.List;

public interface SysDeptMapper {

	SysDeptInfo findOne(Long id);

	List<SysDeptInfo> findAll();

	List<SysDeptInfo> findByParams(SysDeptInfo sysDept);

	int delete(Long id);

	int insert(SysDeptInfo sysDept);

	int update(SysDeptInfo record);
}