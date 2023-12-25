package com.vita.gateway.filter;

import com.vita.common.domain.common.CommonConstant;
import com.vita.common.utils.TraceIdUtil;
import org.slf4j.MDC;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author Edward
 * @date 2023-12-25 14:20
 */
@Component
public class TraceFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // traceId 生成
        String traceId = TraceIdUtil.generateTraceId();
        MDC.put(CommonConstant.TRACE_ID, traceId);

        final ServerHttpRequest finalRequest = exchange.getRequest()
                .mutate()
                .header(CommonConstant.TRACE_ID, traceId)
                .build();
        return chain.filter(exchange.mutate().request(finalRequest).build());
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }
}
