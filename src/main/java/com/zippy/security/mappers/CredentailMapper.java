package com.zippy.security.mappers;

import com.zippy.security.DTO.CredentialDTO;
import com.zippy.security.model.Credential;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CredentailMapper {
    @Mapping(target = "id", ignore = true)
    Credential CredentialDTOToCredential(CredentialDTO credentialDTO);
    CredentialDTO CredentialToCredentialDTO(Credential credential);
}
