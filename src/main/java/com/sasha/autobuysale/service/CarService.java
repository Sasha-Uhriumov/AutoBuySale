package com.sasha.autobuysale.service;

import com.sasha.autobuysale.dto.CreateCarDTO;
import com.sasha.autobuysale.dto.ResponseCarDTO;
import com.sasha.autobuysale.entity.Car;
import com.sasha.autobuysale.exception.CarExists;
import com.sasha.autobuysale.mapper.CarMapper;
import com.sasha.autobuysale.repository.CarRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class CarService {

    private final CarRepository carRepository;

    @Transactional
    public ResponseCarDTO createCar(CreateCarDTO createCarDTO) {
        log.info("Attempt to create a car with VIN: {}", createCarDTO.getVin());

        if (carRepository.existsByVin(createCarDTO.getVin())) {
           throw new CarExists("Car already exists with VIN " + createCarDTO.getVin());
        }

        Car car = CarMapper.toEntity(createCarDTO);

        Car carSaved = carRepository.save(car);
        log.info("Car successfully created with VIN: {}", carSaved.getVin());

       return CarMapper.fromEntity(carSaved);
    }

    @Transactional(readOnly = true)
    public List<ResponseCarDTO> getAllCars() {
        log.info("Try to get all the cars");

        return carRepository.findAll()
                .stream()
                .map(CarMapper::fromEntity)
                .toList();
    }
}
