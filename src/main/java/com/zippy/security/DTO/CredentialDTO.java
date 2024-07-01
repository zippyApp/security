package com.zippy.security.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class CredentialDTO {
    private Long id;
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    private Long personalInformationId;
    @NotNull
    private Long roleId;
}
