package com.example.bussystemapp.service;

import com.example.bussystemapp.config.UserDetailsImplementation;
import com.example.bussystemapp.model.User;
import com.example.bussystemapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import jakarta.persistence.EntityExistsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImplementation implements UserService, UserDetailsService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;



    @Override
    public void createUser(User user){

        if(userRepository.existsById(user.getUsername())){
            throw new IllegalArgumentException("Username already in use");
        }
        if(userRepository.findByEmail(user.getEmail()) != null){
            throw new IllegalArgumentException("Email already in use");
        }

       User newUser = new User();
       newUser.setUsername(user.getUsername());
       newUser.setEmail(user.getEmail());
       newUser.setPassword(passwordEncoder.encode(user.getPassword()));
       userRepository.save(newUser);

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
        System.out.println(user.toString());
        return new UserDetailsImplementation(user);
    }
}
