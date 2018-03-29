package com.control.situation.entity.vo;

import com.control.situation.config.SysContants;
import com.control.situation.utils.datetime.DateUtils;
import org.apache.commons.codec.binary.Base64;

import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

/**
 * Token 信息
 *
 * Created by Administrator on 2018/3/29 0029.
 */
public class TokenInfo implements Serializable {

	/**
	 * 用户登录凭据
	 */
	public String token;

	/**
	 * 用户ID
	 */
	public Long uid;

	/**
	 * token 创建时间
	 */
	public Date createTime;

	/**
	 * token 过期时间，有效时间读取 SysContants.COOKIE_EXPIRE
	 */
	public Date expireTime;

	/**
	 * 客户端 IP，可以用来做 IP 白名单
	 */
	public String clienIP;

//	/**
//	 * 客户端设备
//	 */
//	public String device;

	public TokenInfo(Long uid, String clienIP) {
		this.uid = uid;
		this.token = String.format("%s@%d", makeToken(), uid);
		this.createTime = new Date();
		this.expireTime = DateUtils.getAfterDate(createTime, Calendar.SECOND, SysContants.COOKIE_EXPIRE);
		this.clienIP = clienIP;
	}

	/**
	 * 生成token字符串
	 * @return
	 */
	private static String makeToken() {
		long uuid = UUID.randomUUID().getMostSignificantBits();
		byte[] uuidBytes = ByteBuffer.allocate(8).putLong(uuid).array();
		return Base64.encodeBase64URLSafeString(uuidBytes);
	}
}
