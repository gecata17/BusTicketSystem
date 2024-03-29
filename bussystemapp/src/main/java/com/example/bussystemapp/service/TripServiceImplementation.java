package com.example.bussystemapp.service;

import com.example.bussystemapp.dtos.TripDto;
import com.example.bussystemapp.model.Town;
import com.example.bussystemapp.model.Trip;
import com.example.bussystemapp.repository.TownRepository;
import com.example.bussystemapp.repository.TripRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class TripServiceImplementation implements TripService {

    private final TripRepository tripRepository;
    private final TownRepository townRepository;


    @Override
    public Trip createTrip(Trip trip) {

        Trip newTrip = new Trip();

        newTrip.setStartTown(trip.getStartTown());
        newTrip.setEndTown(trip.getEndTown());
        newTrip.setDateOfDeparture(trip.getDateOfDeparture());
        newTrip.setDateOfArrival(trip.getDateOfArrival());
        newTrip.setAssignedTickets(trip.getAssignedTickets());

        return tripRepository.save(newTrip);
    }

    @Override
    public Trip updateTrip(Trip trip, Long description) {
        Trip foundTrip = tripRepository.findTripById(description);

        foundTrip.setStartTown(trip.getStartTown());
        foundTrip.setEndTown(trip.getEndTown());
        foundTrip.setDateOfDeparture(trip.getDateOfDeparture());
        foundTrip.setDateOfArrival(trip.getDateOfArrival());
        foundTrip.setAssignedTickets(trip.getAssignedTickets());

        return tripRepository.save(foundTrip);
    }

    @Override
    public Trip findByDescription(Long description) {
        return tripRepository.findTripById(description);
    }

    @Override
    public TripDto entityToDto(Trip trip) {

        String startTown = trip.getStartTown() == null ? "" : trip.getStartTown().getTitle();
        String endTown = trip.getEndTown() == null ? "" : trip.getEndTown().getTitle();
        return new TripDto(startTown, endTown, trip.getSeats(), trip.getDateOfDeparture(), trip.getDateOfArrival());
    }

    @Override
    public Trip dtoToEntity(TripDto tripDto) {
        Town startTown;
        Town endTown;

        if(tripDto.getStartTown().equals("")){
            startTown=null;
        }else{
            startTown=townRepository.findByTitle(tripDto.getStartTown());
        }

        if(tripDto.getEndTown().equals("")){
            endTown=null;
        }
        else{
            endTown= townRepository.findByTitle(tripDto.getEndTown());
        }
        return new Trip(startTown, endTown, tripDto.getSeats(), tripDto.getDateOfDeparture(), tripDto.getDateOfArrival());
    }


    @Override
    public List<Trip> findAllOrganisedTripsByTown(String title) {
        Town town = townRepository.findByTitle(title);

        if (town != null) {
            // Directly return the result from the repository method
            return tripRepository.findAllByStartTownAndEndTown(town, town);
        } else {
            // Handle the case when the town with the given title is not found
            return null;
        }
    }


    @Override
    public void deleteByDescription(Long description) {
        tripRepository.deleteTripById(description);
    }
}
