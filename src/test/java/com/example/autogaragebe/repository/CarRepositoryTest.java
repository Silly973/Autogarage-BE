package com.example.autogaragebe.repository;


import com.example.autogaragebe.AutogarageBeApplication;
import com.example.autogaragebe.model.Car;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@ContextConfiguration(classes = {AutogarageBeApplication.class})
@EnableConfigurationProperties
class CarRepositoryTest {

        @Autowired
        private TestEntityManager entityManager;

        @Autowired
        private CarRepository carRepository;



        @Test
        void testFindById() {
            Car car = new Car();
            car.setLicensePlate("HNFG47");
            entityManager.persist(car);
            entityManager.flush();

           Optional<Car> found = carRepository.findById(3L);


            Long expected = 3L;
            Long actual = found.get().getId();

            assertEquals(expected, actual);
        }

        @Test
        void testFindByLicenseplate() {


            //when
            Car found = carRepository.findAllByLicensePlateContainingIgnoreCase("HNFG47");

            //then
            String expected = "hnfg47";
            String actual = found.getLicensePlate().toLowerCase();

            assertEquals(expected, actual);

        }
    }
