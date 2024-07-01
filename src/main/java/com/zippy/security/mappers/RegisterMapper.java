package com.zippy.security.mappers;

import com.zippy.security.DTO.RegisterRequest;
import com.zippy.security.model.Register;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring", uses = {CredentailMapper.class})
public interface RegisterMapper {
    Register RegisterRequestToRegister(RegisterRequest registerRequest);
    RegisterRequest RegisterToRegisterRequest(Register register);
}
