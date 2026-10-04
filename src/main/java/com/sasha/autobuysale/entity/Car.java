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

    @Column(name = "brand", length = 100, nullable = false)
    private String brand;

    @Column(name = "model", length = 100, nullable = false)
    private String model;

    @Column(name = "year", nullable = false)
    private Year year;

    @Column(name = "mileage", nullable = false)
    private Integer mileage;

    @Column(name = "generation", length = 100, nullable = false)
    private String generation;

    @Column(name = "body_type", length = 50, nullable = false)
    private String bodyType;

    //legal information
    @Column(name = "vin", length = 20, nullable = false)
    private String vin;

    @Column(name = "cleared_customs", length = 5, nullable = false)
    private String clearedCustoms;
}
