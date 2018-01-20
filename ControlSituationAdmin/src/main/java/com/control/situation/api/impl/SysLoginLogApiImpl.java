package com.control.situation.api.impl;

import com.control.situation.api.SysLoginLogApi;
import com.control.situation.config.Env;
import com.control.situation.dao.SysLoginLogDao;
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
public class SysLoginLogApiImpl implements SysLoginLogApi {

	private Logger logger = Logger.getLogger(SysLoginLogApiImpl.class);

	@Autowired
	private SysLoginLogDao sysLoginLogDao;

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
