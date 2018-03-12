package com.control.situation.entity;

import java.io.Serializable;

/**
 * UserRole 实体类
 *
 * @author Demon-Coffee
 * @since 1.0
 */
public class UserRoleInfo implements Serializable {

	private static final long serialVersionUID = -1L;

	// 主键
	private Integer id;

	// 用户ID
	private Integer userId;

	// 角色ID
	private Integer roleId;


	public UserRoleInfo(){}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

}