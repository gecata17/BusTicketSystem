package com.example.bussystemapp.repository;

import com.example.bussystemapp.model.Town;
import com.example.bussystemapp.model.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TripRepository extends JpaRepository<Trip,String> {

    Trip findTripById(Long description);


    List<Trip> findAllByStartTownAndEndTown(Town startTown,Town endTown);

    List<Trip> findAllByStartTownAndEndTownAndDateOfDeparture(Town startTown, Town endTown, LocalDate dateOfDeparture);

    void deleteTripById(Long description);

}
