package com.zippy.security.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DocumentTypeDTO {
    @NotNull
    private Integer id;
    private String name;
}
