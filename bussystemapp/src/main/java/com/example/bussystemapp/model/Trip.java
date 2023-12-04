package com.example.bussystemapp.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Trip")
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

    ////???????????????????????????
    //    @OneToMany(mappedBy = "route",cascade = CascadeType.ALL)
    //    private Set<Ticket> assignedTickets = new HashSet<>();
}
