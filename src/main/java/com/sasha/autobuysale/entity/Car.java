package com.sasha.autobuysale.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Year;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String brand;
    private String model;
    private Year year;
    private Integer mileage;
    private String generation;
    private String bodyType;

    //engine and transmission
    private String engineType;
    private Double engineVolume;
    private Integer power;
    private String transmission;
    private String driveType;

    //appearance and condition
    private String color;
    private String condition;
    private Integer ownerCount;
    private String steeringWheel;

    //legal information
    private String vin;
    private String isClearedCustoms;
}
