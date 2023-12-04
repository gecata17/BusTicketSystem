package com.example.bussystemapp.service;


import com.example.bussystemapp.model.Town;
import com.example.bussystemapp.model.Trip;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TripService {

    //to create dtos
    Trip createTrip(Trip trip);

    Trip updateTrip(Trip trip);

    List<Trip> getAllOrganisedTripsByTown(String title);
    void deleteByDescription(String description);

}
