package com.example.bussystemapp.service;


import com.example.bussystemapp.dtos.TripDto;
import com.example.bussystemapp.model.Trip;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TripService {


    Trip createTrip(Trip trip);

    Trip updateTrip(Trip trip, Long description);

    Trip findByDescription(Long description);

    TripDto entityToDto(Trip trip);

    Trip dtoToEntity(TripDto tripDto);

    List<Trip> findAllOrganisedTripsByTown(String title);
    void deleteByDescription(Long description);

}
