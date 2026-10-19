package com.sasha.autobuysale.specification;

import com.sasha.autobuysale.entity.Car;
import org.springframework.data.jpa.domain.Specification;

import java.time.Year;

public class CarSpecification {

    public static Specification<Car> hasBrand(String brand) {
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("brand"), brand));
    }

    public static Specification<Car> hasModel(String model) {
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("model"), model));
    }

    public static Specification<Car> hasYear(Year year) {
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("year"), year));
    }

    public static Specification<Car> hasMileage(Integer mileage) {
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("mileage"), mileage));
    }

    public static Specification<Car> hasGenerations(String generations) {
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("generations"), generations));
    }

    public static Specification<Car> hasBodyType(String bodyType) {
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("bodyType"), bodyType));
    }

    public static Specification<Car> hasVin(String vin) {
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("vin"), vin));
    }

    public static Specification<Car> hasClearedCustoms(String clearedCustoms) {
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("clearedCustoms"), clearedCustoms));
    }
}
