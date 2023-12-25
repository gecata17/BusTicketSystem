package com.example.bussystemapp.config;

import com.example.bussystemapp.service.UserServiceImplementation;
import com.example.bussystemapp.utils.JWTTokenGenerator;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.util.StringUtils;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    private final UserServiceImplementation userService;
    @Autowired
    private final AuthenticationProvider authenticationProvider;

    private final AuthHandlerJwt unauthorizedHandler;

    private final JWTTokenGenerator jwtTokenGenerator;

    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(12);
    }


    private String parseToken(HttpServletRequest request){
        String header  = request.getHeader("Authorization");
        if(StringUtils.hasText(header)){
            return header;
        }
        return null;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable).authenticationProvider(authenticationProvider).authorizeHttpRequests((authZ)-> authZ
                .anyRequest().permitAll());

        return http.build();
    }



}
