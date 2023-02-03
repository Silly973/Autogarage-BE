package com.example.autogaragebe.model;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum AppointmentStatus {
    CUSTOMER_APPROVED,
    CUSTOMER_DISAPPROVED,
    APPOINTMENT_SCHEDULED,
    REPAIR_COMPLETED;

    @JsonCreator
    public static AppointmentStatus create(String value) {
        if(value == null) {
            return null;
        }
        for(AppointmentStatus v : values()) {
            if(value.equals(v.name())) {
                return v;
            }
        }
        return null;
    }

}
