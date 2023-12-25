package com.example.bussystemapp.dtos;

import com.example.bussystemapp.utils.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketDto {
    private String title;

    private Status status;

    private double price;
}
