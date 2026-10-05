package com.sasha.autobuysale.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.Year;

@Setter
@Getter
public class CreateCarDTO {

    @NotBlank(message = "Brand must be not blank")
    @Size(min = 1, max = 100, message = "Brand should be 1-100 characters")
    private String brand;

    @NotBlank(message = "Model must be not blank")
    @Size(min = 1, max = 100, message = "Model should be 1-100 characters")
    private String model;

    @NotNull(message = "Year must be not Null")
    private Year year;

    @NotNull(message = "Mileage must be not null")
    private Integer mileage;

    @NotBlank(message = "Generation must be not blank")
    @Size(min = 1, max = 100, message = "Generation should be 1-100 characters")
    private String generation;

    @NotBlank(message = "Body type must be not blank")
    @Size(min = 1, max = 50, message = "Body type should be 1-50 characters")
    private String bodyType;

    @NotBlank(message = "VIN must be not blank")
    @Size(min = 17, max = 20, message = "VIN should be 1-20 characters")
    private String vin;

    @NotBlank(message = "Cleared customs must be not blank")
    @Size(min = 3, max = 5, message = "Cleared customs should be 3-5 characters")
    private String clearedCustoms;

}
