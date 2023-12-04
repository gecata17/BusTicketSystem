package com.example.bussystemapp.service;

import com.example.bussystemapp.model.Town;
import com.example.bussystemapp.repository.TownRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TownServiceImplementation implements TownService {

    private TownRepository townRepository;

    @Override
    public Town createTown(Town town) {
        return null;
    }

    @Override
    public Town updateTown(Town town) {
        return townRepository.save(town);
    }

    @Override
    public List<Town> getAllTowns() {
        return null;
    }

    @Override
    public void deleteByTitle(String title) {

        townRepository.deleteTownByTitle(title);
    }
}
