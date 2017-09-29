package com.cui.blog.web.filter;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 文章浏览量统计:统计规则，一个用户访问一次算一次，但是如果再不停刷新页面，不能计算，关闭后重新打开需要计算，长时间后再次刷新需要计算，更换浏览器需要统计，
 * <p>
 * Created by cuishixiang on 2017-09-29.
 */
public class ArticleViewCountFilter extends OncePerRequestFilter {

    /**
     * 文章id-访问数量的记录
     */
    private static Map<Integer, Integer> idAndCountMap = new ConcurrentHashMap<>();

    /**
     * 浏览量统计
     *
     * @param request     请求
     * @param response    响应
     * @param filterChain 过滤链
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer id = Integer.valueOf(request.getRequestURI().substring(request.getRequestURI().lastIndexOf("/") + 1));
        Set<Integer> viewedArticleIds = (Set<Integer>) session.getAttribute("viewedArticleIds");
        Object user = session.getAttribute("user");
        if (user == null) {
            if (viewedArticleIds == null) {
                viewedArticleIds = new HashSet<>();
                viewedArticleIds.add(id);
                session.setAttribute("viewedArticleIds", viewedArticleIds);
                Integer counts = idAndCountMap.getOrDefault(id, 0);
                idAndCountMap.put(id, ++counts);
            } else if (!viewedArticleIds.contains(id)) {
                viewedArticleIds.add(id);
                Integer counts = idAndCountMap.getOrDefault(id, 0);
                idAndCountMap.put(id, ++counts);
            }
        }
        filterChain.doFilter(request, response);
    }
}
