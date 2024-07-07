package com.zippy.security.service.impl;

import com.zippy.security.DTO.AuthResponse;
import com.zippy.security.DTO.LoginRequest;
import com.zippy.security.DTO.PersonalInformationDTO;
import com.zippy.security.clients.interfaces.PersonalInformationClient;
import com.zippy.security.model.Credential;
import com.zippy.security.model.Register;
import com.zippy.security.repository.CredentialRepository;
import com.zippy.security.service.interfaces.IAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AuthServiceImpl implements IAuthService {

    private CredentialRepository credentialRepository;
    private JwtService jwtService;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    private PersonalInformationClient personalInformationClient;
;


    @Override
    public Optional<AuthResponse> login(LoginRequest request) {
        return Optional.of(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()))
                .map(authenticationManager::authenticate)
                .flatMap(auth -> credentialRepository.findByUsername(request.getUsername()))
                .map(jwtService::getToken)
                .map(this::buildAuthResponse);
    }

    @Transactional
    @Override
    public Optional<AuthResponse> register(Register register) {
        return Optional.of(register)
                .flatMap(this::setPersonalInformationId)
                .map(this::encodePassword)
                .map(this::saveCredential)
                .map(jwtService::getToken)
                .map(this::buildAuthResponse);
    }

    @Transactional
    @Override
    public String deleteAccount(String token) {
        String username = jwtService.getUsernameFromToken(token);
        Credential credential = credentialRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Credential not found"));
//        credentialRepository.delete(credential);


        // Find the associated personalInformationId before deleting the credential
        Long personalInformationId = credentialRepository.findPersonalInformationIdByCredentialId(credential.getId());

        // Delete the credential
        credentialRepository.deleteByIdAndCascadePersonalInformation(credential.getId());

        // Delete the associated personal information
        if (personalInformationId != null) {
            personalInformationClient.delete(personalInformationId);
//            personalInformationService.deletePersonalInformation(personalInformationId);
        }
        return "Account deleted";
    }

    @Override
    public Optional<PersonalInformationDTO> getPersonalInformation(String token) {
        return Optional.of(token)
                .flatMap(this::getPersonalInformationId)
                .flatMap(personalInformationClient::get);
    }

    @Override
    public String changePassword(String token, String newPassword) {
        return Optional.of(token)
                .map(jwtService::getUsernameFromToken)
                .flatMap(credentialRepository::findByUsername)
                .map(credential -> {
                    credential.setPassword(passwordEncoder.encode(newPassword));
                    return credentialRepository.save(credential);
                })
                .map(savedCredential -> "Password changed")
                .orElseThrow(() -> new RuntimeException("Credential not found"));
    }


    private AuthResponse buildAuthResponse(String token) {
        return AuthResponse.builder().token(token).build();
    }

    private Credential saveCredential(Register register) {
        return credentialRepository.save(register.getCredential());
    }

    private Optional<Register> setPersonalInformationId(Register register) {
        return personalInformationClient.create(register.getPersonalInformation())
                .map(personalInformationDTO -> {
                    register.getCredential().setPersonalInformationId(personalInformationDTO.getId());
                    return register;
                });
    }

    private Register encodePassword(Register register) {
        String encodedPassword = passwordEncoder.encode(register.getCredential().getPassword());
        register.getCredential().setPassword(encodedPassword);
        return register;
    }

    private Optional<Long> getPersonalInformationId(String token) {
        return Optional.of(token)
                .map(jwtService::getUsernameFromToken)
                .flatMap(credentialRepository::findByUsername)
                .map(Credential::getPersonalInformationId);
    }

    @Autowired
    public void setCredentialRepository(CredentialRepository credentialRepository) {
        this.credentialRepository = credentialRepository;
    }

    @Autowired
    public void setJwtService(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Autowired
    public void setPersonalInformationClient(PersonalInformationClient personalInformationClient) {
        this.personalInformationClient = personalInformationClient;
    }
}
