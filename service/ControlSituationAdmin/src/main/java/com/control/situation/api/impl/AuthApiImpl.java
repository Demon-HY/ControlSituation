package com.control.situation.api.impl;

import com.control.situation.api.AuthApi;
import com.control.situation.api.RedisApi;
import com.control.situation.config.Env;
import com.control.situation.config.SysContants;
import com.control.situation.dao.UserDao;
import com.control.situation.entity.UserInfo;
import com.control.situation.entity.vo.LoginInfo;
import com.control.situation.entity.vo.TokenInfo;
import com.control.situation.utils.classes.SerializeUtils;
import com.control.situation.utils.returns.ClientResult;
import com.control.situation.utils.returns.RetCode;
import com.control.situation.utils.crypto.SSHAUtils;
import com.control.situation.utils.ValidateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

 import static com.control.situation.config.SysContants.COOKIE_EXPIRE;

/**
 * Created by Demon-Coffee on 2018/2/7 0007.
 */
@Service
public class AuthApiImpl implements AuthApi {

	@Autowired
	private UserDao userDao;
	@Autowired
	private RedisApi redisApi;

	@Override
	public ClientResult login(Env env, String account, String password) {
		ClientResult c = env.getCr();

		// 登录前事件

		// 检查用户是否存在
		UserInfo userInfo = userDao.findByAccount(account);
		if (ValidateUtils.isEmpty(userInfo)) {
			return c.setCode(RetCode.ERR_ACOUNT_NOT_FOUND);
		}
		// 检查用户状态
		switch (userInfo.getStatus()) {
			case 2:
				return c.setCode(RetCode.ERR_ACCOUNT_LOCK);
			case 3:
				return c.setCode(RetCode.ERR_ACCOUNT_DELETE);
			default:break;
		}
		// 检查密码是否正确
		if (!SSHAUtils.verifySaltedPassword(password, userInfo.getPassword())) {
			return c.setCode(RetCode.ERR_PASSWORD_INVALID);
		}

		// 登录成功，生成 TokenInfo
		TokenInfo tokenInfo = new TokenInfo(userInfo.getId(), env.clientIP);
		// 将信息存入 redis
		LoginInfo loginInfo = new LoginInfo();
		loginInfo.setTokenInfo(tokenInfo);
		loginInfo.setUserInfo(userInfo);

		// 目前可以不用这么复杂，直接通过 token 保存一个 userId 就可以了
//		redisApi.setBean(tokenInfo.token, loginInfo, SysContants.COOKIE_EXPIRE);
		redisApi.set(tokenInfo.token, userInfo.getId().toString(), COOKIE_EXPIRE);
		// 这里通过用户ID记录token，是为了方便用户权限变更后及时更新用户数据，而不需要重新登录，
		// 并且可以防止多地登陆
		redisApi.set(String.format("%d_token", userInfo.getId()), tokenInfo.token, COOKIE_EXPIRE);

		// 登录后事件

		return c.setResult(userInfo);
	}
}
