package com.control.situation.entity;

import java.io.Serializable;

/**
 * SysDept 实体类
 *
 * @author Demon-Coffee
 * @since 1.0
 */
public class SysDeptInfo implements Serializable {

	private static final long serialVersionUID = -1L;

	// 部门ID
	private Integer id;

	// 排序
	private Integer sort;

	// 父部门id
	private Integer pid;

	// 父级ID集合，0,10,101
	private String pids;

	// 部门名称
	private String deptName;


	public SysDeptInfo() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getPids() {
		return pids;
	}

	public void setPids(String pids) {
		this.pids = pids;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

}