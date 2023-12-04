package com.example.bussystemapp.generator;

import com.example.bussystemapp.repository.TicketRepository;
import com.example.bussystemapp.repository.TownRepository;
import com.example.bussystemapp.repository.TripRepository;
import com.example.bussystemapp.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataGenerator {

    @Bean
    CommandLineRunner commandLineRunner(TicketRepository ticketRepository,
                                        UserRepository userRepository,
                                        TownRepository townRepository,
                                        TripRepository tripRepository){
        return args -> {

        };
    }
}
