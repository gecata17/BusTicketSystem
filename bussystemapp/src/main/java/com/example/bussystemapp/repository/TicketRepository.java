package com.example.bussystemapp.repository;

import com.example.bussystemapp.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket,Long> {

    //List<Ticket> findAllByAssignedTo(String username);

    //List<Ticket> findAllByTrip(String description);

    void deleteById(Long id);
}
