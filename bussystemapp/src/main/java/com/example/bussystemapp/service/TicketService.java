package com.example.bussystemapp.service;

import com.example.bussystemapp.dtos.TicketDto;
import com.example.bussystemapp.model.Ticket;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface TicketService {

      Ticket createTicket(Ticket ticket);
      Ticket updateTicket(Ticket ticket, Long id);

      List<Ticket> findAllTicketsByUser(String username);

      List<Ticket> findAllTicketsByRoute(String startTown, String endTown, LocalDate date) throws EntityNotFoundException;

      TicketDto entityToDto(Ticket ticket);

      Ticket dtoToEntity(TicketDto ticketDto, Long tripId);

      void deleteById(Long id);
}
