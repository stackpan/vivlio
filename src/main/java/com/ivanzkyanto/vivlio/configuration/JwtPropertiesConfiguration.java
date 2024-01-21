package com.ivanzkyanto.vivlio.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "jwt")
public record JwtPropertiesConfiguration(long expiration) {
}
