package com.example.bussystemapp.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class TicketsRequest {
    private String startTown;
    private String endTown;
    private LocalDate dateOfDeparture;

}
