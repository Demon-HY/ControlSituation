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
	private Long id;

	// 菜单ID
	@Column(name = "menu_id")
	private Long menuId;

	// 角色ID
	@Column(name = "role_id")
	private Long roleId;


	public RoleMenuInfo(){}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getMenuId() {
		return menuId;
	}

	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
}