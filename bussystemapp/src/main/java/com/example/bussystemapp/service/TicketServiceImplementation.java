package com.example.bussystemapp.service;

import com.example.bussystemapp.dtos.TicketDto;
import com.example.bussystemapp.model.Ticket;
import com.example.bussystemapp.model.Trip;
import com.example.bussystemapp.model.User;
import com.example.bussystemapp.repository.TicketRepository;
import com.example.bussystemapp.repository.TripRepository;
import com.example.bussystemapp.repository.UserRepository;
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
        newTicket.setStatus(ticket.getStatus());
        newTicket.setPrice(ticket.getPrice());
        newTicket.setTrip(ticket.getTrip());
        newTicket.setAssignedTo(ticket.getAssignedTo());
        return ticketRepository.save(newTicket);
    }


    @Override
    public Ticket updateTicket(Ticket ticket, Long id) {
        Ticket foundTicket = ticketRepository.findById(id).orElseThrow(EntityExistsException::new);
        foundTicket.setTitle(ticket.getTitle());
        foundTicket.setStatus(ticket.getStatus());
        foundTicket.setTrip(ticket.getTrip());
        foundTicket.setPrice(ticket.getPrice());
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
    public TicketDto entityToDto(Ticket ticket) {
        String assignedTo = ticket.getAssignedTo() == null ? "" : ticket.getAssignedTo().getUsername();
        String trip = ticket.getTrip() == null ? "" : ticket.getTrip().getDescription();
        return new TicketDto(ticket.getTitle(), ticket.getStatus(), ticket.getPrice(), trip, assignedTo);
    }

    @Override
    public Ticket dtoToEntity(TicketDto ticketDto) {
        User assignedTo;
        Trip trip;

        if (ticketDto.getAssignedTo().equals("")) {
            assignedTo = null;
        } else {
            assignedTo = userRepository.findById(ticketDto.getAssignedTo()).orElseThrow(EntityExistsException::new);
        }

        if (ticketDto.getTrip().equals("")) {
            trip = null;
        } else {
            trip = tripRepository.findByDescription(ticketDto.getTrip());
        }
        return new Ticket(ticketDto.getTitle(), ticketDto.getStatus(), ticketDto.getPrice(), trip, assignedTo);
    }

    @Override
    public void deleteById(Long id) {
        ticketRepository.deleteById(id);

    }
}
