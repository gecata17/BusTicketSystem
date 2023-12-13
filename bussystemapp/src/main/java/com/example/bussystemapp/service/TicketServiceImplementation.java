package com.example.bussystemapp.service;

import com.example.bussystemapp.model.Ticket;
import com.example.bussystemapp.model.Trip;
import com.example.bussystemapp.model.User;
import com.example.bussystemapp.repository.TicketRepository;
import com.example.bussystemapp.repository.TripRepository;
import com.example.bussystemapp.repository.UserRepository;
import com.example.bussystemapp.utils.Status;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketServiceImplementation implements TicketService {


    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;
    private final TripRepository tripRepository;


    //TO DO
    @Override
    public Ticket createTicket(Ticket ticket) {

        if (ticket.getAssignedTo() != null) {
            if (userRepository.findByUsername(ticket.getAssignedTo().getUsername()) == null) {
                throw new IllegalArgumentException("Invalid username");
            }
        }

          Ticket newTicket = new Ticket();
          newTicket.setId(ticket.getId());
          newTicket.setTitle(ticket.getTitle());
          newTicket.setStatus(Status.valueOf("AVAILABLE"));
          newTicket.setTrip(ticket.getTrip());
          newTicket.setAssignedTo(ticket.getAssignedTo());
          return ticketRepository.save(newTicket);
    }

    //TO DO
    @Override
    public Ticket updateTicket(Ticket ticket, Long id) {
        Ticket foundTicket  = ticketRepository.findById(id).orElseThrow(EntityExistsException::new);
        foundTicket.setTitle(ticket.getTitle());
        foundTicket.setStatus(ticket.getStatus());
        foundTicket.setTrip(ticket.getTrip());
        foundTicket.setAssignedTo(ticket.getAssignedTo());

        return ticketRepository.save(foundTicket);

    }

    @Override
    public List<Ticket> findAllTicketsByUser(String username) {
        User user = userRepository.findByUsername(username);
        return ticketRepository.findAllByAssignedTo(user);
    }

    @Override
    public List<Ticket> findAllTicketsByRoute(String description) {
        Trip trip = tripRepository.findByDescription(description);
        return ticketRepository.findAllByTrip(trip);
    }

    @Override
    public void deleteById(Long id) {
        ticketRepository.deleteById(id);
    }
}
