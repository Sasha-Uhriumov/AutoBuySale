package com.sasha.autobuysale.controller;

import com.sasha.autobuysale.dto.CarSearchDTO;
import com.sasha.autobuysale.dto.CreateCarDTO;
import com.sasha.autobuysale.dto.ResponseCarDTO;
import com.sasha.autobuysale.dto.UpdateCarDTO;
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

import static org.mockito.Mockito.*;

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

    @Test
    void updateCar_shouldReturnUpdatedCar() {
        String vin = "jgreigjfierfjioer";

        UpdateCarDTO updateCarDTO = new UpdateCarDTO();
        updateCarDTO.setBrand("BMW");

        ResponseCarDTO responseCarDTO = new ResponseCarDTO();
        responseCarDTO.setBrand("BMW");

        when(carService.updateCar(vin, updateCarDTO)).thenReturn(responseCarDTO);

        ResponseEntity<ResponseCarDTO> response = carController.updateCar(vin, updateCarDTO);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(responseCarDTO, response.getBody());

        verify(carService, times(1)).updateCar(vin, updateCarDTO);
    }

    @Test
    void deleteCar_shouldDeleteCarWhenCarExists() {
        String vin = "jgreigjfierfjioer";

        doNothing().when(carService).deleteCarByVin(vin);

        ResponseEntity<String> response = carController.deleteCarByVin(vin);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals("Car Successfully deleted with vin: " + vin, response.getBody());

        verify(carService, times(1)).deleteCarByVin(vin);
    }
}