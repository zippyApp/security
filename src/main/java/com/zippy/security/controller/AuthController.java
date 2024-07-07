package com.zippy.security.controller;

import com.zippy.security.DTO.AuthResponse;
import com.zippy.security.DTO.LoginRequest;
import com.zippy.security.DTO.RegisterRequest;
import com.zippy.security.mappers.RegisterMapper;
import com.zippy.security.service.impl.JwtService;
import com.zippy.security.service.interfaces.IAuthService;
import feign.Response;
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

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        return authService.login(request)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        return Optional.ofNullable(registerMapper.RegisterRequestToRegister(request))
                .flatMap(authService::register)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

    @GetMapping("/validate")
    public ResponseEntity<?> validateToken(@RequestHeader String token) {
        return jwtService.validateToken(token) ?
                ResponseEntity.ok("Valid") :
                ResponseEntity.badRequest().body("Invalid");
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteAccount(@RequestHeader String token) {
        return jwtService.validateToken(token) ?
                ResponseEntity.ok(authService.deleteAccount(token)) :
                ResponseEntity.badRequest().body("Invalid");
    }

    @GetMapping("/user")
    public ResponseEntity<?> getPersonalInformation(@RequestHeader String token) {
        return jwtService.validateToken(token) ?
                ResponseEntity.ok(authService.getPersonalInformation(token)) :
                ResponseEntity.badRequest().body("Invalid");
    }

    @PutMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestHeader String token, @RequestBody String newPassword) {
        return jwtService.validateToken(token) ?
                ResponseEntity.ok(authService.changePassword(token, newPassword)) :
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
}
