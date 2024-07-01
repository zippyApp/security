package com.zippy.security.mappers;

import com.zippy.security.DTO.ReferenceDTO;
import com.zippy.security.model.Reference;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = DocumentMapper.class)
public interface ReferenceMapper {
    @Mapping(target = "id", ignore = true)
    Reference ReferenceDTOToReference(Reference reference);
    ReferenceDTO ReferenceToReferenceDTO(ReferenceDTO referenceDTO);
}
