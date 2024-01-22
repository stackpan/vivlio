package com.ivanzkyanto.vivlio.configuration;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Objects;

@ConfigurationProperties(prefix = "jwt")
@Getter
public class JwtPropertiesConfiguration {

    private final long expiration;

    public JwtPropertiesConfiguration(Long expiration) {
        this.expiration = !Objects.isNull(expiration) ? expiration : 60 * 60L;
    }
}
