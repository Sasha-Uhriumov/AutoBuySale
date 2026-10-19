package com.sasha.autobuysale.service;

import com.sasha.autobuysale.dto.CarSearchDTO;
import com.sasha.autobuysale.dto.CreateCarDTO;
import com.sasha.autobuysale.dto.ResponseCarDTO;
import com.sasha.autobuysale.entity.Car;
import com.sasha.autobuysale.exception.CarExists;
import com.sasha.autobuysale.mapper.CarMapper;
import com.sasha.autobuysale.repository.CarRepository;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.domain.Specification;

import java.time.Year;
import java.util.List;

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

    @Test
    void searchCars_shouldReturnListOfCars() {

        CarSearchDTO carSearchDTO = new CarSearchDTO();

        Car car1 = new Car();
        car1.setBrand("BMW");
        car1.setModel("M4");
        car1.setYear(Year.of(2014));
        car1.setMileage(20000);
        car1.setGeneration("F82");
        car1.setBodyType("Coupe");
        car1.setVin("test1234");
        car1.setClearedCustoms("YES");

        Car car2 = new Car();
        car2.setBrand("BMW");
        car2.setModel("M4");
        car2.setYear(Year.of(2014));
        car2.setMileage(20000);
        car2.setGeneration("F82");
        car2.setBodyType("Coupe");
        car2.setVin("test12345");
        car2.setClearedCustoms("YES");

        when(carRepository.findAll(any(Specification.class))).thenReturn(List.of(car1, car2));

        List<ResponseCarDTO> result = carService.searchCars(carSearchDTO);

        Assertions.assertEquals(2, result.size());

        verify(carRepository).findAll(any(Specification.class));
    }
}