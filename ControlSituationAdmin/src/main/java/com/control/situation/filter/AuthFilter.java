package com.control.situation.filter;

import com.control.situation.config.Env;
import com.control.situation.utils.JsonResult;
import org.springframework.stereotype.Service;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

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
		JsonResult jsonResult = new JsonResult();

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
		String businessNo = request.getParameter("businessNo");
		String userId = request.getParameter("userId");
		String orderNo = request.getParameter("orderNo");
		String callback = request.getParameter("callback");
		String xubeiUserId = request.getParameter("xubeiUserId");
		try {
			// 商户信息
			if (StringUtils.isEmpty(businessNo)) {
				c.setMessage("商户号不能为空");
				JsonUtil.sendJsonResponse(response,c,callback);
				return;
			}
			TChannelRoute channelRoute = channelRouteService.findChannelByMerchant(businessNo);
			if (StringUtils.isEmpty(channelRoute)) {
				c.setMessage("该商户号查询不到");
				JsonUtil.sendJsonResponse(response,c,callback);
				return;
			}
			if (channelRoute.getUserFlag() == 0) { // 0:无用户，1:有用户
				env.setUserId(channelRoute.getUserId());
			} else {
				env.setUserId(redisService.get(userId));
			}
			// 大多数接口都需要userId和businessNo，所以把它放到一个上下文变量中
			env.setChannelRoute(channelRoute);
			request.setAttribute("env", env);

			// 不需要验证 session
			if (uri.equals("/") || uri.contains("/third") || uri.contains("orderPaySuccess") || uri.contains("/third")
					|| uri.contains("orderPay/user/findOrderPayParams") || uri.contains("/hello")
					|| uri.contains("rechargeShowCallback")) {
				filterChain.doFilter(servletRequest, servletResponse);
				return;
			}

			// 用户Id为空，拦截请求
			if (env.getUserId() == null || env.getUserId().equals("null")) {
				if (StringUtils.notEmpty(xubeiUserId)) {
					env.setUserId(xubeiUserId);
				} else {
					c.setMessage("请登录");
					JsonUtil.sendJsonResponse(response,c,callback);
					return;
				}
			}

			if (StringUtils.notEmpty(orderNo)) {
				Map<String, Object> map = orderChannelService.findOrderChannelMap(orderNo, env.getChannelRoute().getOrderTable());
				if (StringUtils.isEmpty(map)) {
					c.setMessage("该订单查询不到");
					JsonUtil.sendJsonResponse(response,c,callback);
					return;
				}
				map.clear();
			}

			filterChain.doFilter(servletRequest, servletResponse);
		} catch (Exception e) {
			e.printStackTrace();
			c.setMessage("网络错误");
			try {
				JsonUtil.sendJsonResponse(response,c,callback);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

	}
}
