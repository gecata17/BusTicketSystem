package com.example.bussystemapp.controller;

import com.example.bussystemapp.dtos.SignUpRequest;
import com.example.bussystemapp.model.User;
import com.example.bussystemapp.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/private/api/auth")
public class AuthController {

    private UserService userService;

    private AuthenticationManager authManager;

    //token to be added

    @PostMapping("/signup")
    public ResponseEntity<User> signup(@RequestBody SignUpRequest signUpRequest){

        User newUser = new User(signUpRequest.getUsername(),signUpRequest.getEmail(), signUpRequest.getPassword());

        try{
            userService.createUser(newUser);
        }catch (IllegalArgumentException ex){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().body(newUser);
    }
}
