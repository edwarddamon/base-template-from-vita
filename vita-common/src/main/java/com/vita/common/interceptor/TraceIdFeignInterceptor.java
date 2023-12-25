package com.vita.common.interceptor;

import com.vita.common.domain.common.CommonConstant;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.slf4j.MDC;

/**
 * traceId feign 拦截器
 *
 * @author Edward
 * @date 2023-06-17 21:47
 */
public class TraceIdFeignInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        requestTemplate.header(CommonConstant.TRACE_ID, MDC.get(CommonConstant.TRACE_ID));
    }
}
