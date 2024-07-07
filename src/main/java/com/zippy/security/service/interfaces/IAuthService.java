package com.zippy.security.service.interfaces;

import com.zippy.security.DTO.AuthResponse;
import com.zippy.security.DTO.LoginRequest;
import com.zippy.security.DTO.PersonalInformationDTO;
import com.zippy.security.model.Register;

import java.util.Optional;

public interface IAuthService {
    Optional<AuthResponse> login(LoginRequest loginRequest);

    Optional<AuthResponse> register(Register register);

    String deleteAccount(String token);

    String changePassword(String token, String newPassword);

    Optional<PersonalInformationDTO> getPersonalInformation(String token);
}
