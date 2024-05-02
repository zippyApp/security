package com.zippy.security.controller;

import com.zippy.security.DTO.AuthResponse;
import com.zippy.security.service.AuthService;
import com.zippy.security.DTO.LoginRequest;
import com.zippy.security.DTO.RegisterRequest;
import com.zippy.security.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    private final JwtService jwtService;

    private final UserDetailsService userDetailsService;


    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request){
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/register")
        public  ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request){
            return ResponseEntity.ok(authService.register(request));
        }
    @GetMapping("/validate")
    public String  validateToken(@RequestParam String token){
        String username = jwtService.getUsernameFromToken(token);
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        if(!jwtService.isTokenValid(token,userDetails)){
            return "No es valido";
        }else{
            return "Es valido";
        }


    }


}
