package com.control.situation.entity;

import java.io.Serializable;

/**
 * RoleMenu 实体类
 *
 * @author Demon-Coffee
 * @since 1.0
 */
public class RoleMenuInfo implements Serializable {

	private static final long serialVersionUID = -1L;

	// 主键
	private Integer id;

	// 菜单ID
	private Integer menuId;

	// 角色ID
	private Integer roleId;


	public RoleMenuInfo(){}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getMenuId() {
		return menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

}