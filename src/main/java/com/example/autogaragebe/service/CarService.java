package com.example.autogaragebe.service;

import com.example.autogaragebe.dto.CarDto;
import com.example.autogaragebe.exeption.BadRequestException;
import com.example.autogaragebe.exeption.RecordNotFoundException;
import com.example.autogaragebe.model.Car;
import com.example.autogaragebe.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class CarService {


    @Autowired
    private CarRepository carRepository;


    public Car getCar(Long id) {
        Optional<Car> optionalCar = carRepository.findById(id);

        if (optionalCar.isPresent()) {
            return optionalCar.get();
        } else {
            throw new RecordNotFoundException("No car found with ID " + id );
        }
    }


    public Car getCarsByLicensePlate(String licensePlate) {

        return carRepository.findAllByLicensePlateContainingIgnoreCase(licensePlate);

    }


    public Iterable<Car> getAllCars() {

        return carRepository.findAll();

    }



          public Long createCar(CarDto carDto){
              String licensePlate = carDto.getLicensePlate();
              String constructionYear = carDto.getConstructionYear();
              String brand = carDto.getBrand();
              String model = carDto.getModel();
              List<Car> cars = new ArrayList<>();

              cars.add(carRepository.findAllByLicensePlateContainingIgnoreCase(licensePlate));

              if (cars.size() != 1) {
                  throw new BadRequestException("Licenseplate already exists!!");
              }

              Car car = new Car();
              car.setLicensePlate(carDto.getLicensePlate());
              car.setBrand(carDto.getBrand());
              car.setModel(carDto.getModel());
              car.setConstructionYear(carDto.getConstructionYear());

              Car newCar = carRepository.save(car);
              return newCar.getId();
          }




    public void updateCar(Long id, Car car){
        Car optionalCar = carRepository.findById(id).orElse(null);

        if (!car.getLicensePlate().isEmpty()){
            optionalCar.setLicensePlate(car.getLicensePlate());
        }
        if (!car.getBrand().isEmpty()){
            optionalCar.setBrand(car.getBrand());
        }
        if (!car.getModel().isEmpty()){
            optionalCar.setModel(car.getModel());
        }
        if (!car.getConstructionYear().isEmpty()){
            optionalCar.setConstructionYear(car.getConstructionYear());
        }
        carRepository.save(optionalCar);
    }


    public void partialUpdateCar(Long id, Car car){
        Car optionalCar = carRepository.findById(id).orElse(null);
        if (!(car.getLicensePlate()==null) && !car.getLicensePlate().isEmpty()){
            optionalCar.setLicensePlate(car.getLicensePlate());
        }
        carRepository.save(optionalCar);
    }


    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }


}
