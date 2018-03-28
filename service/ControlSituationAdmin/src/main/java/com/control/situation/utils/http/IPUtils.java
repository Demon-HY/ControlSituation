package com.control.situation.utils.http;

/**
 * Created by Administrator on 2018/1/19 0019.
 */
public class IPUtils {

	/**
	 * 一段匹配
	 * @param ip 被检查的ip
	 * @param expression 表达式
	 * @return 是否匹配
	 */
	public static boolean checkIp(String ip, String expression) {
		if (null != ip && null != expression) {
			return ip.matches(expression);
		}
		return false;
	}
	/**
	 * 多段匹配
	 * @param ip 被检查的ip
	 * @param expression 表达式
	 * @return
	 */
	public static boolean checkIpMultiSection(String ip, String expression) {
		if (null != ip && null != expression) {
			for (String exp : expression.split(",")) {
				if (checkIp(ip, exp))
					return true;
			}
		}
		return false;
	}

	/**
	 * 多段匹配并且检查白名单设置
	 * @param ip
	 * @param expression
	 * @param white
	 * @return
	 */
	public static boolean checkIpMultiSectionWithWhiteFlag(String ip, String expression, boolean white) {
		if (null != ip && null != expression) {
			if (checkIpMultiSection(ip, expression)) {
				if (white)
					return true;
			} else {
				if (!white)
					return true;
			}
		}
		return false;
	}
}
