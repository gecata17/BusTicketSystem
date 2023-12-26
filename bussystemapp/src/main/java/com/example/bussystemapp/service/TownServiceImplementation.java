package com.example.bussystemapp.service;

import com.example.bussystemapp.dtos.TownDto;
import com.example.bussystemapp.model.Town;
import com.example.bussystemapp.repository.TownRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

        Optional<Town> foundOptional = townRepository.findByTitle(title);

        if(foundOptional.isPresent()){
            Town found = foundOptional.get();
            found.setTitle(town.getTitle());
            found.setLongitude(town.getLongitude());
            found.setLatitude(town.getLatitude());
            found.setStartTownTrip(town.getStartTownTrip());
            found.setEndTownTrip(town.getEndTownTrip());
            return townRepository.save(found);
        }
        throw new EntityNotFoundException("Town with the following name :  " + title + " is not found ");
    }


    @Override
    public List<Town> getAllTowns() {

        return new ArrayList<>(townRepository.findAll());
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
