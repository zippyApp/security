package com.uis.entornos.proyectologincrud.Auth;

import com.uis.entornos.proyectologincrud.JWT.JWTService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    private final JWTService jwtService;

    private final UserDetailsService userDetailsService;


    @PostMapping(value = "login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request){
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping(value = "register")
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
