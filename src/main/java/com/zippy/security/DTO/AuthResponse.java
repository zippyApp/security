package com.zippy.security.DTO;

import lombok.*;
import lombok.experimental.Accessors;

@Getter
@Setter
@Builder
@Accessors(chain = true, fluent = false)
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {
    String token;
}
