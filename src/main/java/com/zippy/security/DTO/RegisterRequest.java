package com.zippy.security.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest implements java.io.Serializable {
    @NotNull
    private CredentialDTO credential;
    @NotNull
    private PersonalInformationDTO personalInformation;
}