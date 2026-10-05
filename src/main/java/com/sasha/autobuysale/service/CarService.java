package com.sasha.autobuysale.service;

import com.sasha.autobuysale.dto.CreateCarDTO;
import com.sasha.autobuysale.dto.ResponseCarDTO;
import com.sasha.autobuysale.entity.Car;
import com.sasha.autobuysale.mapper.CarMapper;
import com.sasha.autobuysale.repository.CarRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@AllArgsConstructor
public class CarService {

    private final CarRepository carRepository;

    @Transactional
    public ResponseCarDTO createCar(CreateCarDTO createCarDTO) {

        Car car = CarMapper.toEntity(createCarDTO);

       return CarMapper.fromEntity(carRepository.save(car));
    }
}
