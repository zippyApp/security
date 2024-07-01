package com.zippy.security.controller;

import com.zippy.security.DTO.AuthResponse;
import com.zippy.security.DTO.LoginRequest;
import com.zippy.security.DTO.PersonalInformationDTO;
import com.zippy.security.DTO.RegisterRequest;
import com.zippy.security.clients.interfaces.PersonalInformationClient;
import com.zippy.security.mappers.PersonalInformationMapper;
import com.zippy.security.mappers.RegisterMapper;
import com.zippy.security.model.PersonalInformation;
import com.zippy.security.model.Register;
import com.zippy.security.service.impl.JwtService;
import com.zippy.security.service.interfaces.IAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private IAuthService authService;
    private JwtService jwtService;
    private RegisterMapper registerMapper;
    private PersonalInformationClient personalInformationClient;
    private PersonalInformationMapper personalInformationMapper;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(AuthResponse.builder().token(authService.login(request)).build()
        );
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
//        Register register = registerMapper.RegisterRequestToRegister(request);
//        PersonalInformation personalInformation = personalInformationMapper
//                .PersonalInformationDTOToPersonalInformation(personalInformationClient.newPersonalInformation(register.getPersonalInformation()));
//        register.getCredential().setPersonalInformationId(personalInformation.getId());
//        return ResponseEntity.ok(authService.register(register.getCredential()));
        return Optional.of(request)
                .map(registerMapper::RegisterRequestToRegister)
                .map(register -> {
                    register.getCredential().setPersonalInformationId(personalInformationMapper
                            .PersonalInformationDTOToPersonalInformation(personalInformationClient.create(register.
                                    getPersonalInformation()
                            )).getId()
                    );
                    return register;
                })
                .map(register -> authService.register(register.getCredential()))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

    @GetMapping("/validate")
    public ResponseEntity validateToken(@RequestParam String token) {
        return jwtService.validateToken(token) ?
                ResponseEntity.ok("Valid") :
                ResponseEntity.badRequest().body("Invalid");
    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteAccount(@RequestParam String token) {
        return jwtService.validateToken(token) ?
                ResponseEntity.ok(authService.deleteAccount(token)) :
                ResponseEntity.badRequest().body("Invalid");
    }

    @Autowired
    public void setAuthService(IAuthService authService) {
        this.authService = authService;
    }

    @Autowired
    public void setJwtService(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Autowired
    public void setRegisterMapper(RegisterMapper registerMapper) {
        this.registerMapper = registerMapper;
    }

    @Autowired
    public void setPersonalInformationClient(PersonalInformationClient personalInformationClient) {
        this.personalInformationClient = personalInformationClient;
    }

    @Autowired
    public void setPersonalInformationMapper(PersonalInformationMapper personalInformationMapper) {
        this.personalInformationMapper = personalInformationMapper;
    }
}
