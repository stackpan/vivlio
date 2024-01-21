package com.ivanzkyanto.vivlio.controller;

import com.ivanzkyanto.vivlio.controller.dto.LoginRequest;
import com.ivanzkyanto.vivlio.controller.dto.LoginResponse;
import com.ivanzkyanto.vivlio.controller.dto.WebResponse;
import com.ivanzkyanto.vivlio.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authentication")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<WebResponse<LoginResponse>> login(@RequestBody @Valid LoginRequest request) {
        String token = authenticationService.login(request.getUsername(), request.getPassword());
        LoginResponse data = new LoginResponse(token);

        WebResponse<LoginResponse> response = new WebResponse<>("Login successfully", data);
        return ResponseEntity.ok(response);
    }
}
