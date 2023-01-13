package com.example.EntgraTask.device;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class DeviceConfiguration {

    @Bean
    CommandLineRunner commandLineRunner(DeviceRepository repository){
        return args -> {
            List<Device> students = new ArrayList<>();
            students.add(new Device("Oshanath's phone", DeviceStatus.ENROLLED, "iPhone XR", LocalDateTime.of(2023, 1, 3,13, 24)));
            students.add(new Device("Ravindu's phone", DeviceStatus.ACTIVE, "Note 20 plus", LocalDateTime.of(2023, 1, 3,13, 24)));
            repository.saveAll(students);
        };
    }

}
