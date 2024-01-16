package com.example.bussystemapp.service;

import com.example.bussystemapp.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public interface UserService
{
    void createUser(User user);

    User findByUsername(String username);


    User findByEmail(String email);

    List<User> getAllUsers();

    User updateUser(User updatedUser,String username);
    void deleteByUsername(String username);
}
