package com.control.situation.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * Menu 实体类
 *
 * @author Demon-Coffee
 * @since 1.0
 */
public class MenuInfo implements Serializable {

	private static final long serialVersionUID = -1L;

	// 菜单ID
	@Column
	private Integer id;

	// 菜单父编号
	private Integer pid;

	// 当前菜单的所有父菜单编号
	private String pids;

	// 菜单名称
	private String name;

	// 菜单图标
	private String icon;

	// url地址
	private String url;

	// 菜单排序值
	private Integer sort;

	// 菜单层级,(接口没有层级)
	private Boolean level;

	// 是否有子节点
	private Boolean isChildren;

	// 是否是菜单：1-是，0-不是(接口路由)
	private Boolean menu;

	// 菜单状态：1-启用，0-不启用
	private Boolean enable;

	// 是否展开：1-打开，0-不打开
	private Boolean open;

	// 创建时间
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;

	// 更新时间
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updateTime;

	// 备注
	private String remark;


	public MenuInfo(){}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Boolean getLevel() {
		return level;
	}

	public void setLevel(Boolean level) {
		this.level = level;
	}

	public Boolean getIsChildren() {
		return isChildren;
	}

	public void setIsChildren(Boolean isChildren) {
		this.isChildren = isChildren;
	}

	public Boolean getMenu() {
		return menu;
	}

	public void setMenu(Boolean menu) {
		this.menu = menu;
	}

	public Boolean getEnable() {
		return enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}

	public Boolean getOpen() {
		return open;
	}

	public void setOpen(Boolean open) {
		this.open = open;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}