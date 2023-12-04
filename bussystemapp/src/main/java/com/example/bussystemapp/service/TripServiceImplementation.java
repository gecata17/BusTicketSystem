package com.example.bussystemapp.service;

import com.example.bussystemapp.model.Trip;
import com.example.bussystemapp.repository.TripRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TripServiceImplementation implements TripService{

    private TripRepository tripRepository;

    @Override
    public Trip createTrip(Trip trip) {
        return null;
    }

    @Override
    public Trip updateTrip(Trip trip) {
        return null;
    }

    @Override
    public List<Trip> getAllOrganisedTripsByTown(String title) {
        return null;
    }

    @Override
    public void deleteByDescription(String description) {
           tripRepository.deleteTripByDescription(description);
    }
}
