package com.control.situation.entity.vo;

import com.control.situation.entity.MenuInfo;

import java.util.List;

/**
 * Created by Administrator on 2018/4/14 0014.
 */
public class MenuInfoVo extends MenuInfo {

    private List<MenuInfo> childMenus;

    @Override
    public List<MenuInfo> getChildMenus() {
        return childMenus;
    }

    @Override
    public void setChildMenus(List<MenuInfo> childMenus) {
        this.childMenus = childMenus;
    }
}
