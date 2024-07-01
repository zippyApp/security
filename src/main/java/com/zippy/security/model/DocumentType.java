package com.zippy.security.model;

import lombok.*;
import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DocumentType implements Serializable {
    private Integer id;
    private String name;
}
