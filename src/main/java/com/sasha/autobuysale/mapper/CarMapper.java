package com.sasha.autobuysale.mapper;

import com.sasha.autobuysale.dto.CreateCarDTO;
import com.sasha.autobuysale.dto.ResponseCarDTO;
import com.sasha.autobuysale.dto.UpdateCarDTO;
import com.sasha.autobuysale.entity.Car;
import com.sasha.autobuysale.enums.ClearedCustoms;

public class CarMapper {

    public static Car toEntity(CreateCarDTO createCarDTO) {
        Car car = new Car();

        car.setBrand(createCarDTO.getBrand());
        car.setModel(createCarDTO.getModel());
        car.setYear(createCarDTO.getYear());
        car.setMileage(createCarDTO.getMileage());
        car.setGeneration(createCarDTO.getGeneration());
        car.setBodyType(createCarDTO.getBodyType());
        car.setVin(createCarDTO.getVin());

        if (createCarDTO.getClearedCustoms().equalsIgnoreCase("YES")) {
            car.setClearedCustoms(true);
        } else {
            car.setClearedCustoms(false);
        }

        return car;
    }

    public static ResponseCarDTO fromEntity(Car car) {
        ResponseCarDTO responseCarDTO = new ResponseCarDTO();

        responseCarDTO.setId(car.getId());
        responseCarDTO.setBrand(car.getBrand());
        responseCarDTO.setModel(car.getModel());
        responseCarDTO.setYear(car.getYear());
        responseCarDTO.setMileage(car.getMileage());
        responseCarDTO.setGeneration(car.getGeneration());
        responseCarDTO.setBodyType(car.getBodyType());
        responseCarDTO.setVin(car.getVin());

        if (car.getClearedCustoms().equals(true)) {
            responseCarDTO.setClearedCustoms(ClearedCustoms.YES);
        } else {
            responseCarDTO.setClearedCustoms(ClearedCustoms.NO);
        }

        return responseCarDTO;
    }

    public static Car updateCar(Car car, UpdateCarDTO updateCarDTO) {

        if (updateCarDTO.getBrand() != null && !updateCarDTO.getBrand().isBlank()) {
            car.setBrand(updateCarDTO.getBrand());
        }
        if (updateCarDTO.getModel() != null && !updateCarDTO.getModel().isBlank()) {
            car.setModel(updateCarDTO.getModel());
        }
        if (updateCarDTO.getYear() != null) {
            car.setYear(updateCarDTO.getYear());
        }
        if (updateCarDTO.getMileage() != null) {
            car.setMileage(updateCarDTO.getMileage());
        }
        if (updateCarDTO.getGeneration() != null && !updateCarDTO.getGeneration().isBlank()) {
            car.setGeneration(updateCarDTO.getGeneration());
        }
        if (updateCarDTO.getBodyType() != null && !updateCarDTO.getBodyType().isBlank()) {
            car.setBodyType(updateCarDTO.getBodyType());
        }
        if (updateCarDTO.getClearedCustoms() != null && !updateCarDTO.getClearedCustoms().isBlank()) {
            if (updateCarDTO.getClearedCustoms().equals("YES")) {
                car.setClearedCustoms(true);
            } else{
                car.setClearedCustoms(false);
            }
        }
        return car;
    }
}
