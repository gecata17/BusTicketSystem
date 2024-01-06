package com.example.bussystemapp.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class TripDto {

    private String startTown;
    private String endTown;
    private Long seats;

    private LocalDate dateOfDeparture;
    private LocalDate dateOfArrival;


}
