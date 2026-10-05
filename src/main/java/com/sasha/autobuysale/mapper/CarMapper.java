package com.sasha.autobuysale.mapper;

import com.sasha.autobuysale.dto.CreateCarDTO;
import com.sasha.autobuysale.dto.ResponseCarDTO;
import com.sasha.autobuysale.entity.Car;

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
        car.setClearedCustoms(createCarDTO.getClearedCustoms());

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
        responseCarDTO.setClearedCustoms(car.getClearedCustoms());

        return responseCarDTO;
    }

    public static Car updateCarDTO(Car car, CreateCarDTO createCarDTO) {

        car.setBrand(createCarDTO.getBrand());
        car.setModel(createCarDTO.getModel());
        car.setYear(createCarDTO.getYear());
        car.setMileage(createCarDTO.getMileage());
        car.setGeneration(createCarDTO.getGeneration());
        car.setBodyType(createCarDTO.getBodyType());
        car.setVin(createCarDTO.getVin());
        car.setClearedCustoms(createCarDTO.getClearedCustoms());

        return car;
    }
}
