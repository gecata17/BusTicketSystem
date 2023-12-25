package com.example.bussystemapp.service;


import com.example.bussystemapp.dtos.TripDto;
import com.example.bussystemapp.model.Trip;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TripService {


    Trip createTrip(Trip trip);

    Trip updateTrip(Trip trip, String description);

    Trip findByDescription(String description);

    TripDto entityToDto(Trip trip);

    Trip dtoToEntity(TripDto tripDto);

    List<Trip> getAllOrganisedTripsByTown(String title);
    void deleteByDescription(String description);

}
