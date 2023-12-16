package com.example.bussystemapp.controller;

import com.example.bussystemapp.dtos.TicketDto;
import com.example.bussystemapp.model.Ticket;
import com.example.bussystemapp.service.TicketService;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("private/api/ticketsearch")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;


    @GetMapping("/{myprofile}")
    public ResponseEntity<List<TicketDto>> getTickets(@PathVariable("myprofile") String username){

        List<Ticket> tickets = ticketService.findAllTicketsByUser(username);

        return new ResponseEntity<>(tickets.stream().map(ticketService::entityToDto).toList(), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<TicketDto> createTicket(@RequestBody TicketDto ticketDto){
        try{
            return new ResponseEntity<>(ticketService.entityToDto(ticketService.createTicket(ticketService.dtoToEntity(ticketDto))), HttpStatus.CREATED);
        } catch (EntityExistsException ex){

            return ResponseEntity.badRequest().build();
        }
    }


    //doesn't work
    @PutMapping("/update/{id}")
    public ResponseEntity<TicketDto> updateTicket(@PathVariable("id") Long id, @RequestBody TicketDto ticketDto){
        try{
            return new ResponseEntity<>(ticketService.entityToDto(ticketService.updateTicket(ticketService.dtoToEntity(ticketDto),id)),HttpStatus.OK);
        } catch (EntityExistsException exception){
            return ResponseEntity.badRequest().build();
        }
    }

    //doesn't work
    @DeleteMapping("/delete/{id}")
    public void deleteTicket(@PathVariable("id") Long id){
        ticketService.deleteById(id);
    }

}
