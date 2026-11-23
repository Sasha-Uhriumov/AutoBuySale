package com.sasha.autobuysale.dto;

import com.sasha.autobuysale.enums.ClearedCustoms;
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
    private ClearedCustoms clearedCustoms;
}
