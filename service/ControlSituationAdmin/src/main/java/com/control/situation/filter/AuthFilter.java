package com.control.situation.filter;

import com.control.situation.api.RedisApi;
import com.control.situation.api.impl.RedisApiImpl;
import com.control.situation.config.Env;
import com.control.situation.config.SysContants;
import com.control.situation.utils.ValidateUtils;
import com.control.situation.utils.returns.ClientResult;
import com.control.situation.utils.conversion.JsonUtil;
import com.control.situation.utils.returns.RetCode;
import com.control.situation.utils.http.CookieUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
public class AuthFilter implements Filter {

	private RedisApi redisApi;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		ServletContext servletContext = filterConfig.getServletContext();
		ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		redisApi = (RedisApiImpl) ctx.getBean("redisApiImpl");
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		Env env = new Env();
		ClientResult c = new ClientResult();
		env.setCr(c);

		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		// 解决跨域问题
		response.setHeader("Access-Control-Allow-Origin", "*");
		// 获取客户端IP
		env.clientIP = getIpAddr(request);
		// 请求路径
		String uri = request.getRequestURI();

		// 这样可以在 HttpApi 层获取
		request.setAttribute("env", env);

		String token = CookieUtils.getCookieValue(request, "token");
		if (ValidateUtils.notEmpty(token)) {
			String userId = redisApi.get(token);
			if (ValidateUtils.isEmpty(userId)) {
				c.setCode(RetCode.ERR_TOKEN_EXPIRE);
				JsonUtil.sendJsonResponse(response, c);
				return;
			}
			// token 刷新有效时间
			redisApi.expire(token, SysContants.COOKIE_EXPIRE);
			env.userId = userId;
			env.token = token;
		}

		// 不需要验证权限的接口
		if (uri.equals("/api/auth/checkLogin") || uri.equals("/api/auth/login")) {
			filterChain.doFilter(servletRequest, servletResponse);
			return;
		}

		if (ValidateUtils.isEmpty(env.userId)) {
			c.setCode(RetCode.ERR_USER_NOT_LOGIN);
			JsonUtil.sendJsonResponse(response, c);
			return;
		}

		filterChain.doFilter(servletRequest, servletResponse);
	}

	/**
	 * 获取用户真实IP地址，不使用request.getRemoteAddr()的原因是有可能用户使用了代理软件方式避免真实IP地址,
	 * 可是，如果通过了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP值
	 *
	 * @return ip
	 */
	private String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		System.out.println("x-forwarded-for ip: " + ip);
		if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
			// 多次反向代理后会有多个ip值，第一个ip才是真实ip
			if (ip.contains(",")) {
				ip = ip.split(",")[0];
			}
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
			System.out.println("Proxy-Client-IP ip: " + ip);
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
			System.out.println("WL-Proxy-Client-IP ip: " + ip);
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
			System.out.println("HTTP_CLIENT_IP ip: " + ip);
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
			System.out.println("HTTP_X_FORWARDED_FOR ip: " + ip);
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("X-Real-IP");
			System.out.println("X-Real-IP ip: " + ip);
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
			System.out.println("getRemoteAddr ip: " + ip);
		}
		System.out.println("获取客户端ip: " + ip);
		return ip;
	}

	@Override
	public void destroy() {
	}
}
