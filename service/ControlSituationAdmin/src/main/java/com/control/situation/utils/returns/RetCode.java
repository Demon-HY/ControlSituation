package com.control.situation.utils.returns;

/**
 * 返回给客户端的错误码
 * 200 - 成功
 * 100开头 - 系统级错误
 * 300开头 - 用户/登录
 * 310开头 - 部门
 * 320开头 - 菜单
 */
public class RetCode {

	public static final String OK = "200";

	/***************************************************  系统错误 - 100开头 ************************************************/
	public static final String ERR_SERVER_EXCEPTION = "100000";
	public static final String ERR_BAD_PARAMS = "1000001";
	public static final String ERR_FORBIDDEN = "1000002";
	public static final String ERR_INVALID_JSON = "1000003";
	public static final String ERR_NOT_FOUND = "1000004";
    public static final String ERR_OPERATION_NOT_SUPPORTED = "1000005";
	public static final String ERR_WRITE_REDIS_FAILED = "1000006";
    /************************************************** 系统错误-100开头 ***********************************************/

    /************************************************** 用户/认证-300开头 ***********************************************/
	public static final String ERR_USER_NOT_LOGIN = "3000001";
	public static final String ERR_TOKEN_EXPIRE = "3000002";
	public static final String ERR_ACOUNT_NOT_FOUND = "3000003";
	public static final String ERR_PASSWORD_INVALID = "3000004";
	public static final String ERR_ACCOUNT_LOCK = "3000005";
	public static final String ERR_ACCOUNT_DELETE = "3000006";
	public static final String ERR_CREATE_USER_FAILED = "3000007";
	public static final String ERR_UPDATE_USER_FAILED = "3000008";
	public static final String ERR_ACCOUNT_EXIST = "3000009";
    /************************************************** 用户/认证-300开头 ***********************************************/

    public static String getMsgByStat(String stat) {
    	switch (stat) {
		    case OK : return "操作成功";
		    case ERR_SERVER_EXCEPTION : return "系统繁忙，请稍后重试";
		    case ERR_BAD_PARAMS : return "参数错误";
		    case ERR_FORBIDDEN : return "无访问权限";
		    case ERR_INVALID_JSON : return "非法JSON串";
		    case ERR_NOT_FOUND : return "资源不存在";
		    case ERR_OPERATION_NOT_SUPPORTED : return "不支持该操作";
		    case ERR_WRITE_REDIS_FAILED : return "写入 Redis 失败";

		    case ERR_USER_NOT_LOGIN : return "请登录";
		    case ERR_TOKEN_EXPIRE : return "Token 已失效";
		    case ERR_ACOUNT_NOT_FOUND : return "账户不存在";
		    case ERR_PASSWORD_INVALID : return "密码无效";
		    case ERR_ACCOUNT_LOCK : return "账号已锁定";
		    case ERR_ACCOUNT_DELETE : return "账号已删除";
		    case ERR_CREATE_USER_FAILED : return "创建用户失败";
		    case ERR_UPDATE_USER_FAILED : return "更新用户失败";
		    case ERR_ACCOUNT_EXIST : return "该账号已被使用";
		    default: return "Stat is not found";
	    }
    }
}
