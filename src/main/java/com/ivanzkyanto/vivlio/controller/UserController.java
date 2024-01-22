package com.ivanzkyanto.vivlio.controller;

import com.ivanzkyanto.vivlio.controller.dto.UserRequest;
import com.ivanzkyanto.vivlio.controller.dto.UserResponse;
import com.ivanzkyanto.vivlio.controller.dto.WebResponse;
import com.ivanzkyanto.vivlio.model.User;
import com.ivanzkyanto.vivlio.service.UserService;
import com.ivanzkyanto.vivlio.util.UserMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final UserMapper userMapper;

    @GetMapping
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<WebResponse<List<UserResponse>>> getAll(JwtAuthenticationToken principal) {
        List<User> users = userService.getAll();

        System.out.println(principal);

        List<UserResponse> data = users.stream()
                .map(userMapper::toResponse)
                .toList();

        WebResponse<List<UserResponse>> response = new WebResponse<>("Success", data);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<WebResponse<UserResponse>> register(@RequestBody @Valid UserRequest request) {
        User model = userMapper.toModel(request);

        User registered = userService.register(model);

        UserResponse data = userMapper.toResponse(registered);
        WebResponse<UserResponse> response = new WebResponse<>("User created successfully.", data);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
