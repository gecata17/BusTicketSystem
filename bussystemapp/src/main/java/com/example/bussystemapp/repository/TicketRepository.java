package com.example.bussystemapp.repository;

import com.example.bussystemapp.model.Ticket;
import com.example.bussystemapp.model.Trip;
import com.example.bussystemapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket,Long> {

    List<Ticket> findAllByAssignedTo(User assignedTo);

    List<Ticket> findAllByTrip(Trip description);

    void deleteById(Long id);
}
