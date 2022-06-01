package com.alphabank.restapi.configurations;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "apiKeys")
@Configuration("apiKeysConfig")
public record ApiKeys(
        String gifApiKey,
        String currencyApiKey
) {
}
