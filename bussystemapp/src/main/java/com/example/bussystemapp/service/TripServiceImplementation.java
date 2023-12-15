package com.example.bussystemapp.service;

import com.example.bussystemapp.dtos.TripDto;
import com.example.bussystemapp.model.Trip;
import com.example.bussystemapp.repository.TripRepository;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TripServiceImplementation implements TripService{

    private TripRepository tripRepository;

    //TO DO
    @Override
    public Trip createTrip(Trip trip) {
        return null;
    }

    //TO DO
    @Override
    public Trip updateTrip(Trip trip) {
        return null;
    }

    @Override
    public Trip findByDescription(String description) {
        return tripRepository.findById(description).orElseThrow(EntityExistsException::new);
    }

    @Override
    public TripDto entityToDto(Trip trip) {
        return new TripDto(trip.getDescription(),trip.getSeats(),trip.getDateOfDeparture(),trip.getDateOfArrival());
    }

    @Override
    public Trip dtoToEntity(TripDto tripDto) {
        return new Trip(tripDto.getDescription(),tripDto.getSeats(),tripDto.getDateOfDeparture(),tripDto.getDateOfArrival());
    }

    //TO DO
    @Override
    public List<Trip> getAllOrganisedTripsByTown(String title) {
        return null;
    }


    @Override
    public void deleteByDescription(String description) {
           tripRepository.deleteTripByDescription(description);
    }
}
