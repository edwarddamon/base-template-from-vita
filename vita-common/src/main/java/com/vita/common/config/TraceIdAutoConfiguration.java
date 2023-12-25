package com.vita.common.config;

import com.vita.common.interceptor.TraceIdFeignInterceptor;
import com.vita.common.interceptor.TraceIdHandlerInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Edward
 * @date 2023-12-22 18:30
 */
@Import(value = TraceIdFeignInterceptor.class)
@ConditionalOnClass(value = DispatcherServlet.class)
public class TraceIdAutoConfiguration implements WebMvcConfigurer {

    /**
     * 所有路径
     */
    private static final String ALL_PATH = "/**";

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new TraceIdHandlerInterceptor())
                .addPathPatterns(ALL_PATH);
    }
}
