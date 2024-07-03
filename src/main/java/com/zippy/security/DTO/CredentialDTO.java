package com.zippy.security.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
