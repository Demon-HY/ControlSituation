package com.control.situation.dao.impl;

import com.control.situation.dao.SysDeptDao;
import com.control.situation.entity.SysDeptInfo;
import com.control.situation.mapper.SysDeptMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysDeptDaoImpl implements SysDeptDao {

	@Autowired
	private SysDeptMapper sysDeptMapper;

	@Override
	public SysDeptInfo findOne(Long id) {
		return sysDeptMapper.findOne(id);
	}

	@Override
	public List<SysDeptInfo> findAll() {
	    return sysDeptMapper.findAll();
    }

    @Override
    public List<SysDeptInfo> findByParams(SysDeptInfo sysDept) {
		return sysDeptMapper.findByParams(sysDept);
	}

	@Override
	public int delete(Long id) {
		return sysDeptMapper.delete(id);
	}

	@Override
	public int insert(SysDeptInfo sysDept) {
		return sysDeptMapper.insert(sysDept);
	}

	@Override
	public int update(SysDeptInfo sysDept) {
		return sysDeptMapper.update(sysDept);
	}
}
