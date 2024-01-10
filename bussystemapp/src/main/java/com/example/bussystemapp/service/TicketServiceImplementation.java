package com.example.bussystemapp.service;

import com.example.bussystemapp.dtos.TicketDto;
import com.example.bussystemapp.dtos.TripDto;
import com.example.bussystemapp.model.Ticket;
import com.example.bussystemapp.model.Town;
import com.example.bussystemapp.model.Trip;
import com.example.bussystemapp.model.User;
import com.example.bussystemapp.repository.TicketRepository;
import com.example.bussystemapp.repository.TownRepository;
import com.example.bussystemapp.repository.TripRepository;
import com.example.bussystemapp.repository.UserRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketServiceImplementation implements TicketService {


    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;
    private final TripRepository tripRepository;

    private final TownRepository townRepository;

    private final TripService tripService;


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
    public List<Ticket> findAllTicketsByRoute(String startTownName, String endTownName, LocalDate date) throws EntityNotFoundException {
        Town startTown=townRepository.findByTitle(startTownName);
        if (startTown==null) {
            throw new EntityNotFoundException("Invalid town name");
        }
        Town endTown=townRepository.findByTitle(endTownName);
        if (endTown==null) {
            throw new EntityNotFoundException("Invalid town name");
        }
        List<Trip> trips = tripRepository.findAllByStartTownAndEndTownAndDateOfDeparture(startTown, endTown, date);
        if (trips==null) {
            return null;
        }
        List<Ticket> tickets = new ArrayList<Ticket>();
        for (Trip trip : trips) {
            tickets.addAll(ticketRepository.findAllByTrip(trip));
        }
        return tickets;
    }

    @Override
    public TicketDto entityToDto(Ticket ticket) {
        String assignedTo = ticket.getAssignedTo() == null ? "" : ticket.getAssignedTo().getUsername();
        TripDto trip = tripService.entityToDto(ticket.getTrip() == null ? new Trip() : ticket.getTrip());
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
            trip = tripRepository.findById(ticketDto.getTrip().getStartTown()).orElseThrow(EntityNotFoundException::new);
        }
        return new Ticket(ticketDto.getTitle(), ticketDto.getStatus(), ticketDto.getPrice(), trip, assignedTo);
    }

    @Override
    public void deleteById(Long id) {
        ticketRepository.deleteById(id);

    }
}
