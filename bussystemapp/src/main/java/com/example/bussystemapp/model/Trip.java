package com.example.bussystemapp.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Trip {

    @Id
    @Column(name = "desciption",nullable = false)
    private String description;


    @ManyToOne()
    @JoinColumn(name = "StartTown",referencedColumnName = "title")
    private Town startTown;

    @ManyToOne()
    @JoinColumn(name = "EndTown",referencedColumnName = "title")
    private Town endTown;

    @Column(name = "Seats", nullable = false)
    private Long seats;

    //check how LocalDateTime works for specific date and time
    @Column(name = "DateOfDeparture", nullable = false)
    private LocalDateTime dateOfDeparture;

    @Column(name = "DateOfArrival", nullable = false)
    private LocalDateTime dateOfArrival;


    @OneToMany(mappedBy = "trip",cascade = CascadeType.ALL)
    private Set<Ticket> assignedTickets = new HashSet<>();

    public Trip(String description,Long seats, LocalDateTime dateOfDeparture, LocalDateTime dateOfArrival) {
        this.description=description;
        this.seats = seats;
        this.dateOfDeparture = dateOfDeparture;
        this.dateOfArrival = dateOfArrival;
    }


}
