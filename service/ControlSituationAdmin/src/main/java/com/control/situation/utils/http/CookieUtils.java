package com.control.situation.utils.http;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * cookie
 */
public class CookieUtils {

	/**
	 * 获取 Cookie 值
	 */
	public static String getCookieValue(HttpServletRequest request, String cookieName) {
		Cookie[] cookies = request.getCookies();//这样便可以获取一个cookie数组
		if (null == cookies) {
			return null;
		}
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals(cookieName)) {
				return cookie.getValue();
			}
		}
		return null;
	}

	/**
	 * 获取 Cookie
	 *
	 * @param request
	 * @param cookieName
	 * @return Cookie
	 */
	public static Cookie getCookie(HttpServletRequest request, String cookieName) {
		try {
			Cookie[] cookies = request.getCookies();//这样便可以获取一个cookie数组
			if (null == cookies) {
				return null;
			}
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(cookieName)) {
					return cookie;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void addCookie(HttpServletResponse resp, String name, String value, int maxAge) {
		addCookie(resp, name, value, maxAge, null);
	}

	/**
	 * 设置cookie
	 * @param resp 响应
	 * @param name  cookie名字
	 * @param value cookie值
	 * @param maxAge cookie生命周期  以秒为单位
	 * @param cookieDomain cookie 作用域
	 */
	public static void addCookie(HttpServletResponse resp, String name, String value, int maxAge, String cookieDomain){
		try {
			Cookie cookie = new Cookie(name,value);
			cookie.setPath("/");
			if (cookieDomain != null) cookie.setDomain(cookieDomain);
			if(maxAge>0)  cookie.setMaxAge(maxAge);
			resp.addCookie(cookie);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 删除 cookie
	 *
	 * @param name cookie名
	 */
	public static void removeCookie(HttpServletRequest request, HttpServletResponse response, String name) {
		Cookie[] cookies = request.getCookies();
		if (null==cookies) {
			System.out.println("没有cookie==============");
		} else {
			for(Cookie cookie : cookies){
				if(cookie.getName().equals(name)){
					cookie.setValue(null);
					cookie.setMaxAge(0);// 立即销毁cookie
					cookie.setPath("/");
					response.addCookie(cookie);
					break;
				}
			}
		}
	}

	/**
	 * 删除 cookie
	 *
	 * @param response
	 * @param name cookie名
	 */
	public static void removeCookie(HttpServletResponse response, String name, String cookieDomain) {
		try {
			Cookie cookie = new Cookie(name, null);
			cookie.setPath("/");
			cookie.setDomain(cookieDomain);
			cookie.setMaxAge(0);
			response.addCookie(cookie);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 删除 cookie
	 *
	 * @param response
	 * @param cookie cookie
	 */
	public static void removeCookie(HttpServletResponse response, Cookie cookie) {
		cookie.setValue(null);
		cookie.setMaxAge(0);// 立即销毁cookie
		cookie.setPath("/");
		response.addCookie(cookie);
	}
}
