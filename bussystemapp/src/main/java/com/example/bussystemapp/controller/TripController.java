package com.example.bussystemapp.controller;



import com.example.bussystemapp.dtos.TripDto;
import com.example.bussystemapp.model.Trip;
import com.example.bussystemapp.service.TripService;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("private/api/trips")
@RequiredArgsConstructor
public class TripController {

    private final TripService tripService;

    @PostMapping()
    public ResponseEntity<TripDto> createTrip(@RequestBody TripDto tripDto){
        try{
            return new ResponseEntity<>(tripService.entityToDto(tripService.createTrip(tripService.dtoToEntity(tripDto))), HttpStatus.CREATED);
        } catch (EntityExistsException | IllegalArgumentException existsException){
            return ResponseEntity.badRequest().build();
        }

    }

    @PutMapping("/update/{description}")
    public ResponseEntity<TripDto> updateTrip(@PathVariable("description") Long description, @RequestBody TripDto tripDto){
        try{
            return new ResponseEntity<>(tripService.entityToDto(tripService.updateTrip(tripService.dtoToEntity(tripDto), description)),HttpStatus.OK);

        } catch (EntityExistsException | IllegalArgumentException exception){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{title}")
    public ResponseEntity<List<TripDto>> getTripsByTown(@PathVariable("title") String title){
        List<Trip> trips = tripService.findAllOrganisedTripsByTown(title);
        return new ResponseEntity<>(trips.stream().map(tripService::entityToDto).toList(),HttpStatus.OK);
    }

    @DeleteMapping("/{description}")
    public void deleteTrip(@PathVariable("description") Long description){
        tripService.deleteByDescription(description);
    }

}
