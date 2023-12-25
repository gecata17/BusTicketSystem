package com.example.bussystemapp.service;

import com.example.bussystemapp.dtos.TownDto;
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

    private final TownRepository townRepository;


    @Override
    public Town createTown(Town town) {



        Town newTown = new Town();
        newTown.setTitle(town.getTitle());
        newTown.setLongitude(town.getLongitude());
        newTown.setLatitude(town.getLatitude());
        newTown.setStartTownTrip(town.getStartTownTrip());
        newTown.setEndTownTrip(town.getEndTownTrip());
        return townRepository.save(newTown);

    }



    @Override
    public Town findByTitle(String title) {
        return townRepository.findById(title).orElseThrow(EntityExistsException::new);
    }

    @Override
    public Town updateTown(Town town, String title) {
        return null;
    }


    @Override
    public List<Town> getAllTowns() {
        List<Town> towns = new ArrayList<>();

        townRepository.findAll().forEach(towns::add);
        return towns;
    }

    @Override
    public TownDto entityToDto(Town town) {
        return new TownDto(town.getTitle());
    }

    @Override
    public Town dtoToEntity(TownDto townDto) {
        return new Town(townDto.getTitle());
    }

    @Override
    public void deleteByTitle(String title) {

        townRepository.deleteTownByTitle(title);


    }
}
