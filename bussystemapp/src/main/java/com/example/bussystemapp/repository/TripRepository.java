package com.example.bussystemapp.repository;

import com.example.bussystemapp.model.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TripRepository extends JpaRepository<Trip,String> {

    void deleteTripByDescription(String description);
}
