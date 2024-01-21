package com.ivanzkyanto.vivlio.controller.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    @NotEmpty(message = "{required.field}")
    @Size(min = 1, max = 30, message = "{invalid.field}")
    private String name;

    @NotEmpty(message = "{required.field}")
    @Size(min = 1, max = 50, message = "{invalid.field}")
    @Pattern(regexp = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?")
    private String email;

    @NotEmpty(message = "{required.field}")
    @Size(min = 1, max = 40, message = "{invalid.field}")
    private String password;

    @NotEmpty(message = "{required.field}")
    @Pattern(regexp = "^(USER|ADMIN)$")
    private String role;

}
