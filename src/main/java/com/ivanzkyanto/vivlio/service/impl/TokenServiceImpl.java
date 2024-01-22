package com.ivanzkyanto.vivlio.service.impl;

import com.ivanzkyanto.vivlio.configuration.JwtPropertiesConfiguration;
import com.ivanzkyanto.vivlio.model.User;
import com.ivanzkyanto.vivlio.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {

    private final JwtEncoder jwtEncoder;

    private final JwtPropertiesConfiguration jwtProperties;

    @Override
    public String generate(Authentication authentication) {
        Instant now = Instant.now();
        long expiration = jwtProperties.getExpiration();

        User user = (User) authentication.getPrincipal();

        String scope = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .subject(user.getId())
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiration))
                .claim("scope", scope)
                .build();

        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }
}
