package com.control.situation.dao.impl;

import com.control.situation.dao.SysRelationDao;
import com.control.situation.entity.SysRelationInfo;
import com.control.situation.mapper.SysRelationMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysRelationDaoImpl implements SysRelationDao {

	@Autowired
	private SysRelationMapper sysRelationMapper;

	@Override
	public SysRelationInfo findOne(Long id) {
		return sysRelationMapper.findOne(id);
	}

	@Override
	public List<SysRelationInfo> findAll() {
	    return sysRelationMapper.findAll();
    }

    @Override
    public List<SysRelationInfo> findByParams(SysRelationInfo sysRelation) {
		return sysRelationMapper.findByParams(sysRelation);
	}

	@Override
	public int delete(Long id) {
		return sysRelationMapper.delete(id);
	}

	@Override
	public int insert(SysRelationInfo sysRelation) {
		return sysRelationMapper.insert(sysRelation);
	}

	@Override
	public int update(SysRelationInfo sysRelation) {
		return sysRelationMapper.update(sysRelation);
	}
}
