package com.example.bussystemapp.dtos;

import com.example.bussystemapp.utils.Status;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

public class TicketDto {

    private Long id;

    private String title;

    private Status status;

    private double price;

    private TripDto trip;

    private String assignedTo;


    @JsonCreator
    public TicketDto(Long id, @JsonProperty("title") String title,@JsonProperty("status") Status status,@JsonProperty("price") double price,@JsonProperty("trip") TripDto trip,@JsonProperty("assignedTo") String assignedTo){
        this.id = id;
        this.title=title;
        this.status=status;
        this.price=price;
        this.trip=trip;
        this.assignedTo=assignedTo;
    }
}
