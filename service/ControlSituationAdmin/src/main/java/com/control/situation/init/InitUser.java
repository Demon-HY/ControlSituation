package com.control.situation.init;

import com.alibaba.fastjson.JSONObject;
import com.control.situation.dao.impl.RoleDaoImpl;
import com.control.situation.dao.impl.UserDaoImpl;
import com.control.situation.dao.impl.UserRoleDaoImpl;
import com.control.situation.entity.RoleInfo;
import com.control.situation.entity.UserInfo;
import com.control.situation.entity.UserRoleInfo;
import com.control.situation.utils.crypto.SSHAUtils;
import com.control.situation.utils.ValidateUtils;
import com.control.situation.utils.exception.LogicalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 用户初始化,在程序启动时执行
 *
 * Created by Demon on 2018/2/7 0007.
 */
@Service
public class InitUser {
    
    @Autowired
    private Environment env;
    @Autowired
    private UserDaoImpl userDao;
    @Autowired
    private RoleDaoImpl roleDao;
    @Autowired
    private UserRoleDaoImpl userRoleDao;

    /**
     * 初始化用户，如果已创建，目前则不做处理
     */
    public void initUser() throws LogicalException {
        String _userNames = env.getProperty("control.user.init.name");
        String[] names = _userNames.split(",");
        for (String name : names) {
            try {
                if (name.trim().length() == 0) {
                    continue;
                }
                String account = env.getProperty(String.format("control.user.init.%s.username", name));
                String password = env.getProperty(String.format("control.user.init.%s.password", name));
                String attrStr  = env.getProperty(String.format("control.user.init.%s.attrs", name));

                JSONObject attrs = JSONObject.parseObject(attrStr);

	            UserInfo user = userDao.findByAccount(account);
	            if (user != null) {
		            System.out.println(String.format("User %s is exists!", account));
		            continue;
	            }
	            user = new UserInfo();
	            user.setAccount(account);
	            user.setPassword(SSHAUtils.getSaltedPassword(password));
	            user.setNickName(attrs.getString("nick"));
	            user.setEmail(attrs.getString("email"));
	            user.setStatus(attrs.getInteger("status"));
	            user.setCreateTime(new Date());
	            user.setUpdateTime(new Date());
	            userDao.insert(user);
	            System.out.println(String.format("User %s created.", account));

	            // 获取用户 ID
                user = userDao.findByAccount(account);
                // 设置用户角色
                // 检查默认角色是否已创建
                RoleInfo role = roleDao.findByRoleName(attrs.getString("role"));
                if (ValidateUtils.isEmpty(role)) {
                    throw new LogicalException("INIT_ERROR", "Please create role...");
                }
	            UserRoleInfo userRoleInfo = new UserRoleInfo();
	            userRoleInfo.setUserId(user.getId());
	            userRoleInfo.setRoleId(role.getId());
	            userRoleDao.insert(userRoleInfo);
	            System.out.println(String.format("User %s binding role %s.", user.getAccount(), role.getName()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
