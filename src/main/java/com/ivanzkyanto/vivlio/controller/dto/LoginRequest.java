package com.ivanzkyanto.vivlio.controller.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {

    @NotEmpty(message = "{required.field}")
    @Size(min = 1, max = 30, message = "{invalid.field}")
    private String username;

    @NotEmpty(message = "{required.field}")
    @Size(min = 1, max = 50, message = "{invalid.field}")
    private String password;

}
