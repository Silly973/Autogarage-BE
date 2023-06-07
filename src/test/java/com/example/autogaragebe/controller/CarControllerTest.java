package com.example.autogaragebe.controller;

import com.example.autogaragebe.model.Car;
import com.example.autogaragebe.security.JwtUtil;
import com.example.autogaragebe.service.CarService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(CarController.class)
@AutoConfigureMockMvc(addFilters = false)
@WithMockUser(username = "testuser", roles = "USER")
@ActiveProfiles("test")
class CarControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JwtUtil jwtUtil;

    @MockBean
    private CarService carService;


    @Test
    public void getCarByIdTest() throws Exception {
        Car car = new Car();
        car.setId(2L);
        car.setLicensePlate("288XX8");

        given(carService.getCar(2L)).willReturn(car);

        mockMvc.perform(get("/cars/2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(2)))
                .andExpect(jsonPath("$.licensePlate", is("288XX8")));
    }

    @Test
    public void getCarsByLicensePlateTest( )throws Exception{
        Car car = new Car();
        car.setLicensePlate("288XX8");
        List<Car> allCars = Arrays.asList(car);

        given(carService.getCarsByLicensePlate("288XX8")).willReturn(car);

        mockMvc.perform(get("/cars/licenseplate/288XX8")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.licensePlate", is("288XX8")));


    }

    @Test
    public void getAllCarsTest() throws Exception {

        Car car = new Car();
        car.setLicensePlate("G707NX");
        List<Car> allCars = Arrays.asList(car);

        given(carService.getAllCars()).willReturn(allCars);

        mockMvc.perform(get("/cars")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].licensePlate", is("G707NX")));

    }

    @Test
    public void deleteCarTest() throws Exception {
        Car car = new Car();
        car.setId(2L);
        List<Car> allCars = Arrays.asList(car);

        doNothing().when(carService).deleteCar(2L);

        mockMvc.perform(delete("/cars/2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(carService, times(1)).deleteCar(2L);

    }



}