package com.example.bussystemapp.controller;

import com.example.bussystemapp.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/private/api/auth")
public class AuthController {

    private UserService userService;

}
