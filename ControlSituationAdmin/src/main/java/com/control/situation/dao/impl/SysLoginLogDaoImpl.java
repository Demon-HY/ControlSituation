package com.control.situation.dao.impl;

import com.control.situation.dao.SysLoginLogDao;
import com.control.situation.entity.SysLoginLogInfo;
import com.control.situation.mapper.SysLoginLogMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysLoginLogDaoImpl implements SysLoginLogDao {

	@Autowired
	private SysLoginLogMapper sysLoginLogMapper;

	@Override
	public SysLoginLogInfo findOne(Long id) {
		return sysLoginLogMapper.findOne(id);
	}

	@Override
	public List<SysLoginLogInfo> findAll() {
	    return sysLoginLogMapper.findAll();
    }

    @Override
    public List<SysLoginLogInfo> findByParams(SysLoginLogInfo sysLoginLog) {
		return sysLoginLogMapper.findByParams(sysLoginLog);
	}

	@Override
	public int delete(Long id) {
		return sysLoginLogMapper.delete(id);
	}

	@Override
	public int insert(SysLoginLogInfo sysLoginLog) {
		return sysLoginLogMapper.insert(sysLoginLog);
	}

	@Override
	public int update(SysLoginLogInfo sysLoginLog) {
		return sysLoginLogMapper.update(sysLoginLog);
	}
}
