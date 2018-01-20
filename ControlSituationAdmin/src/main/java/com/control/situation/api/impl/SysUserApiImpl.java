package com.control.situation.api.impl;

import com.control.situation.api.sysUserApi;
import com.control.situation.config.Env;
import com.control.situation.dao.sysUserDao;
import com.control.situation.utils.ClientResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.apache.log4j.Logger;

/**
 * 业务逻辑处理类
 *
 * @author Demon-Coffee
 * @since 1.0
 */
@Service
public class SysUserApiImpl implements SysUserApi {

	private Logger logger = Logger.getLogger(SysUserApiImpl.class);

	@Autowired
	private SysUserDao sysUserDao;

	@Override
	public ClientResult findList(Env env) {
	return null;
	}

	@Override
	public ClientResult update(Env env) {
	return null;
	}

	@Override
	public ClientResult delete(Env env) {
	return null;
	}

	@Override
	public ClientResult save(Env env) {
	return null;
	}

	@Override
	public ClientResult findDetail(Env env) {
	return null;
	}
}
