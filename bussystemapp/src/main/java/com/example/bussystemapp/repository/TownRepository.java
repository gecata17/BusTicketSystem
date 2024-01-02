package com.example.bussystemapp.repository;

import com.example.bussystemapp.model.Town;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TownRepository extends JpaRepository<Town,String> {

   Town findByTitle(String title);
    void deleteTownByTitle(String title);
}
