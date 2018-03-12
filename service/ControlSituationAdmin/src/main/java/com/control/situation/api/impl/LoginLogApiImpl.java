package com.control.situation.api.impl;

import com.control.situation.api.LoginLogApi;
import com.control.situation.config.Env;
import com.control.situation.dao.LoginLogDao;
import com.control.situation.utils.ClientResult;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 业务逻辑处理类
 *
 * @author Demon-Coffee
 * @since 1.0
 */
@Service
public class LoginLogApiImpl implements LoginLogApi {

	private Logger logger = Logger.getLogger(LoginLogApiImpl.class);

	@Autowired
	private LoginLogDao loginLogDao;

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
