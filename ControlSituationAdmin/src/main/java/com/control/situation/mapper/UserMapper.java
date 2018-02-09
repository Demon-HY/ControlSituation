package com.control.situation.mapper;

import com.control.situation.entity.UserInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserMapper {

	UserInfo findOne(Long id);

	List<UserInfo> findAll();

	List<UserInfo> findByParams(UserInfo user);

	int delete(Long id);

	int insert(UserInfo user);

	int update(UserInfo record);

	UserInfo findByAccount(@Param("account") String account);
}