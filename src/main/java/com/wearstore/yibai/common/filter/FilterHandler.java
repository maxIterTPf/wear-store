package com.wearstore.yibai.common.filter;

import com.wearstore.yibai.common.properties.WearStoreFilterProperties;
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
 * 参考链接 @see https://www.jianshu.com/p/2dbb585ffb1c
 *
 * @author PF.Tian
 * @since 2021/08/18
 */
@Slf4j
public class FilterHandler implements Filter {

    private static String[] excludePaths;

    private boolean isEnabled;

    public FilterHandler(WearStoreFilterProperties.FilterConfig filterConfig) {
        isEnabled = filterConfig.isEnabled();
        excludePaths = filterConfig.getExcludePaths();
        log.debug("isEnabled:" + isEnabled);
        log.debug("excludePaths:" + Arrays.toString(excludePaths));
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

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

    @Override
    public void destroy() {
    }

}
