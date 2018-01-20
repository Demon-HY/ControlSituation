package com.control.situation.mapper;

import com.control.situation.entity.SysRelationInfo;

import java.util.List;

public interface SysRelationMapper {

	SysRelationInfo findOne(Long id);

	List<SysRelationInfo> findAll();

	List<SysRelationInfo> findByParams(SysRelationInfo sysRelation);

	int delete(Long id);

	int insert(SysRelationInfo sysRelation);

	int update(SysRelationInfo record);
}