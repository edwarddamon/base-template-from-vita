package com.vita.common.utils;

import cn.hutool.core.lang.UUID;

/**
 * 链路跟踪工具
 *
 * @author Edward
 * @date 2023-12-22 18:10
 */
public class TraceIdUtil {

    /**
     * 生成 traceId
     * @return traceId
     */
    public static String generateTraceId() {
        return UUID.randomUUID().toString(true).substring(0,12);
    }
}
