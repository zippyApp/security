package com.zippy.security.model;

import com.zippy.security.DTO.PersonalInformationDTO;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Register implements java.io.Serializable{
    private Credential credential;
    private PersonalInformationDTO personalInformation;
}
