package com.sasha.autobuysale.service;

import com.sasha.autobuysale.dto.CreateCarDTO;
import com.sasha.autobuysale.dto.ResponseCarDTO;
import com.sasha.autobuysale.entity.Car;
import com.sasha.autobuysale.exception.CarExists;
import com.sasha.autobuysale.mapper.CarMapper;
import com.sasha.autobuysale.repository.CarRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Year;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CarServiceTest {

    @Mock
    private CarRepository carRepository;

    @InjectMocks
    private CarService carService;

    @Test
    void createCar() {

        Long id = 1L;

        CreateCarDTO createCarDTO = new CreateCarDTO();

        createCarDTO.setBrand("BMW");
        createCarDTO.setModel("M4");
        createCarDTO.setYear(Year.of(2014));
        createCarDTO.setMileage(20000);
        createCarDTO.setGeneration("F82");
        createCarDTO.setBodyType("Coupe");
        createCarDTO.setVin("rer3e39f9r4kr455efdef");
        createCarDTO.setClearedCustoms("YES");

        Car car = CarMapper.toEntity(createCarDTO);
        car.setId(id);

        when(carRepository.existsByVin("rer3e39f9r4kr455efdef")).thenReturn(false);
        when(carRepository.save(any(Car.class))).thenReturn(car);

        ResponseCarDTO responseCarDTO = carService.createCar(createCarDTO);
        verify(carRepository, times(1)).save(any(Car.class));

        Assertions.assertNotNull(responseCarDTO);
        Assertions.assertEquals(id, responseCarDTO.getId());
        Assertions.assertEquals("BMW", responseCarDTO.getBrand());
        Assertions.assertEquals("M4", responseCarDTO.getModel());
        Assertions.assertEquals(Year.of(2014), responseCarDTO.getYear());
        Assertions.assertEquals(20000, responseCarDTO.getMileage());
        Assertions.assertEquals("F82", responseCarDTO.getGeneration());
        Assertions.assertEquals("Coupe", responseCarDTO.getBodyType());
        Assertions.assertEquals("rer3e39f9r4kr455efdef", responseCarDTO.getVin());
        Assertions.assertEquals("YES", responseCarDTO.getClearedCustoms());
    }
    @Test
    void createCar_shouldThrowCarExists_whenVinAlreadyExists() {

        CreateCarDTO createCarDTO = new CreateCarDTO();
        createCarDTO.setVin("vin1234");

        when(carRepository.existsByVin("vin1234")).thenReturn(true);

        Assertions.assertThrows(
                CarExists.class,
                () -> carService.createCar(createCarDTO)
        );

        verify(carRepository).existsByVin("vin1234");
        verify(carRepository, never()).save(any(Car.class));
    }
}