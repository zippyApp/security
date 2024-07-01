package com.zippy.security.model;

import lombok.*;


import java.io.Serializable;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Reference implements Serializable {
    private Long id;
    private String firstNames;
    private String lastNames;
    private String phone;
    private String email;
    private Document document;
}
