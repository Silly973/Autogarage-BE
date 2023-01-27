package com.example.autogaragebe.controller;

import com.example.autogaragebe.dto.CustomerDto;
import com.example.autogaragebe.model.Car;
import com.example.autogaragebe.model.Customer;
import com.example.autogaragebe.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value="customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    //Get
    @GetMapping(value="/{id}")
    public ResponseEntity<Object>getCustomer(@PathVariable Long id){
        return ResponseEntity.ok(customerService.getCustomer(id));

    }

    @GetMapping(value = "")
    public ResponseEntity<Object>getCustomers(@RequestParam(required = false)String lastName){

        if (lastName == null || lastName.isEmpty()){
            return ResponseEntity.ok(customerService.getAllCustomers());

        } else {
            return ResponseEntity.ok(customerService.getCustomerByLastName(lastName));
        }
    }


    //Post
    @PostMapping(value = "")
    public ResponseEntity<Object>createCustomer(@Valid @RequestBody CustomerDto customerDto){

        Long newId = customerService.createCustomer(customerDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/id").buildAndExpand(newId).toUri();

        return ResponseEntity.created(uri).build();
    }

    //Put
    @PutMapping(value = "/{id}")
   public ResponseEntity<Object>updateCustomer(@PathVariable Long id, @RequestBody Customer customer){
       customerService.updateCustomer(id, customer);

     return ResponseEntity.noContent().build();



    }
    //Patch
    @PatchMapping(value = "/{id}")
    public ResponseEntity<Object> partialUpdateCustomer(@PathVariable Long id, @RequestBody Customer customer){
        customerService.partialUpdateCustomer(id, customer);

        return ResponseEntity.noContent().build();
    }


    //Delete
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object>deleteCustomer(@PathVariable Long id){
        customerService.deleteCustomer(id);

        return ResponseEntity.noContent().build();
    }

    //customer and car
    @GetMapping(value = "/{id}/cars")
    public ResponseEntity<Object>getCustomersCar(@PathVariable Long id){

        return ResponseEntity.ok(customerService.getCustomersCar(id));
    }

    @PostMapping(value="/{id}/cars")
    public ResponseEntity<Object> addCustomersCar(@PathVariable Long id, @RequestBody Car car){
        customerService.addCustomersCar(id,car);

        return ResponseEntity.created(null).build();
    }

}
