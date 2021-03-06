package com.wearstore.common.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拦截器是依赖于SpringMVC的，需要有mvc的依赖。
 * 拦截器只能拦截action请求。不包括静态资源[css，js...]。
 * 在拦截器的生命周期中，可以多次被调用。
 *
 * @author PF.Tian
 * @since 2021/08/18
 */
@Slf4j
@Service(value = "InterceptorOperation")
public class InterceptorOperation implements HandlerInterceptor {

    /**
     * preHandle是请求执行前执行的
     * 有返回值Boolean类型，true：表示放行
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("TestInterceptor preHandle....");
        return true;
    }

    /**
     * postHandler是请求结束执行的，但只有preHandle方法返回true的时候才会执行
     *
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        log.info("TestInterceptor postHandle....");
    }

    /**
     * afterCompletion是视图渲染完成后才执行，同样需要preHandle返回true，
     * 该方法通常用于清理资源等工作。
     *
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        log.info("TestInterceptor afterCompletion....");
    }

}
