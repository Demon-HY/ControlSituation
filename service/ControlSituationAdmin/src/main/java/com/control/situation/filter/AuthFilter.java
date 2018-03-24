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
		env.setClientResult(c);

		HttpServletRequest request= (HttpServletRequest) servletRequest;
		HttpServletResponse response=(HttpServletResponse) servletResponse;
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");

		// 解决跨域问题
		String originHeader = request.getHeader("Origin");
		response.setHeader("Access-Control-Allow-Origin", originHeader);
		response.setHeader("Access-Control-Allow-Methods", "POST, GET");
		response.setHeader("Access-Control-Allow-Headers", "x-requested-with,content-type");

		String uri = request.getRequestURI();

		try {
			request.setAttribute("env", env);

			String token = CookieUtils.getCookieValue(request, "token");
			if (ValidateUtils.notEmpty(token)) {
				String userId = redisApi.get(token);
				if (ValidateUtils.isEmpty(userId)) {
					c.setCode(RetCode.ERR_TOKEN_EXPIRE);
					JsonUtil.sendJsonResponse(response,c);
					return;
				}
				// token 刷新有效时间
				redisApi.expire(token, SysContants.COOKIE_EXPIRE);
				env.setUserId(userId);
				env.setToken(token);
			}

			// 不需要验证权限的接口
			if (uri.equals("/api/auth/checkLogin") || uri.equals("/api/auth/login")) {
				filterChain.doFilter(servletRequest, servletResponse);
				return;
			}

			if (ValidateUtils.isEmpty(env.getUserId())) {
				c.setCode(RetCode.ERR_USER_NOT_LOGIN);
				JsonUtil.sendJsonResponse(response,c);
				return;
			}

			filterChain.doFilter(servletRequest, servletResponse);
		} catch (Exception e) {
			e.printStackTrace();
			c.setCode(RetCode.ERR_SERVER_EXCEPTION);
			try {
				JsonUtil.sendJsonResponse(response,c);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

	}

	@Override
	public void destroy() {	}
}
