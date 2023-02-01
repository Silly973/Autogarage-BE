package com.example.autogaragebe.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CustomerTest {

    Customer customer;


    @BeforeEach
    void setUp(){
        customer = new Customer();
        this.customer.setFirstName("Silvia");
        this.customer.setLastName("van Lingen");
        this.customer.setPhoneNumber("0612345678");
        this.customer.setEmail("email@gmail.com");
    }


    @Test
    public void getFirstNameTest(){
        String expectedFirstname = "Silvia";
        String actualFirstName = this.customer.getFirstName();
        assertEquals(expectedFirstname,actualFirstName);
    }

    @Test
    public void getLasttNameTest(){
        String expectedLasttname = "van Lingen";
        String actualLastName = this.customer.getLastName();
        assertEquals(expectedLasttname,actualLastName);
    }
}