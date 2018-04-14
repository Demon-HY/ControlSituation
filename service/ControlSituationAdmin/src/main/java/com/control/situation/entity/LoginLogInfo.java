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
@Table(name = "login_log")
public class LoginLogInfo implements Serializable {

	private static final long serialVersionUID = -1L;

	// 主键
	@Id
	@Column(name = "id")
	private Long id;

	// 日志名称
	@Column(name = "log_name")
	private String logName;

	// 管理员id
	@Column(name = "user_id")
	private Integer userId;

	// 创建时间
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "create_time")
	private Date createTime;

	// 是否执行成功
	@Column(name = "succeed")
	private Integer succeed;

	// 具体消息
	@Column(name = "message")
	private String message;

	// 登录ip
	@Column(name = "ip")
	private String ip;


	public LoginLogInfo(){}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getSucceed() {
		return succeed;
	}

	public void setSucceed(Integer succeed) {
		this.succeed = succeed;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

}