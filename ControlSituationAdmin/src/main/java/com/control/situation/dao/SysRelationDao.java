package com.control.situation.dao;

import com.control.situation.entity.SysRelationInfo;

import java.util.List;

/**
* 数据库层封装
*
* @author Demon-Coffee
* @since 1.0
*/
public interface SysRelationDao {

	SysRelationInfo findOne(Long id);

	List<SysRelationInfo> findAll();

    List<SysRelationInfo> findByParams(SysRelationInfo sysRelation);

    int delete(Long id);

    int insert(SysRelationInfo sysRelation);

    int update(SysRelationInfo sysRelation);
}
