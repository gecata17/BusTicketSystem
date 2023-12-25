package com.example.bussystemapp.controller;

import com.example.bussystemapp.config.UserDetailsImplementation;
import com.example.bussystemapp.dtos.LoginRequest;
import com.example.bussystemapp.dtos.LoginResponse;
import com.example.bussystemapp.dtos.SignUpRequest;
import com.example.bussystemapp.model.User;
import com.example.bussystemapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/private/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authManager;

    //token to be added

    @PostMapping("/signup")
    public ResponseEntity<User> signup(@RequestBody SignUpRequest signUpRequest) {
        User newUser = new User(signUpRequest.getUsername() ,signUpRequest.getEmail(),signUpRequest.getPassword());
        try{
            userService.createUser(newUser);
        }catch (IllegalArgumentException ex){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().body(newUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest){
        Authentication auth = authManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(auth);

        //token object
        UserDetailsImplementation userDetails= (UserDetailsImplementation) auth.getPrincipal();



        return ResponseEntity.ok(new LoginResponse(userDetails.getPassword(),userDetails.getUsername(), userDetails.getEmail()));
    }
}
