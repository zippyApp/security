package com.zippy.security.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Document implements Serializable {
    private Long id;
    private String number;
    private String frontImage;
    private String backImage;
    private DocumentType type;

}
