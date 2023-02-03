package com.example.autogaragebe.service;


import com.example.autogaragebe.dto.RepairDto;
import com.example.autogaragebe.exeption.RecordNotFoundException;
import com.example.autogaragebe.model.Repair;
import com.example.autogaragebe.repository.RepairRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;


@Service
public class RepairService {

    @Autowired
    private RepairRepository repairRepository;

    public Repair getRepair(Long id){
        Optional<Repair> optionalRepair = repairRepository.findById(id);

        if(optionalRepair.isPresent()){
            return optionalRepair.get();
        }else{
            throw new RecordNotFoundException("No repair found with this id: " + id);
        }
    }

    public Iterable <Repair> getAllRepairs(LocalDate appointmentDate){

        if(appointmentDate == null) {

            return repairRepository.findAll();

        } else {

            Iterable<Repair> repairs =  repairRepository.findAllByAppointmentDate(appointmentDate);

            int counter = 0;
            for(Object i : repairs) {
                counter++;
            }

            if(counter==0) {

                throw new RecordNotFoundException("There are no repairs found on this date");

            } else {

                return repairs;
            }
        }
    }

    public Long createRepair(RepairDto repairDto){
            Repair repair = new Repair();
            repair.setAppointmentDate(repairDto.getAppointmentDate());
            repair.setAppointmentStatus(repairDto.getAppointmentStatus());
            Repair newRepair = repairRepository.save(repair);
            return newRepair. getId();
        }

    public void updateRepair(Long id, RepairDto repairDto){
            Optional<Repair>optionalRepair = repairRepository.findById(id);

            if(optionalRepair.isPresent()){
                Repair repair = optionalRepair.get();
                repair.setAppointmentDate(repairDto.getAppointmentDate());
                repair.setAppointmentStatus(repairDto.getAppointmentStatus());

                repairRepository.save(repair);

            }else{
                throw new RecordNotFoundException("No repair found with this id: " + id);
            }

        }

    public void partialUpdateRepair(Long id, RepairDto repairDto) {
            Optional<Repair> optionalRepair = repairRepository.findById(id);

            if (optionalRepair.isPresent()){
                Repair repair = optionalRepair.get();
                if(repairDto.getAppointmentDate() != null) {

                    repair.setAppointmentDate(repairDto.getAppointmentDate());

                }

                if(repairDto.getAppointmentStatus() != null) {

                    repair.setAppointmentStatus(repairDto.getAppointmentStatus());

                }

                repairRepository.save(repair);

            }else {
                throw new RecordNotFoundException("No repair found with this id: " + id);
            }
        }


    public void deleteRepair(Long id){
        repairRepository.deleteById(id);{
        }

    }
}
