package com.example.autogaragebe.dto;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;


@Getter
@Setter
public class CarDto {

    @NotNull
    private String LicensePlate;
    private Long id;
    private String brand;
    private String model;
    private String constructionYear;

    public CarDto(String licensePlate, Long id, String brand, String model, String constructionYear) {
        LicensePlate = licensePlate;
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.constructionYear = constructionYear;
    }



    public CarDto() {

    }
}
