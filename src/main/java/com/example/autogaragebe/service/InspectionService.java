package com.example.autogaragebe.service;


import com.example.autogaragebe.dto.InspectionDto;
import com.example.autogaragebe.exeption.RecordNotFoundException;
import com.example.autogaragebe.model.Inspection;
import com.example.autogaragebe.repository.InspectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;


@Service
public class InspectionService {

    @Autowired
    private InspectionRepository inspectionRepository;

    public Inspection getInspection(Long id){
        Optional<Inspection> optionalInspection = inspectionRepository.findById(id);

        if(optionalInspection.isPresent()) {
            return optionalInspection.get();
        } else {
            throw new RecordNotFoundException("No Inspection found with id:  " + id);
        }
    }

    public Iterable<Inspection>getAllInspections(LocalDate appointmentdate) {
        if (appointmentdate == null) {

            return inspectionRepository.findAll();

        } else {

            Iterable<Inspection> inspections = inspectionRepository.findAllByAppointmentDate(appointmentdate);

            int counter = 0;
            for (Object i : inspections) {
                counter++;
            }

            if (counter == 0) {

                throw new RecordNotFoundException("No inspections found for this date");

            } else {

                return inspections;
            }

        }
    }

    public Long createInspection(InspectionDto inspectionDto){
        Inspection inspection  = new Inspection();
        inspection.setAppointmentDate(inspectionDto.getAppointmentDate());
        inspection.setAppointmentStatus(inspectionDto.getAppointmentStatus());
        Inspection newInspection = inspectionRepository.save(inspection);

        return (long) newInspection.getId();

    }

    public void updateInspection(Long id, InspectionDto inspectionDto){
        Optional<Inspection>optionalInspection = inspectionRepository.findById(id);

        if(optionalInspection.isPresent()){
            Inspection inspection  = optionalInspection.get();
            inspection.setAppointmentDate(inspectionDto.getAppointmentDate());
            inspection.setAppointmentStatus(inspectionDto.getAppointmentStatus());
            inspectionRepository.save(inspection);

        }else {
            throw new RecordNotFoundException("No Inspection found with id:  " + id);
        }
    }
    public void partialUpdateInspection(Long id, InspectionDto inspectionDto){
        Optional<Inspection>optionalInspection = inspectionRepository.findById(id);

        if(optionalInspection.isPresent()){
            Inspection inspection = optionalInspection.get();

            if (inspectionDto.getAppointmentDate() !=null){
                inspection.setAppointmentDate(inspectionDto.getAppointmentDate());
            }
            if (inspectionDto.getAppointmentStatus() !=null){
                inspection.setAppointmentStatus(inspectionDto.getAppointmentStatus());
            }
            inspectionRepository.save(inspection);

        } else {
            throw new RecordNotFoundException("No Inspection found with id:  " + id);
        }
    }

    public void deleteInspection(Long id){
        inspectionRepository.deleteById(id);
    }

}
