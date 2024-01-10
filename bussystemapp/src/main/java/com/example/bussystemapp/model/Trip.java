package com.example.bussystemapp.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
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
    @SequenceGenerator(
            name = "trip_id",
            sequenceName = "trip_id",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "trip_id"
    )
    private Long id;


    @ManyToOne()
    @JoinColumn(name = "StartTown",referencedColumnName = "title")
    private Town startTown;

    @ManyToOne()
    @JoinColumn(name = "EndTown",referencedColumnName = "title")
    private Town endTown;

    @Column(name = "Seats", nullable = false)
    private Long seats = 0L;

    //check how LocalDateTime works for specific date and time
    @Column(name = "DateOfDeparture", nullable = false)
    private LocalDate dateOfDeparture;

    @Column(name = "DateOfArrival", nullable = false)
    private LocalDate dateOfArrival;


    @OneToMany(mappedBy = "trip",cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<Ticket> assignedTickets = new HashSet<>();

    public Trip(Town startTown,Town endTown,Long seats, LocalDate dateOfDeparture, LocalDate dateOfArrival) {
        this.startTown=startTown;
        this.endTown=endTown;
        this.seats = seats;
        this.dateOfDeparture = dateOfDeparture;
        this.dateOfArrival = dateOfArrival;

    }


}
