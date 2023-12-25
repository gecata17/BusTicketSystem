package com.example.bussystemapp.controller;

import com.example.bussystemapp.dtos.TownDto;
import com.example.bussystemapp.model.Town;
import com.example.bussystemapp.repository.TownRepository;
import com.example.bussystemapp.service.TownService;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("private/api/towns")
@RequiredArgsConstructor
public class TownController {

    private TownService townService;


    @PostMapping()
    public ResponseEntity<TownDto> createTown(@RequestBody TownDto townDto){
        try{
            return new ResponseEntity<>(townService.entityToDto(townService.createTown(townService.dtoToEntity(townDto))), HttpStatus.CREATED);
        } catch (EntityExistsException | IllegalArgumentException existsException){
            return ResponseEntity.badRequest().build();
        }

    }


    @PutMapping("/update")
    public ResponseEntity<TownDto> updateTown(@RequestBody TownDto townDto){

    }

    @GetMapping("/all")
    public ResponseEntity<List<Town>> getAllTowns() {
        return new ResponseEntity<>(townService.getAllTowns(),HttpStatus.OK);

    }

}
