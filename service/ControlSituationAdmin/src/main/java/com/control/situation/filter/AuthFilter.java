package com.control.situation.filter;

import com.control.situation.api.RedisApi;
import com.control.situation.api.impl.RedisApiImpl;
import com.control.situation.config.Env;
import com.control.situation.config.SysContants;
import com.control.situation.utils.ValidateUtils;
import com.control.situation.utils.conversion.JsonUtil;
import com.control.situation.utils.http.CookieUtils;
import com.control.situation.utils.returns.ClientResult;
import com.control.situation.utils.returns.RetCode;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

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
        String[] origin = {"http://localhost:8080"};
        Set<String> allowedOrigins = new HashSet<>(Arrays.asList(origin));
        String originHeader = request.getHeader("Origin");
        if (allowedOrigins.contains(originHeader)) {
            response.setHeader("Access-Control-Allow-Origin", originHeader);
            response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
            response.setHeader("Access-Control-Max-Age", "3600");
            // 如果要把Cookie发到服务器，需要指定Access-Control-Allow-Credentials字段为true;
            response.setHeader("Access-Control-Allow-Credentials", "true");
            response.setHeader("XDomainRequestAllowed", "1");
            //表明服务器支持的所有头信息字段
            response.setHeader("Access-Control-Allow-Headers", "Origin, No-Cache, X-Requested-With, If-Modified-Since," +
                    "Pragma, Last-Modified, Cache-Control, Expires, Content-Type, X-E4M-With,userId,token");
        }
        // 请求路径
        String uri = request.getRequestURI();
        String token = CookieUtils.getCookieValue(request, "token");

        // 获取客户端IP
        env.clientIP = getIpAddr(request);
        env.token = token;

        // 这样可以在 HttpApi 层获取
        request.setAttribute("env", env);

        // 不需要验证权限的接口
        if (uri.equals("/api/auth/checkLogin.do") || uri.equals("/api/auth/login.do")) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        if (ValidateUtils.notEmpty(token)) {
            String userId = redisApi.get(token);
            // token失效
            if (ValidateUtils.isEmpty(userId)) {
                c.setCode(RetCode.ERR_TOKEN_EXPIRE);
                JsonUtil.sendJsonResponse(response, c);
                return;
            }
            // token 刷新有效时间
            redisApi.expire(token, SysContants.COOKIE_EXPIRE);
            env.userId = Long.valueOf(userId);
            env.token = token;
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
        String ipAddress;
        try {
            ipAddress = request.getHeader("x-forwarded-for");
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getRemoteAddr();
                if (ipAddress.equals("127.0.0.1")) {
                    // 根据网卡取本机配置的IP
                    InetAddress inet = null;
                    try {
                        inet = InetAddress.getLocalHost();
                    } catch (UnknownHostException e) {
                        e.printStackTrace();
                    }
                    assert inet != null;
                    ipAddress = inet.getHostAddress();
                }
            }
            // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
            if (ipAddress != null && ipAddress.length() > 15) { // "***.***.***.***".length()
                if (ipAddress.indexOf(",") > 0) {
                    ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
                }
            }
        } catch (Exception e) {
            ipAddress = "";
        }
        return ipAddress;
    }

    @Override
    public void destroy() {
    }
}
