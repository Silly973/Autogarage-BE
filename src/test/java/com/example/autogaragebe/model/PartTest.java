package com.example.autogaragebe.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PartTest {

    Part part;

    @BeforeEach
    public void setUp(){
        part = new Part();
        this.part.setName("Accu Bosch S4 028");
        this.part.setPrice(110.87);
    }

    @Test
    public void getPartNameTest (){
        String expectedName = "Accu Bosch S4 028";
        String actualName = this.part.getName();
        assertEquals(expectedName,actualName);
    }

    @Test
    public void getPartPriceTest (){
       Double expectedPrice = 110.87;
       Double actualPrice = this.part.getPrice();
        assertEquals(expectedPrice, actualPrice);
    }

}