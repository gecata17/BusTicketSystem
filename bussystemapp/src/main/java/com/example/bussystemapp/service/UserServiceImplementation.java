package com.example.bussystemapp.service;

import com.example.bussystemapp.config.UserDetailsImplementation;
import com.example.bussystemapp.model.User;
import com.example.bussystemapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import jakarta.persistence.EntityExistsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImplementation implements UserService, UserDetailsService {

    private final UserRepository userRepository;


    //TO DO
    @Override
    public void createUser(User user){
       User newUser = new User();

    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findById(username).orElseThrow(EntityExistsException::new);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findById(username).orElseThrow(EntityExistsException::new);

        return new UserDetailsImplementation(user);
    }
}
