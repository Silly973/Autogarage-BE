package com.example.autogaragebe.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class PartDto {

    @NotBlank
    private String name;
    @NotNull
    private double price;

    public PartDto(String name, double price) {
        this.name = name;
        this.price = price;
    }
}
