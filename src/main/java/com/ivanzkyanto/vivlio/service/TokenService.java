package com.ivanzkyanto.vivlio.service;

import org.springframework.security.core.Authentication;

public interface TokenService {

    String generate(Authentication authentication);

}
