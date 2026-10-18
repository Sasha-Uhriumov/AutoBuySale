package com.sasha.autobuysale.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.Year;

@Setter
@Getter
public class CarSearchDTO {

    private String brand;
    private String model;
    private Year year;
    private Integer mileage;
    private String generations;
    private String bodyType;
    private String vin;
    private String clearedCustoms;
}
