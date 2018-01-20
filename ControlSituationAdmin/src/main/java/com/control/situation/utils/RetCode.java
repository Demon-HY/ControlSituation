package com.control.situation.utils;

/**
 * 返回给客户端的错误码
 * 200 - 成功
 * 10开头 - 系统级错误
 * 30开头 - 用户
 * 40开头 - 部门
 * 50开头 - 菜单
 */
public class RetCode {

	/**
	 * OK
	 */
	public static final String OK = "200";

	/**
	 * 错误码：服务端异常
	 */
	public static final String ERR_SERVER_EXCEPTION = "100";
	
	/**
     * 错误码：参数错误
     */
	public static final String ERR_BAD_PARAMS = "101";
	
	/**
     * 错误码：无访问权限
     */
	public static final String ERR_FORBIDDEN = "102";
	
	/**
     * 错误码：非法JSON串
     */
	public static final String ERR_INVALID_JSON = "103";
	
	/**
     * 错误码：资源不存在
     */
	public static final String ERR_NOT_FOUND = "104";
	
	/**
     * 错误码：用户没有登录
     */
	public static final String ERR_USER_NOT_LOGIN = "106";
	
	/**
     * 错误码：不支持该操作
     */
    public static final String ERR_OPERATION_NOT_SUPPORTED = "107";
    
}
