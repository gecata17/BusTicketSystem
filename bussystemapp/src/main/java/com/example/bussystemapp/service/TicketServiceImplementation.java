package com.example.bussystemapp.service;

import com.example.bussystemapp.model.Ticket;
import com.example.bussystemapp.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketServiceImplementation implements TicketService {


    private final TicketRepository ticketRepository;


    @Override
    public Ticket createTicket(Ticket ticket) {
        return null;
    }

    @Override
    public Ticket updateTicket(Ticket ticket, Long id) {
        return null;
    }

//    @Override
//    public List<Ticket> findAllTicketsByUser(String username) {
//        return ticketRepository.findAllByAssignedTo(username);
//    }

//    @Override
//    public List<Ticket> findAllTicketsByRoute(String description) {
//        return ticketRepository.findAllByTrip(description);
//    }

    @Override
    public void deleteById(Long id) {
          ticketRepository.deleteById(id);
    }
}
