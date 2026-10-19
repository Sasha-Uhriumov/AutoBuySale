package com.sasha.autobuysale.controller;

import com.sasha.autobuysale.dto.CarSearchDTO;
import com.sasha.autobuysale.dto.CreateCarDTO;
import com.sasha.autobuysale.dto.ResponseCarDTO;
import com.sasha.autobuysale.service.CarService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CarControllerTest {

    @Mock
    private CarService carService;

    @InjectMocks
    private CarController carController;

    @Test
    void createCar_shouldReturnResponseCarDTO() {

        CreateCarDTO createCarDTO = new CreateCarDTO();
        ResponseCarDTO responseCarDTO = new ResponseCarDTO();

        when(carService.createCar(createCarDTO)).thenReturn(responseCarDTO);

        ResponseEntity<ResponseCarDTO> response = carController.createCar(createCarDTO);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertSame(responseCarDTO, response.getBody());

        verify(carService).createCar(createCarDTO);
    }

    @Test
    void searchCars_shouldReturnCars() {
        CarSearchDTO carSearchDTO = new CarSearchDTO();
        List<ResponseCarDTO> responseCarDTOS = new ArrayList<>();

        CreateCarDTO createCarDTO = new CreateCarDTO();
        createCarDTO.setBrand("BMW");

        when(carService.searchCars(carSearchDTO)).thenReturn(responseCarDTOS);

        ResponseEntity<List<ResponseCarDTO>> response = carController.searchCars(carSearchDTO);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(responseCarDTOS, response.getBody());

        verify(carService).searchCars(carSearchDTO);
    }
}