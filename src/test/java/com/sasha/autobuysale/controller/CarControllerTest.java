package com.sasha.autobuysale.controller;

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
}