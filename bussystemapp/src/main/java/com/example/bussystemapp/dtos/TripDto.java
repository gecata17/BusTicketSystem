package com.example.bussystemapp.dtos;

import com.example.bussystemapp.utils.Status;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data

public class TripDto {

    private Long id;

    private String startTown;
    private String endTown;
    private Long seats;

    private LocalDate dateOfDeparture;
    private LocalDate dateOfArrival;


    @JsonCreator
    public TripDto(@JsonProperty("startTown") String startTown, @JsonProperty("endTown") String endTown, @JsonProperty("seats") Long seats, @JsonProperty("dateOfDeparture") LocalDate dateOfDeparture, @JsonProperty("dateOfArrival") LocalDate dateOfArrival){
        this.startTown=startTown;
        this.endTown=endTown;
        this.seats=seats;
        this.dateOfDeparture=dateOfDeparture;
        this.dateOfArrival=dateOfArrival;
    }
}
