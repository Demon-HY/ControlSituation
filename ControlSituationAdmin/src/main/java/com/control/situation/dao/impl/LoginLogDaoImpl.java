package com.control.situation.dao.impl;

import com.control.situation.dao.LoginLogDao;
import com.control.situation.entity.LoginLogInfo;
import com.control.situation.mapper.LoginLogMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginLogDaoImpl implements LoginLogDao {

	@Autowired
	private LoginLogMapper loginLogMapper;

	@Override
	public LoginLogInfo findOne(Long id) {
		return loginLogMapper.findOne(id);
	}

	@Override
	public List<LoginLogInfo> findAll() {
	    return loginLogMapper.findAll();
    }

    @Override
    public List<LoginLogInfo> findByParams(LoginLogInfo loginLog) {
		return loginLogMapper.findByParams(loginLog);
	}

	@Override
	public int delete(Long id) {
		return loginLogMapper.delete(id);
	}

	@Override
	public int insert(LoginLogInfo loginLog) {
		return loginLogMapper.insert(loginLog);
	}

	@Override
	public int update(LoginLogInfo loginLog) {
		return loginLogMapper.update(loginLog);
	}
}
