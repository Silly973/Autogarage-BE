package com.example.autogaragebe.repository;


import com.example.autogaragebe.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

    Invoice findAllByLicensePlateContainingIgnoreCase(String licensePlate);
    Invoice findAllById(Long id);


}

