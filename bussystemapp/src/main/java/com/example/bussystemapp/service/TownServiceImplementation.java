package com.example.bussystemapp.service;

import com.example.bussystemapp.model.Town;
import com.example.bussystemapp.repository.TownRepository;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TownServiceImplementation implements TownService {

    private TownRepository townRepository;

    //TO DO
    @Override
    public Town createTown(Town town) {
        return null;
    }



    @Override
    public Town findByTitle(String title) {
        return townRepository.findById(title).orElseThrow(EntityExistsException::new);
    }


    @Override
    public List<Town> getAllTowns() {
        List<Town> towns = new ArrayList<>();

        townRepository.findAll().forEach(towns::add);
        return towns;
    }

    @Override
    public void deleteByTitle(String title) {

        townRepository.deleteTownByTitle(title);
    }
}
