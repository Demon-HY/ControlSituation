package com.control.situation.api.impl;

import com.control.situation.api.MenuApi;
import com.control.situation.api.RedisApi;
import com.control.situation.config.Env;
import com.control.situation.config.SysContants;
import com.control.situation.dao.MenuDao;
import com.control.situation.entity.MenuInfo;
import com.control.situation.entity.RoleInfo;
import com.control.situation.utils.ValidateUtils;
import com.control.situation.utils.returns.ClientResult;
import com.control.situation.utils.returns.RetCode;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

/**
 * 业务逻辑处理类
 *
 * @author Demon-Coffee
 * @since 1.0
 */
@Service
public class MenuApiImpl implements MenuApi {

    private Logger logger = Logger.getLogger(MenuApiImpl.class);

    @Autowired
    private MenuDao menuDao;
    @Autowired
    private RoleApiImpl roleApi;
    @Autowired
    private RedisApiImpl redisApi;

    @Override
    public ClientResult findList(Env env) {
        // 获取当前用户拥有的所有角色
        List<RoleInfo> roles = roleApi.findListByUserId(env.userId);
        if (ValidateUtils.isEmpty(roles)) {
            return env.cr.setCode(RetCode.ERR_USER_NOT_BIND_ROLE);
        }

        // 该用户所拥有的所有菜单
        List<MenuInfo> menuInfoList = new ArrayList<>();
        for (RoleInfo role : roles) {
            List<MenuInfo> menuInfos = menuDao.findListByRoleId(role.getId());
            if (ValidateUtils.notEmpty(menuInfos)) {
                menuInfoList.addAll(menuInfos);
            }
        }

        // 多个角色拥有的菜单会有重复，需要去重
        menuInfoList = new ArrayList<>(new HashSet<>(menuInfoList));
        // 格式化菜单
        menuInfoList = formatMenu(menuInfoList, 0);
        // 将用户所属角色和菜单保存到 Redis 中
        redisApi.setList(String.format(SysContants.USER_ROLE_LIST, env.userId), roles);
        redisApi.setList(String.format(SysContants.USER_MENU_LIST, env.userId), menuInfoList);
        // 设置过期时间
        redisApi.expire(String.format(SysContants.USER_ROLE_LIST, env.userId), SysContants.COOKIE_EXPIRE);
        redisApi.expire(String.format(SysContants.USER_MENU_LIST, env.userId), SysContants.COOKIE_EXPIRE);


        return env.cr.setResult(menuInfoList);
    }

    /**
     * 格式化菜单数据，以树形结构返回
     *
     * @param menuInfoList 待格式化的菜单数据
     * @param pid          父菜单ID，一级菜单的父菜单ID是0
     * @return 树形菜单结构
     */
    private List<MenuInfo> formatMenu(List<MenuInfo> menuInfoList, Integer pid) {
        List<MenuInfo> menus = new ArrayList<>();

        for (MenuInfo menu : menuInfoList) {
            if (Objects.equals(menu.getPid(), pid)) {
                menu.setChildMenus(formatMenu(menuInfoList, menu.getId()));
            }
            menus.add(menu);
        }

        return menus;
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
