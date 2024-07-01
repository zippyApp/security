package com.zippy.security.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class PersonalInformationDTO {
    private Long id;
    @NotEmpty
    private String firstNames;
    @NotEmpty
    private String lastNames;
    @NotEmpty
    @Email
    private String email;
    @NotEmpty
    private String occupation;
    @NotEmpty
    private String phone;
    @NotEmpty
    private String birthDate;
    @NotNull
    private DocumentDTO document;
    @NotNull
    private ReferenceDTO reference;
}