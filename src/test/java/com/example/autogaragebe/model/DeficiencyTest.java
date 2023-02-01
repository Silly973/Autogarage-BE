package com.example.autogaragebe.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DeficiencyTest {

    Deficiency deficiency;

    @BeforeEach
    public void setUp(){
        deficiency = new Deficiency();
        this.deficiency.setName("Remmen schoonmaken");
    }



    @Test
    public void getDeficiencyNameTest(){
        String expectedName = "Remmen schoonmaken";
        String actualName = this.deficiency.getName();
        assertEquals(expectedName,actualName);
    }

}