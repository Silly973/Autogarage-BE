package com.example.autogaragebe.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CarTest {

    Car car;

    @BeforeEach
    void setUp(){
        car = new Car();
        this.car.setLicensePlate("288XX8");
        this.car.setBrand("Volkswagen");
    }

    @Test
    public void getLicensePlateTest(){
        String expectLicensePlate = "288XX8";
        String actualLicensePlate = this.car.getLicensePlate();
        assertEquals(expectLicensePlate,actualLicensePlate);
    }

    @Test
    public void getBrandCarTest(){
        String expectBrand = "Volkswagen";
        String actualBrand = this.car.getBrand();
        assertEquals(expectBrand,actualBrand);
    }


}