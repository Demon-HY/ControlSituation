package com.control.situation.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * OperationLog 实体类
 *
 * @author Demon-Coffee
 * @since 1.0
 */
public class OperationLogInfo implements Serializable {

	private static final long serialVersionUID = -1L;

	// 主键
	private Integer id;

	// 日志类型
	private String logType;

	// 日志名称
	private String logName;

	// 用户id
	private Integer userId;

	// 模块名称
	private String moduleName;

	// 操作数据
	private String operationData;

	// 创建时间
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createtime;

	// 是否成功
	private String succeed;

	// 备注
	private String remark;


	public OperationLogInfo(){}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLogType() {
		return logType;
	}

	public void setLogType(String logType) {
		this.logType = logType;
	}

	public String getLogName() {
		return logName;
	}

	public void setLogName(String logName) {
		this.logName = logName;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getOperationData() {
		return operationData;
	}

	public void setOperationData(String operationData) {
		this.operationData = operationData;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getSucceed() {
		return succeed;
	}

	public void setSucceed(String succeed) {
		this.succeed = succeed;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}