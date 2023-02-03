package com.example.autogaragebe.service;


import com.example.autogaragebe.dto.CustomerDto;
import com.example.autogaragebe.exeption.RecordNotFoundException;
import com.example.autogaragebe.model.Car;
import com.example.autogaragebe.model.Customer;
import com.example.autogaragebe.repository.CarRepository;
import com.example.autogaragebe.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CarRepository carRepository;

    //Get
    public Customer getCustomer(Long id){
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if(optionalCustomer.isPresent()){
            return optionalCustomer.get();
        }else{
            throw new RecordNotFoundException("No customer found with id: " + id);
        }
    }

    public Iterable<Customer>getAllCustomers(){
        return customerRepository.findAll();
    }

    public Customer getCustomerByLastName(String lastName){
        return customerRepository.findByLastNameContainingIgnoreCase(lastName);
    }


    //Post
    public Long createCustomer(CustomerDto customerDto){
        Customer customer = new Customer();
        customer.setFirstName(customerDto.getFirstName());
        customer.setLastName(customerDto.getLastName());
        customer.setPhoneNumber(customerDto.getPhoneNumber());
        customer.setEmail(customerDto.getEmail());

        Customer newCustomer = customerRepository.save(customer);
        return newCustomer.getId();
    }

    //Put
    public void updateCustomer(Long id, Customer customer){
        Customer optionalCustomer = customerRepository.findById(id).orElse(null);

        if (!customer.getFirstName().isEmpty()){
            optionalCustomer.setFirstName(customer.getFirstName());
        }
        if (!customer.getLastName().isEmpty()){
            optionalCustomer.setLastName(customer.getLastName());
        }
        if (!customer.getPhoneNumber().isEmpty()){
            optionalCustomer.setPhoneNumber(customer.getPhoneNumber());
        }
        if (!customer.getEmail().isEmpty()){
            optionalCustomer.setEmail(customer.getEmail());
        }
        customerRepository.save(optionalCustomer);

    }

    public void partialUpdateCustomer(Long id, Customer customer){
        Customer optionalCustomer = customerRepository.findById(id).orElse(null);

        if (!(customer.getFirstName() == null) && !customer.getFirstName().isEmpty()){
            optionalCustomer.setFirstName(customer.getFirstName());
        }
        if (!(customer.getLastName()==null) && !customer.getLastName().isEmpty()){
            optionalCustomer.setLastName(customer.getLastName());
        }
        customerRepository.save(optionalCustomer);
    }

    //Delete
    public void deleteCustomer(Long id){
        customerRepository.deleteById(id);
    }

    //Get customersCar
    public Iterable<Car> getCustomersCar(Long id){
        Optional<Customer>optionalCustomer = customerRepository.findById(id);

        if(optionalCustomer.isPresent()){
            Customer customer = optionalCustomer.get();
            return customer.getCars();
        } else{
            throw new RecordNotFoundException("No customer found with id: " + id);
        }
    }

    //Add customersCar
    public void addCustomersCar(Long id , Car car){
        Optional<Customer>optionalCustomer = customerRepository.findById(id);
        if(optionalCustomer.isPresent()){
            Customer customer = optionalCustomer.get();
            List<Car> cars = customer.getCars();
            car.setOwner(customer);
            carRepository.save(car);
            cars.add(car);
            customerRepository.save(customer);
        } else{
            throw new RecordNotFoundException("No customer found with id: " + id);
        }

    }
}
