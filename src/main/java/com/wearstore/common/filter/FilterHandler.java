package com.wearstore.common.filter;

import com.wearstore.common.properties.FilterProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;

/**
 * 定义过滤器
 * Filter是依赖于Servlet的，需要有Servlet的依赖。
 * <p>
 * 过滤器只能在容器初始化时被调用一次。
 * Filter可以拦截所有请求。包括静态资源[css，js...]
 * <p>
 * 参考链接 @see https://www.jianshu.com/p/2dbb585ffb1c
 *
 * @author PF.Tian
 * @since 2021/08/18
 */
@Slf4j
public class FilterHandler implements Filter {

    private static String[] excludePaths;

    private boolean isEnabled;

    public FilterHandler(FilterProperties.FilterConfig filterConfig) {
        isEnabled = filterConfig.isEnabled();
        excludePaths = filterConfig.getExcludePaths();
        log.debug("isEnabled:" + isEnabled);
        log.debug("excludePaths:" + Arrays.toString(excludePaths));
    }

    /**
     * init() 在容器初始化时执行，只执行一次。
     *
     * @param filterConfig
     * @throws ServletException
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    /**
     * doFilter() 目标请求之前拦截执行，拦截之后需要放行才开始执行目标方法。
     * filterChain.doFilter(servletRequest,servletResponse);
     *
     * @param request
     * @param response
     * @param chain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (!isEnabled) {
            chain.doFilter(request, response);
            return;
        }
        HttpServletRequest req = (HttpServletRequest) request;
        String path = req.getServletPath();
        String url = req.getRequestURL().toString();
        PathMatcher pathMatcher = new AntPathMatcher();
        boolean isOut = true;
        if (ArrayUtils.isNotEmpty(excludePaths)) {
            for (String pattern : excludePaths) {
                if (pathMatcher.match(pattern, path)) {
                    isOut = false;
                    break;
                }
            }
        }
        if (isOut) {
            log.debug(url);
        }
        chain.doFilter(req, response);
    }

    /**
     * destroy() 在容器销毁时执行，只执行一次。
     */
    @Override
    public void destroy() {
    }

}
