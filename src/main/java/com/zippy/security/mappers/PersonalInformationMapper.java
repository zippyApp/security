package com.zippy.security.mappers;

import com.zippy.security.DTO.PersonalInformationDTO;
import com.zippy.security.DTO.RegisterRequest;
import com.zippy.security.model.PersonalInformation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ReferenceMapper.class, DocumentMapper.class})
public interface PersonalInformationMapper {
    PersonalInformation PersonalInformationDTOToPersonalInformation(PersonalInformationDTO personalInformationDTO);
    PersonalInformationDTO PersonalInformationToPersonalInformationDTO(PersonalInformation personalInformation);
}
