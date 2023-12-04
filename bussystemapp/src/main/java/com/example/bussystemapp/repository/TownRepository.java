package com.example.bussystemapp.repository;

import com.example.bussystemapp.model.Town;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TownRepository extends JpaRepository<Town,String> {

    void deleteTownByTitle(String title);
}
