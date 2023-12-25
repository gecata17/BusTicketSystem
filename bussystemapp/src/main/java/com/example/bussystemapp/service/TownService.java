package com.example.bussystemapp.service;

import com.example.bussystemapp.dtos.TownDto;
import com.example.bussystemapp.model.Town;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TownService {


    Town createTown(Town town);

    Town findByTitle(String title);

    Town updateTown(Town town, String title);

    List<Town> getAllTowns();

    TownDto entityToDto(Town town);

    Town dtoToEntity(TownDto townDto);

    void deleteByTitle(String title);

}
