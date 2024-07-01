package com.zippy.security.DTO;


import com.zippy.security.model.Document;
import com.zippy.security.model.Reference;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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