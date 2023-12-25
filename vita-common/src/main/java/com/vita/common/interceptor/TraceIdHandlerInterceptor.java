package com.vita.common.interceptor;

import com.vita.common.domain.common.CommonConstant;
import com.vita.common.utils.TraceIdUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * traceId 拦截器
 *
 * @author Edward
 * @date 2023-12-22 18:19
 */
public class TraceIdHandlerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String traceId = request.getHeader(CommonConstant.TRACE_ID);
        MDC.put(CommonConstant.TRACE_ID, StringUtils.isNotBlank(traceId) ? traceId : TraceIdUtil.generateTraceId());
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        MDC.remove(CommonConstant.TRACE_ID);
    }
}
