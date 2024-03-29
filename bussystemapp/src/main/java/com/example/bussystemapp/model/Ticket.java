package com.example.bussystemapp.model;

import com.example.bussystemapp.utils.Status;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {


    @Id
    @SequenceGenerator(
            name = "ticket_id",
            sequenceName = "ticket_id",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "ticket_id"
    )
    private Long id;

    @Column(name = "Title", nullable = false)
    private String title;

    @Column(name = "Status",nullable = false)
    private Status status ;



    @ManyToOne()
    @JoinColumn(name = "AssignedTo",referencedColumnName = "username")
    private User assignedTo;



    @Column(name = "Price",nullable = false)
    private Double price;


    @ManyToOne()
    @JoinColumn(name = "Route",referencedColumnName = "id")
    @JsonBackReference
    private Trip trip;


    public Ticket(String title, Status status,double price,Trip trip,User assignedTo) {
        this.title = title;
        this.status = status;
        this.price = price;
        this.trip=trip;
        this.assignedTo=assignedTo;
    }

}
