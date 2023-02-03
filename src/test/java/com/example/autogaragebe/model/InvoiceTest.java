package com.example.autogaragebe.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InvoiceTest {

    Invoice invoice;

    @BeforeEach
    public void setUp() {
        invoice = new Invoice();
        this.invoice.setLicensePlate("288XXN");
    }
    @Test
    public void getLicensePlateTest (){
        String expectedLicensePlate = "288XXN";
        String actualLicensePlate = this.invoice.getLicensePlate();
        assertEquals(expectedLicensePlate,actualLicensePlate);
    }

}