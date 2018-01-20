package com.control.situation.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * SysRelation 实体类
 *
 * @author Demon-Coffee
 * @since 1.0
 */
public class SysRelationInfo implements Serializable {

	private static final long serialVersionUID = -1L;

	// 主键
	private Integer id;

	// 菜单id
	private Integer menuId;

	// 角色id
	private Integer roleId;


	public SysRelationInfo() {
	}

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