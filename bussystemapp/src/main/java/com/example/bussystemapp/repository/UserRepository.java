package com.example.bussystemapp.repository;

import com.example.bussystemapp.model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,String> {


    User findByUsername(String username);
    User findByEmail(String email);

    @Transactional
    void deleteByUsername(String username);
}
