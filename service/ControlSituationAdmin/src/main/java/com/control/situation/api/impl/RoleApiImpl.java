package com.control.situation.api.impl;

import com.control.situation.api.RoleApi;
import com.control.situation.config.Env;
import com.control.situation.dao.RoleDao;
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
public class RoleApiImpl implements RoleApi {

	private Logger logger = Logger.getLogger(RoleApiImpl.class);

	@Autowired
	private RoleDao roleDao;

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
