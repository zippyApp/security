package com.zippy.security.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Register implements java.io.Serializable{
    private Credential credential;
    private PersonalInformation personalInformation;
}
