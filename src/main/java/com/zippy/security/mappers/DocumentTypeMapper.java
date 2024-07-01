package com.zippy.security.mappers;

import org.mapstruct.Mapper;
import com.zippy.security.DTO.DocumentTypeDTO;
import com.zippy.security.model.DocumentType;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DocumentTypeMapper {
    DocumentTypeDTO DocumentTypeToDocumentTypeDTO(DocumentType documentType);
    DocumentType DocumentTypeDTOToDocumentType(DocumentTypeDTO documentTypeDTO);
}
