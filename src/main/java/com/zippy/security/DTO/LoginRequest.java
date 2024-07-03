package com.zippy.security.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.Accessors;


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
