package com.zippy.security.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import lombok.experimental.Accessors;


@Getter
@Setter
@Builder
@Accessors(chain = true, fluent = false)
public class LoginRequest {
    @NotBlank
    String username;
    @NotBlank
    String password;
}
