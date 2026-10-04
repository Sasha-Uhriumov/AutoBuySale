package com.sasha.autobuysale.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "car_listings")
public class CarListing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //TODO images

    private String description;
    private Integer viewsCount;
    private String Status;
    private LocalDate dateCreated;
    private LocalDate dateUpdated;

    //TODO user

}
