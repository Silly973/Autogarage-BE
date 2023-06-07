package com.example.autogaragebe.dto;


import com.example.autogaragebe.model.AppointmentStatus;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
public class RepairDto {

    @NotNull
    private LocalDate appointmentDate;


    @NotNull
    private AppointmentStatus appointmentStatus;

}
