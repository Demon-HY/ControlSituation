package com.control.situation.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author Demon-Coffee
 * @since 1.0
 */
@Table(name = "menu")
public class MenuInfo implements Serializable {

	private static final long serialVersionUID = -1L;

	// 菜单ID
	@Id
	@Column(name = "id")
	private Integer id;

	// 菜单父编号
	@Column(name = "pid")
	private Integer pid;

	// 当前菜单的所有父菜单编号
	@Column(name = "pids")
	private String pids;

	// 菜单名称
	@Column(name = "name")
	private String name;

	// url地址
	@Column(name = "url")
	private String url;

	// 菜单排序值
	@Column(name = "sort")
	private Integer sort;

	// 菜单层级,(接口没有层级)
	@Column(name = "level")
	private Integer level;

	// 是否有子节点
	@Column(name = "is_children")
	private Integer isChildren;

	// 是否是菜单：1-是，0-不是(接口路由)
	@Column(name = "menu")
	private Integer menu;

	// 菜单状态：1-启用，0-不启用
	@Column(name = "enable")
	private Integer enable;

	// 是否展开：1-打开，0-不打开
	@Column(name = "open")
	private Integer open;

	// 创建时间
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "create_time")
	private Date createTime;

	// 更新时间
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "update_time")
	private Date updateTime;

	// 备注
	@Column(name = "remark")
	private String remark;

    /**
     * 子菜单
     */
    private List<MenuInfo> childMenus;

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

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getIsChildren() {
		return isChildren;
	}

	public void setIsChildren(Integer isChildren) {
		this.isChildren = isChildren;
	}

	public Integer getMenu() {
		return menu;
	}

	public void setMenu(Integer menu) {
		this.menu = menu;
	}

	public Integer getEnable() {
		return enable;
	}

	public void setEnable(Integer enable) {
		this.enable = enable;
	}

	public Integer getOpen() {
		return open;
	}

	public void setOpen(Integer open) {
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

    public List<MenuInfo> getChildMenus() {
        return childMenus;
    }

    public void setChildMenus(List<MenuInfo> childMenus) {
        this.childMenus = childMenus;
    }
}