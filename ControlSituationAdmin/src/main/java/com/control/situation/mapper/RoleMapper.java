package com.control.situation.mapper;

import com.control.situation.entity.RoleInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;

public interface RoleMapper {

	RoleInfo findOne(Long id);

	List<RoleInfo> findAll();

	List<RoleInfo> findByParams(RoleInfo role);

	int delete(Long id);

	int insert(RoleInfo role);

	int update(RoleInfo record);
}