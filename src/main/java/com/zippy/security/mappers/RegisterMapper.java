package com.zippy.security.mappers;

import com.zippy.security.DTO.RegisterRequest;
import com.zippy.security.model.Register;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {PersonalInformationMapper.class, CredentailMapper.class})
public interface RegisterMapper {
    Register RegisterRequestToRegister(RegisterRequest registerRequest);
    RegisterRequest RegisterToRegisterRequest(Register register);
}
