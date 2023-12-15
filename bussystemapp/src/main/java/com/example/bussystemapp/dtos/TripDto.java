package com.example.bussystemapp.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TripDto {

    private String description;

    private Long seats;

    private LocalDateTime dateOfDeparture;

    private LocalDateTime dateOfArrival;
}
