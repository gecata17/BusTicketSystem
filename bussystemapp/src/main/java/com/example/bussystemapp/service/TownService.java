package com.example.bussystemapp.service;

import com.example.bussystemapp.model.Town;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TownService {

    //to create dtos
    Town createTown(Town town);

    Town findByTitle(String title);

    List<Town> getAllTowns();

    void deleteByTitle(String title);

}
