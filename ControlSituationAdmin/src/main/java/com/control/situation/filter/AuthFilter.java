package com.control.situation.filter;

import com.control.situation.config.Env;
import com.control.situation.utils.ClientResult;
import com.control.situation.utils.JsonUtil;
import com.demon.utils.stat.RetStat;
import org.springframework.stereotype.Service;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
public class AuthFilter implements Filter {
	
	@Override
	public void destroy() {	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
//		ServletContext servletContext = filterConfig.getServletContext();
//		ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(servletContext);
//		channelRouteService = (ChannelRouteService) ctx.getBean("channelRouteServiceImpl");
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		Env env = new Env();
		ClientResult c = new ClientResult();

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
		String token = request.getParameter("token");
		try {
			if (uri.contains("favicon")) {
				return;
			}

			filterChain.doFilter(servletRequest, servletResponse);
		} catch (Exception e) {
			e.printStackTrace();
			c.setMessage(RetStat.ERR_SERVER_EXCEPTION);
			try {
				JsonUtil.sendJsonResponse(response,c);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

	}
}
