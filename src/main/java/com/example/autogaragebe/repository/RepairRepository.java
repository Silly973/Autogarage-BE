package com.example.autogaragebe.repository;

import com.example.autogaragebe.model.Repair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;


@Repository
public interface RepairRepository extends JpaRepository<Repair, Long> {

    Iterable<Repair> findAllByAppointmentDate(LocalDate appointmentDate);

}
