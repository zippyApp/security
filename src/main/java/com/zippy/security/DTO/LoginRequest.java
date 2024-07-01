package com.zippy.security.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.experimental.Accessors;
import lombok.NoArgsConstructor;

@Getter
@Setter
@Builder
@Accessors(chain = true, fluent = false)
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
    @NotBlank
    String username;
    @NotBlank
    String password;
}
