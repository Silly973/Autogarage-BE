package com.example.autogaragebe.repository;

import com.example.autogaragebe.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    Car findAllByLicensePlateContainingIgnoreCase(String licensePlate);


}
