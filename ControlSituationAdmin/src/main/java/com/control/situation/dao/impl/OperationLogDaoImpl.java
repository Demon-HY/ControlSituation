package com.control.situation.dao.impl;

import com.control.situation.dao.OperationLogDao;
import com.control.situation.entity.OperationLogInfo;
import com.control.situation.mapper.OperationLogMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperationLogDaoImpl implements OperationLogDao {

	@Autowired
	private OperationLogMapper operationLogMapper;

	@Override
	public OperationLogInfo findOne(Long id) {
		return operationLogMapper.findOne(id);
	}

	@Override
	public List<OperationLogInfo> findAll() {
	    return operationLogMapper.findAll();
    }

    @Override
    public List<OperationLogInfo> findByParams(OperationLogInfo operationLog) {
		return operationLogMapper.findByParams(operationLog);
	}

	@Override
	public int delete(Long id) {
		return operationLogMapper.delete(id);
	}

	@Override
	public int insert(OperationLogInfo operationLog) {
		return operationLogMapper.insert(operationLog);
	}

	@Override
	public int update(OperationLogInfo operationLog) {
		return operationLogMapper.update(operationLog);
	}
}
