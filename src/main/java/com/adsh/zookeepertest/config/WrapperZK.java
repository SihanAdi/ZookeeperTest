package com.adsh.zookeepertest.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "curator")
public class WrapperZK {

    private int retryCount;
    private int elapsedTimeMs;
    private String connextString;
    private int sessionTimeoutMs;
    private int connectionTimeoutMs;
}
