package com.sasha.autobuysale.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.Year;

@Setter
@Getter
public class UpdateCarDTO {

    private String brand;
    private String model;
    private Year year;
    private Integer mileage;
    private String generation;
    private String bodyType;
    private String clearedCustoms;
}
