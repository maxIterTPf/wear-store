package com.wearstore.yibai.common.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author PF.Tian
 * @since 2021/08/18
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new InterceptorOperation())
                .addPathPatterns("/**")     // 拦截所有请求，包括静态资源文件
                .excludePathPatterns("/", "/web/**", "/index.html", "/images/**");//放行 web下的请求，登录页，静态资源

    }

}
