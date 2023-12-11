package com.example.bussystemapp.service;

import com.example.bussystemapp.model.Ticket;
import com.example.bussystemapp.model.Trip;
import com.example.bussystemapp.model.User;
import com.example.bussystemapp.repository.TicketRepository;
import com.example.bussystemapp.repository.TripRepository;
import com.example.bussystemapp.repository.UserRepository;
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
        return null;
    }

    //TO DO
    @Override
    public Ticket updateTicket(Ticket ticket, Long id) {

        //  Ticket found = ticketRepository.
                return null;
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
