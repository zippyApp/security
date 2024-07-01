package com.zippy.security.model;


import lombok.*;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonalInformation implements Serializable {
    private Long id;
    private String firstNames;
    private String lastNames;
    private String email;
    private String occupation;
    private String phone;
    private LocalDate birthDate;
    private Document document;
    private Reference reference;
}
