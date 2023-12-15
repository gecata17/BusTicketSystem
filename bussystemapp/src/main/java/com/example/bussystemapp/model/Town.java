package com.example.bussystemapp.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Town")
public class Town {


    @Id
    @Column(name = "Title",nullable = false)
    private String title;

    @Column(name = "Longitude",nullable = false)
    private double longitude;

    @Column(name = "Latitude",nullable = false)
    private double latitude;


    @OneToMany(mappedBy = "startTown",cascade = CascadeType.ALL)
    private Set<Trip> startTownTrip= new HashSet<>();

    @OneToMany(mappedBy = "endTown",cascade = CascadeType.ALL)
    private Set<Trip> endTownTrip = new HashSet<>();


    public Town(String title) {
        this.title = title;
    }

}
