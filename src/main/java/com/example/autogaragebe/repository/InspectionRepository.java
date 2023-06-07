package com.example.autogaragebe.repository;

import com.example.autogaragebe.model.Inspection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface InspectionRepository extends JpaRepository<Inspection, Long> {

    Iterable<Inspection> findAllByAppointmentDate(LocalDate appointmentDate);

}
