package com.control.situation.dao;

import com.control.situation.entity.SysDeptInfo;

import java.util.List;

/**
* 数据库层封装
*
* @author Demon-Coffee
* @since 1.0
*/
public interface SysDeptDao {

	SysDeptInfo findOne(Long id);

	List<SysDeptInfo> findAll();

    List<SysDeptInfo> findByParams(SysDeptInfo sysDept);

    int delete(Long id);

    int insert(SysDeptInfo sysDept);

    int update(SysDeptInfo sysDept);
}
