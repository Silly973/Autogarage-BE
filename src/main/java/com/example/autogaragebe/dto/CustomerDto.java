package com.example.autogaragebe.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;

@Getter
@Setter
public class CustomerDto {


    private String firstName;
    private String lastName;

    @Length(min= 10, max= 12)
    private String phoneNumber;
    @Email
    private String email;

    public CustomerDto(Long id, String firstName, String lastName, String phoneNumber, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public CustomerDto() {

    }
}
