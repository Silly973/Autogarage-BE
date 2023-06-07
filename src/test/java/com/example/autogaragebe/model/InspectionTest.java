package com.example.autogaragebe.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InspectionTest {

    Inspection inspection;


    @BeforeEach
    public void setUp(){
        inspection = new Inspection();
        this.inspection.setAppointmentDate(LocalDate.of(2023,02,21));
        this.inspection.setAppointmentStatus(AppointmentStatus.REPAIR_COMPLETED);
    }

    @Test
    void testGetScheduledCar() {
        Car car = new Car();
        inspection.setScheduledCar(car);
        assertEquals(car, inspection.getScheduledCar());
    }

    @Test
    public void getAppointmentDateTest(){
        LocalDate expectedDate = LocalDate.of(2023,02,21);
        LocalDate actualdate = this.inspection.getAppointmentDate();
        assertEquals(expectedDate,actualdate);
    }

}