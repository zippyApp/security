package com.zippy.security.DTO;

import com.zippy.security.model.DocumentType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DocumentDTO {
    private Long id;
    @NotEmpty
    private String number;
    private String frontImage;
    private String backImage;
    @NotNull
    private DocumentTypeDTO type;


}