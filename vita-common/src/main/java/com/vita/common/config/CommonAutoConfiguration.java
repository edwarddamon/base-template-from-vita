package com.vita.common.config;

import com.vita.common.controller.HealthController;
import com.vita.common.exception.handler.GlobalExceptionHandler;
import org.springframework.context.annotation.Bean;

/**
 * @since 2023/05/30 11:16
 */
public class CommonAutoConfiguration {

    /**
     * 全局异常
     */
    @Bean
    public GlobalExceptionHandler globalExceptionHandler() {
        return new GlobalExceptionHandler();
    }

    /**
     * 健康检查
     */
    @Bean
    public HealthController healthController() {
        return new HealthController();
    }

}
