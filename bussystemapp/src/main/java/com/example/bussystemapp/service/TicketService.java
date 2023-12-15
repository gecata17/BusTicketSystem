package com.example.bussystemapp.service;

import com.example.bussystemapp.dtos.TicketDto;
import com.example.bussystemapp.model.Ticket;
import com.example.bussystemapp.repository.TicketRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TicketService {

      //to create dtos
      Ticket createTicket(Ticket ticket);
      Ticket updateTicket(Ticket ticket, Long id);

      List<Ticket> findAllTicketsByUser(String username);

      List<Ticket> findAllTicketsByRoute(String description);

      TicketDto entityToDto(Ticket ticket);

      Ticket dtoToEntity(Ticket ticketDto);

      void deleteById(Long id);
}
