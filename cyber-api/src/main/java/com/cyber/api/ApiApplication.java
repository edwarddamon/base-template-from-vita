package com.cyber.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @since 2023/06/01 17:35
 */
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = "com.cyber.api")
public class ApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }
}
