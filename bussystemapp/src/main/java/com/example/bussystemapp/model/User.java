package com.example.bussystemapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;


import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class User  {

    @Id
    @Column(name = "username", nullable = false)
    String username;

    @JsonIgnore
    @Column(name = "password",nullable = false)
    private String password;

    @Column(name = "email",nullable = false)
    private String email;


    @OneToMany(mappedBy = "assignedTo",cascade = CascadeType.ALL)
    private Set<Ticket> tickets = new HashSet<>();

    public User(String username, String email,String password) {
        this.username = username;
        this.email = email;
        this.password = password;

    }

}
