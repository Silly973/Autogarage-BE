package com.example.autogaragebe.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "deficiencies")
public class Deficiency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="inspection_id", referencedColumnName = "id")
    private Inspection deficiency;

    public Deficiency(){

    }

    public Deficiency(Long id, String name, Inspection deficiency) {
        this.id = id;
        this.name = name;
        this.deficiency = deficiency;
    }
}
