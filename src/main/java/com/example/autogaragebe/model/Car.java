package com.example.autogaragebe.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@Entity
@Table(name = "cars")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String brand;
    private String model;
    private String constructionYear;
    private String licensePlate;


    @JsonIgnoreProperties("cars)")
    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer owner;

   @JsonIgnore
   @OneToMany(mappedBy = "scheduledCar", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
   private List<Inspection> inspections = new ArrayList<>();

   @JsonIgnore
   @OneToMany(mappedBy = "scheduledCar", cascade = CascadeType.ALL, orphanRemoval = true)
   @LazyCollection(LazyCollectionOption.FALSE)
   private List<Repair> repairs = new ArrayList<>();


    public Car(){

    }

    public Customer getOwner(){
        return owner;
    }



    public Car(String licensePlate) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.constructionYear = constructionYear;
        this.licensePlate = licensePlate;

    }





}
