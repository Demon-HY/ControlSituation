package com.control.situation.dao.impl;

import com.control.situation.dao.SysOperationLogDao;
import com.control.situation.entity.SysOperationLogInfo;
import com.control.situation.mapper.SysOperationLogMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysOperationLogDaoImpl implements SysOperationLogDao {

	@Autowired
	private SysOperationLogMapper sysOperationLogMapper;

	@Override
	public SysOperationLogInfo findOne(Long id) {
		return sysOperationLogMapper.findOne(id);
	}

	@Override
	public List<SysOperationLogInfo> findAll() {
	    return sysOperationLogMapper.findAll();
    }

    @Override
    public List<SysOperationLogInfo> findByParams(SysOperationLogInfo sysOperationLog) {
		return sysOperationLogMapper.findByParams(sysOperationLog);
	}

	@Override
	public int delete(Long id) {
		return sysOperationLogMapper.delete(id);
	}

	@Override
	public int insert(SysOperationLogInfo sysOperationLog) {
		return sysOperationLogMapper.insert(sysOperationLog);
	}

	@Override
	public int update(SysOperationLogInfo sysOperationLog) {
		return sysOperationLogMapper.update(sysOperationLog);
	}
}
