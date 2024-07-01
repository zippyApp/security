package com.zippy.security.mappers;

import com.zippy.security.DTO.DocumentDTO;
import com.zippy.security.model.Document;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = DocumentTypeMapper.class)
public interface DocumentMapper {
    @Mapping(target = "id", ignore = true)
    Document DocumentDTOToDocument(DocumentDTO documentDTO);
    DocumentDTO DocumentToDocumentDTO(Document document);
}
