package com.ivanzkyanto.vivlio.service.impl;

import com.ivanzkyanto.vivlio.service.AuthenticationService;
import com.ivanzkyanto.vivlio.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationManager authenticationManager;

    private final TokenService tokenService;

    @Override
    public String login(String username, String password) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authenticated = authenticationManager.authenticate(authenticationToken);

        return tokenService.generate(authenticated);
    }
}
