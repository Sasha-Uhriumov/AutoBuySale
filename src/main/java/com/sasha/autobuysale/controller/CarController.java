package com.sasha.autobuysale.controller;

import com.sasha.autobuysale.dto.CreateCarDTO;
import com.sasha.autobuysale.dto.ResponseCarDTO;
import com.sasha.autobuysale.service.CarService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cars")
@AllArgsConstructor
public class CarController {

    private final CarService carService;

    @PostMapping
    public ResponseEntity<ResponseCarDTO> createCar(@RequestBody CreateCarDTO createCarDTO) {

        ResponseCarDTO responseCarDTO = carService.createCar(createCarDTO);

        return ResponseEntity.ok(responseCarDTO);
    }

    @GetMapping
    public ResponseEntity<List<ResponseCarDTO>> getCars() {

        List<ResponseCarDTO> allCars = carService.getAllCars();

        return ResponseEntity.ok(allCars);
    }
}
