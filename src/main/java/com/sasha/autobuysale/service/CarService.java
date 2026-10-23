package com.sasha.autobuysale.service;

import com.sasha.autobuysale.dto.CarSearchDTO;
import com.sasha.autobuysale.dto.CreateCarDTO;
import com.sasha.autobuysale.dto.ResponseCarDTO;
import com.sasha.autobuysale.dto.UpdateCarDTO;
import com.sasha.autobuysale.entity.Car;
import com.sasha.autobuysale.exception.CarExists;
import com.sasha.autobuysale.exception.CarNotFound;
import com.sasha.autobuysale.mapper.CarMapper;
import com.sasha.autobuysale.repository.CarRepository;
import com.sasha.autobuysale.specification.CarSpecification;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
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
        log.info("Attempt to create a car with vin: {}", createCarDTO.getVin());

        if (carRepository.existsByVin(createCarDTO.getVin())) {
           throw new CarExists("Car already exists with vin: " + createCarDTO.getVin());
        }

        Car car = CarMapper.toEntity(createCarDTO);

        Car carSaved = carRepository.save(car);
        log.info("Car successfully created with vin: {}", carSaved.getVin());

       return CarMapper.fromEntity(carSaved);
    }

    @Transactional(readOnly = true)
    public List<ResponseCarDTO> searchCars(CarSearchDTO carSearchDTO) {
        log.info("Attempt to get e car");

        Specification<Car> specification = buildSearchSpecification(carSearchDTO);

       return carRepository.findAll(specification).stream()
               .map(CarMapper::fromEntity)
               .toList();
    }

    @Transactional
    public ResponseCarDTO updateCar(String findByVin, UpdateCarDTO updateCarDTO) {
        log.info("Attempt to update car with {}", findByVin);

        Car existsCar = carRepository.findByVin(findByVin)
                .orElseThrow(() -> new CarNotFound("Car not found with vin: " + findByVin));

        Car updatedCar = CarMapper.updateCar(existsCar, updateCarDTO);
        log.info("Car with vin: {} successfully updated", updatedCar.getVin());

        return CarMapper.fromEntity(carRepository.save(updatedCar));
    }

    @Transactional
    public void deleteCarByVin(String vin) {
        log.info("Attempt to delete car with win: {}", vin);

        Car existsCar = carRepository.findByVin(vin)
                .orElseThrow(() -> new CarNotFound("Car not found with vin: " + vin));

        carRepository.delete(existsCar);
        log.info("Car with vin: {} successfully deleted", vin);
    }

    public Specification<Car> buildSearchSpecification(CarSearchDTO carSearchDTO) {

        Specification<Car> specification = Specification.unrestricted();

        if (carSearchDTO.getBrand() != null && !carSearchDTO.getBrand().isBlank()) {
            specification = specification.and(CarSpecification.hasBrand(carSearchDTO.getBrand()));
        }

        if (carSearchDTO.getModel() != null && !carSearchDTO.getModel().isBlank()) {
            specification = specification.and(CarSpecification.hasModel(carSearchDTO.getModel()));
        }

        if (carSearchDTO.getYear() != null) {
            specification = specification.and(CarSpecification.hasYear(carSearchDTO.getYear()));
        }

        if (carSearchDTO.getMileage() != null) {
            specification = specification.and(CarSpecification.hasMileage(carSearchDTO.getMileage()));
        }

        if (carSearchDTO.getGenerations() != null && !carSearchDTO.getGenerations().isBlank()) {
            specification = specification.and(CarSpecification.hasGenerations(carSearchDTO.getGenerations()));
        }

        if (carSearchDTO.getBodyType() != null && !carSearchDTO.getBodyType().isBlank()) {
            specification = specification.and(CarSpecification.hasBodyType(carSearchDTO.getBodyType()));
        }

        if (carSearchDTO.getVin() != null && !carSearchDTO.getVin().isBlank()) {
            specification = specification.and(CarSpecification.hasVin(carSearchDTO.getVin()));
        }

        if (carSearchDTO.getClearedCustoms() != null && !carSearchDTO.getClearedCustoms().isBlank()) {
            specification = specification.and(CarSpecification.hasClearedCustoms(carSearchDTO.getClearedCustoms()));
        }
        return specification;
    }
}
