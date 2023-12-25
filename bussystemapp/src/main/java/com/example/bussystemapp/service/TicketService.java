package com.example.bussystemapp.service;

import com.example.bussystemapp.dtos.TicketDto;
import com.example.bussystemapp.model.Ticket;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TicketService {

      Ticket createTicket(Ticket ticket);
      Ticket updateTicket(Ticket ticket, Long id);

      List<Ticket> findAllTicketsByUser(String username);

      List<Ticket> findAllTicketsByRoute(String description);

      TicketDto entityToDto(Ticket ticket);

      Ticket dtoToEntity(TicketDto ticketDto);

      void deleteById(Long id);
}
