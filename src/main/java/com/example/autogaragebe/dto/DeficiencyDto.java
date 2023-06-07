package com.example.autogaragebe.dto;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class DeficiencyDto {

    @NotEmpty
    private String name;


}
