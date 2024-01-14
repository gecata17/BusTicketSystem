package com.example.bussystemapp.controller;

import com.example.bussystemapp.dtos.TicketDto;
import com.example.bussystemapp.model.Ticket;
import com.example.bussystemapp.service.TicketService;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("private/api/ticketsearch")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;


    @GetMapping("/usertickets/{username}")
    public ResponseEntity<List<TicketDto>> getTicketsByUser(@PathVariable("username") String username){

        List<Ticket> tickets = new ArrayList<>();
        try {
            tickets=ticketService.findAllTicketsByUser(username);
        }catch (Exception exception){
            return  ResponseEntity.badRequest().build();
        }

        return new ResponseEntity<>(tickets.stream().map(ticketService::entityToDto).toList(), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<TicketDto>> getTicketsByRoute(@RequestParam("startTown") String startTown, @RequestParam("endTown") String endTown, @RequestParam("dateOfDeparture") String date){
        List<Ticket> tickets = new ArrayList<Ticket>();
        try {
            tickets = ticketService.findAllTicketsByRoute(startTown, endTown, LocalDate.parse(date));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().build();
        }
        return new ResponseEntity<>(tickets.stream().map(ticketService::entityToDto).toList(),HttpStatus.OK);
    }

    @PostMapping("/{trip_id}")
    public ResponseEntity<TicketDto> createTicket(@PathVariable("trip_id") Long tripId, @RequestBody TicketDto ticketDto){
        try{
            Ticket ticket=ticketService.dtoToEntity(ticketDto, tripId);
            TicketDto newTicketDto=ticketService.entityToDto(ticketService.createTicket(ticket));
            return new ResponseEntity<>(newTicketDto, HttpStatus.CREATED);
        } catch (EntityExistsException ex){

            return ResponseEntity.badRequest().build();
        }
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<TicketDto> updateTicket(@PathVariable("id") Long id, @RequestBody TicketDto ticketDto){
        try{
            System.out.println(ticketDto);
            Long tripId = ticketDto.getTrip() != null ? ticketDto.getTrip().getId() : -1;
            return new ResponseEntity<>(ticketService.entityToDto(ticketService.updateTicket(ticketService.dtoToEntity(ticketDto, tripId), id)),HttpStatus.OK);
        } catch (EntityExistsException exception){
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public void deleteTicket(@PathVariable("id") Long id){
        ticketService.deleteById(id);
    }

}
