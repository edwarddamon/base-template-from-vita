package com.vita.auth.config;

import com.alibaba.cloud.nacos.NacosDiscoveryProperties;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.annotation.Resource;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Configuration
public class NacosConfiguration {

    /**
     * app 版本号
     */
    private static final String APP_VERSION = "app.version";

    @Resource
    private Environment env;

    @Bean
    @ConditionalOnProperty(value = "spring.cloud.nacos.discovery.enabled", matchIfMissing = true)
    public NacosDiscoveryProperties nacosProperties() {
        NacosDiscoveryProperties nacosDiscoveryProperties = new NacosDiscoveryProperties();
        Map<String, String> metadata = nacosDiscoveryProperties.getMetadata();
        metadata.put("env", StringUtils.join(env.getActiveProfiles()));
        metadata.put("version", env.getProperty(APP_VERSION));
        metadata.put("startup.time", OffsetDateTime.now().format(DateTimeFormatter.ISO_OFFSET_DATE_TIME));
        return nacosDiscoveryProperties;
    }
}
