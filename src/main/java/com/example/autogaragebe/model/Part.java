package com.example.autogaragebe.model;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name="parts")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Part {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double price;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "repair_id", referencedColumnName = "id")
    private Repair part;

    public Part(){
    }


    public Part(Long id, String name, Double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

}
