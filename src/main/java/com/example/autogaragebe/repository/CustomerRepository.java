package com.example.autogaragebe.repository;


import com.example.autogaragebe.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Customer findByLastNameContainingIgnoreCase(String lastName);

}
