package com.uis.entornos.proyectologincrud.Auth;


import com.uis.entornos.proyectologincrud.JWT.JWTService;
import com.uis.entornos.proyectologincrud.User.Role;
import com.uis.entornos.proyectologincrud.User.User;
import com.uis.entornos.proyectologincrud.User.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private  final JWTService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    public AuthResponse login(LoginRequest request) {

        authenticationManager.authenticate( new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        UserDetails user = userRepository.findByUsername(request.getUsername()).orElseThrow();
        String token = jwtService.getToken(user);
        return AuthResponse.builder()
                .token(token)
                .build();
    }

    public AuthResponse register(RegisterRequest request) {
        User user = User.builder()
                .username(request.getUsername())
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .country(request.getCountry())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();

        userRepository.save(user);
        return AuthResponse.builder()
                .token(jwtService.getToken(user))
                .build();
    }
}
