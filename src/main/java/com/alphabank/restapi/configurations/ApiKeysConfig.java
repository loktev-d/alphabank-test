package com.alphabank.restapi.configurations;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "api-keys")
@Configuration("apiKeysConfig")
@Data
public class ApiKeysConfig {
    private String gifApiKey;
    private String currencyApiKey;
}
