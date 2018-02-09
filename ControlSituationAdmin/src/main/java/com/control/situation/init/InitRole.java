package com.control.situation.init;

import com.control.situation.dao.RoleDao;
import com.control.situation.entity.RoleInfo;
import com.demon.utils.ValidateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by Demon-Coffee on 2018/2/7 0007.
 */
@Service
public class InitRole {

	@Autowired
	private Environment env;
	@Autowired
	private RoleDao roleDao;

	/**
	 * 初始化角色
	 */
	public void initRole() {
		String _roleNames = env.getProperty("control.role.init.name");
		String[] roleNames = _roleNames.split(",");
		for (String name : roleNames) {
			String roleName = env.getProperty(String.format("control.role.init.%s.name", name));
			String roleDescription = env.getProperty(String.format("control.role.init.%s.description", name));

			// 检查默认角色是否已创建
			List<RoleInfo> roles = roleDao.findByParams(new RoleInfo(roleName));
			if (ValidateUtils.notEmpty(roles)) {
				System.out.println(String.format("Role %s is exists!", roleName));
				continue;
			}

			RoleInfo role = new RoleInfo();
			role.setName(roleName);
			role.setDescription(roleDescription);
			role.setCreateTime(new Date());
			role.setUpdateTime(new Date());
			roleDao.insert(role);
			System.out.println(String.format("Role %s created.", roleName));
		}


	}

}
