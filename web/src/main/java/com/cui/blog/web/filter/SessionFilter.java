package com.cui.blog.web.filter;

import org.springframework.http.HttpStatus;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Session过滤器：用户登陆判断，暂时就做个单机版的吧，等加机器了可以用spring-session来管理分布式session
 * <p>
 * Created by cuishixiang on 2017-09-14.
 */
public class SessionFilter extends OncePerRequestFilter {

    // 不过滤的uri
    private String[] notFilter = new String[]{"/login"};

    /**
     * 过滤处理
     *
     * @param request     请求
     * @param response    响应
     * @param filterChain 过滤链
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 请求的uri
        String uri = request.getRequestURI();
        // 是否过滤
        boolean doFilter = true;
        for (String s : notFilter) {
            if (uri.contains(s)) {
                // 如果uri中包含不过滤的uri，则不进行过滤
                doFilter = false;
                break;
            }
        }

        // 执行过滤
        if (doFilter) {
            // 从session中获取登录者实体
            Object user = request.getSession().getAttribute("user");
            if (user == null) {
                boolean isAjaxRequest = isAjaxRequest(request);
                if (isAjaxRequest) {
                    response.sendError(HttpStatus.UNAUTHORIZED.value(), "您已经太长时间没有操作,请刷新页面");
                    return;
                }
                response.sendRedirect("/blog/admin/account/login");
            } else {
                // 如果session中存在登录者实体，则继续
                filterChain.doFilter(request, response);
            }
        } else {
            // 如果不执行过滤，则继续
            filterChain.doFilter(request, response);
        }
    }

    /**
     * 判断是否为Ajax请求
     *
     * @param request 请求
     * @return 是true, 否false
     */
    private boolean isAjaxRequest(HttpServletRequest request) {
        String header = request.getHeader("X-Requested-With");
        return (header != null && "XMLHttpRequest".equals(header));
    }
}


