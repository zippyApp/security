package com.zippy.security.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Builder
@Accessors(chain = true, fluent = false)
public class AuthResponse {
    String token;
}
