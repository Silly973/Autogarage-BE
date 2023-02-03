package com.example.autogaragebe.service;

import com.example.autogaragebe.dto.CarDto;
import com.example.autogaragebe.exeption.RecordNotFoundException;
import com.example.autogaragebe.model.Car;
import com.example.autogaragebe.repository.CarRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class CarServiceTest {

    @Mock
    private CarRepository carRepository;

    @InjectMocks
    private CarService carService;

    @Test
    public void getCarCorrect(){
        //arrange
        Car expected = new Car();
        expected.setId(1L);

        when(carRepository.findById(any(Long.class))).thenReturn(Optional.of((expected)));

        //act
        Car result = carService.getCar(1L);

        //assert
        assertEquals(expected, result);


    }

    @Test
    public void getCarsByLicensePlateCorrect(){
        Car expected = new Car();
        expected.setLicensePlate("G707NX");
        when(carRepository.findAllByLicensePlateContainingIgnoreCase(any(String.class))).thenReturn(expected);

        Car result = carService.getCarsByLicensePlate("g707nx");

        assertEquals(expected, result);
    }

    @Test
    public void getAllCarsCorrect(){
        List<Car> car = new ArrayList<>();
        Car car1 = new Car();
        Car car2 = new Car();
        car1.setLicensePlate("HNFG47");
        car2.setLicensePlate("GG123G");
        car.add(car1);
        car.add(car2);

        when(carRepository.findAll())
                .thenReturn(car);

        Iterable<Car> result = carService.getAllCars();

        assertIterableEquals(car, result);

    }


    @Test
    public void createCarCorrect() {
        CarDto carDto = new CarDto();
        carDto.setLicensePlate("G707NX");
        carDto.setConstructionYear("2017");
        carDto.setBrand("Ford");
        carDto.setModel("Fiesta");

        Car car = new Car();
        car.setId(1L);
        car.setLicensePlate(carDto.getLicensePlate());
        car.setBrand(carDto.getBrand());
        car.setModel(carDto.getModel());
        car.setConstructionYear(carDto.getConstructionYear());
        when(carRepository.findAllByLicensePlateContainingIgnoreCase(any(String.class))).thenReturn(null);
        when(carRepository.save(any(Car.class))).thenReturn(car);

        Long result = carService.createCar(carDto);

        assertEquals(1L, result);
    }

    @Test
    public void updateCarCorrect(){
        Long id = 1L;
        Car car = new Car();
        car.setLicensePlate("G707NX");
        car.setBrand("Ford");
        car.setModel("Fiesta");
        car.setConstructionYear("2015");

        Car optionalCar = new Car();
        optionalCar.setId(1L);
        optionalCar.setLicensePlate("G707NN");
        optionalCar.setBrand("Volkswagen");
        optionalCar.setModel("Polo");
        optionalCar.setConstructionYear("2017");

        when(carRepository.findById(id)).thenReturn(Optional.of(optionalCar));
        when(carRepository.save(optionalCar)).thenReturn(optionalCar);

        carService.updateCar(id, car);

        verify(carRepository, times(1)).findById(id);
        verify(carRepository, times(1)).save(optionalCar);

        assertEquals("G707NX",optionalCar.getLicensePlate());
        assertEquals("Ford",optionalCar.getBrand());
        assertEquals("Fiesta",optionalCar.getModel());
        assertEquals("2015",optionalCar.getConstructionYear());

    }

    @Test
    public void partialUpdateCarCorrect(){
        Long id = 1L;
        String newLicensePlate = "G808XX";
        Car car = new Car();
        car.setLicensePlate(newLicensePlate);
        Car optionalCar = new Car();
        optionalCar.setId(id);
        optionalCar.setLicensePlate("G707NX");
        when(carRepository.findById(id)).thenReturn(Optional.of(optionalCar));
        when(carRepository.save(optionalCar)).thenReturn(optionalCar);

        carService.partialUpdateCar(id, car);

        verify(carRepository).findById(id);
        verify(carRepository).save(optionalCar);
        assertEquals(newLicensePlate,optionalCar.getLicensePlate());
    }



    @Test
    public void DeleteCarCorrect() {
        Long id = 1L;

        carService.deleteCar(id);

        verify(carRepository, times(1)).deleteById(id);

    }


    // Testing the something went wrong!/////

    @Test
    public void getCarNotFound(){
        when(carRepository.findById(any(Long.class))).thenReturn(Optional.empty());

        try{
            carService.getCar(1L);
        } catch (RecordNotFoundException e){
            assertEquals("No car found with ID 1",e.getMessage());
        }

    }


}



