package com.example.autogaragebe.controller;

import com.example.autogaragebe.dto.CarDto;
import com.example.autogaragebe.model.Car;
import com.example.autogaragebe.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;


@RestController
@RequestMapping(value= "cars")
public class CarController {

    @Autowired
    private CarService carService;


    //Get
    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> getCar(@PathVariable Long id) {
        return ResponseEntity.ok(carService.getCar(id));
    }


    @GetMapping("/licenseplate/{licenseplate}")
    public ResponseEntity<Car> getCarsByLincensePlate(@PathVariable String licenseplate){
        return ResponseEntity.ok(carService.getCarsByLicensePlate(licenseplate));
    }


    @GetMapping(value = "")
    public ResponseEntity<Object> getAllCars(@RequestParam(required = false) String licensePlate) {

        if (licensePlate == null || licensePlate.isEmpty()) {

            return ResponseEntity.ok(carService.getAllCars());

        } else {

            return ResponseEntity.ok(carService.getCarsByLicensePlate(licensePlate));

        }
    }


    @PostMapping(value = "")
    public ResponseEntity<Object>createCar(@Valid @RequestBody CarDto carDto){

        Long newId = carService.createCar(carDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/id").buildAndExpand(newId).toUri();

        return ResponseEntity.created(uri).build();
    }


    //Put
    @PutMapping(value="/{id}")
    public ResponseEntity<Object> updateCar(@PathVariable Long id, @RequestBody Car car){
        carService.updateCar(id, car);
        return ResponseEntity.noContent().build();
    }
    //ged. update
    @PatchMapping(value = "/{id}")
    public ResponseEntity<Object> partialupdateCar(@PathVariable Long id, @RequestBody Car car) {

        carService.partialUpdateCar(id, car);
        return ResponseEntity.noContent().build();
    }


    //Delete
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteCar(@PathVariable Long id) {

        carService.deleteCar(id);
        return ResponseEntity.noContent().build();
    }


}
