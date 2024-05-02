package com.zippy.security.Config;

//import com.uis.entornos.proyectologincrud.JWT.JWTAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
//@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

//    private  final  JWTAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws  Exception
    {
        return  http
                .csrf(csrf->
                        csrf.disable())
                .authorizeHttpRequests(authRequest->
                        authRequest
                                .requestMatchers(
                                        "/auth/**",
                                        "/api/v1/demo",
                                        "/api/v1/user/new"
                                ).permitAll()
//                                .anyRequest().authenticated()
                )
//                .sessionManagement( sessionManager ->
//                        sessionManager
//                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                )
//                .authenticationProvider(authProvider)
//                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)

                .build();

    }
}
