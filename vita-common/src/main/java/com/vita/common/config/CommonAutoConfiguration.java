package com.vita.common.config;

import com.vita.common.exception.handler.GlobalExceptionHandler;
import org.springframework.context.annotation.Bean;

/**
 * @since 2023/05/30 11:16
 */
public class CommonAutoConfiguration {

    @Bean
    public GlobalExceptionHandler globalExceptionHandler() {
        return new GlobalExceptionHandler();
    }

}
