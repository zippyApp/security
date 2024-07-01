package com.zippy.security.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class RegisterRequest implements java.io.Serializable {
    @NotNull
    private CredentialDTO credential;
    @NotNull
    private PersonalInformationDTO personalInformation;
}