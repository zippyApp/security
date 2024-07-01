package com.zippy.security.DTO;

import com.zippy.security.model.Document;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ReferenceDTO {
    private Long id;

    @NotEmpty
    private String firstNames;

    @NotEmpty
    private String lastNames;

    @NotEmpty
    private String phone;

    @Email
    @NotEmpty
    private String email;

    @NotNull
    private DocumentDTO document;
}