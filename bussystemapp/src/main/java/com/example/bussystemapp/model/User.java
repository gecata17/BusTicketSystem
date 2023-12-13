package com.example.bussystemapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;


import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "User")
public class User  {

    @Id
    @Column(name = "username", nullable = false)
    String username;

    @JsonIgnore
    @Column(name = "Password",nullable = false)
    private String password;

    @Column(name = "Email",nullable = false)
    private String email;

   //// ???????????
    //@OneToMany(mappedBy = "assignedTo",cascade = Cascade.ALL)
    //private Set<Tickets> tickets = new HashSet<>();
}
