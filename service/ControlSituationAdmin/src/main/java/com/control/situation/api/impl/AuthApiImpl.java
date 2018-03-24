package com.control.situation.api.impl;

import com.control.situation.api.AuthApi;
import com.control.situation.config.Env;
import com.control.situation.dao.UserDao;
import com.control.situation.entity.UserInfo;
import com.control.situation.utils.returns.ClientResult;
import com.control.situation.utils.returns.RetCode;
import com.control.situation.utils.crypto.SSHAUtils;
import com.control.situation.utils.ValidateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Demon-Coffee on 2018/2/7 0007.
 */
@Service
public class AuthApiImpl implements AuthApi {

	@Autowired
	private UserDao userDao;

	@Override
	public ClientResult login(Env env, String account, String password) {
		ClientResult c = env.getClientResult();
		// 检查用户是否存在
		UserInfo user = userDao.findByAccount(account);
		if (ValidateUtils.isEmpty(user)) {
			return c.setCode(RetCode.ERR_ACOUNT_NOT_FOUND);
		}
		// 检查用户状态
		switch (user.getStatus()) {
			case 2:
				return c.setCode(RetCode.ERR_ACCOUNT_LOCK);
			case 3:
				return c.setCode(RetCode.ERR_ACCOUNT_DELETE);
			default:break;
		}
		// 检查密码是否正确
		if (!SSHAUtils.verifySaltedPassword(password, user.getPassword())) {
			return c.setCode(RetCode.ERR_PASSWORD_INVALID);
		}

		return c.setResult(user);
	}
}
