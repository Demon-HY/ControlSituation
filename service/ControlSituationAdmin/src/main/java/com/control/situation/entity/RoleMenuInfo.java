package com.control.situation.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Demon-Coffee
 * @since 1.0
 */
@Table(name = "role_menu")
public class RoleMenuInfo implements Serializable {

	private static final long serialVersionUID = -1L;

	// 主键
	@Id
	@Column(name = "id")
	private Integer id;

	// 菜单ID
	@Column(name = "menu_id")
	private Integer menuId;

	// 角色ID
	@Column(name = "role_id")
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