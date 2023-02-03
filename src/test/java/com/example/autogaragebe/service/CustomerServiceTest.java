package com.example.autogaragebe.service;


import com.example.autogaragebe.dto.CustomerDto;
import com.example.autogaragebe.exeption.RecordNotFoundException;
import com.example.autogaragebe.model.Customer;
import com.example.autogaragebe.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {


    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    @Test
    public void getCustomerCorrect(){
        //arrange
        Customer expected = new Customer();
        expected.setId(1L);

        when(customerRepository.findById(any(Long.class))).thenReturn(Optional.of((expected)));

        //act
       Customer result = customerService.getCustomer(1L);

        //assert
        assertEquals(expected, result);

    }

    @Test
    public void getCustomerByLastNameCorrect(){
        Customer expected = new Customer();
        expected.setLastName("van Lingen");
        when(customerRepository.findByLastNameContainingIgnoreCase(any(String.class))).thenReturn(expected);

        Customer result = customerService.getCustomerByLastName("van lingen");

        assertEquals(expected, result);
    }

    @Test
    public void getAllCustomersCorrect(){
        List<Customer> customer = new ArrayList<>();
        Customer customer1 = new Customer();
        Customer customer2 = new Customer();
        customer1.setFirstName("Silvia");
        customer1.setLastName("van Lingen");
        customer1.setPhoneNumber("0612345678");
        customer1.setEmail("email@gmail.com");
        customer2.setFirstName("Nikki");
        customer2.setLastName("de Vrij");
        customer2.setPhoneNumber("0645678977");
        customer2.setEmail("deVrij@gmail.com");

        when(customerRepository.findAll()).thenReturn(customer);

        Iterable<Customer> result = customerService.getAllCustomers();

        assertIterableEquals(customer,result);
    }

    @Test
    public void createCustomerCorrect() {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setFirstName("van den Berg");
        customerDto.setLastName("John");
       customerDto.setPhoneNumber("061122334455");
       customerDto.setEmail("Berg@Gmail.com");

        Customer customer = new Customer();
        customer.setId(1L);
        customer.setFirstName(customerDto.getFirstName());
        customer.setLastName(customerDto.getLastName());
        customer.setPhoneNumber(customerDto.getPhoneNumber());
        customer.setEmail(customerDto.getEmail());

        when(customerRepository.save(any(Customer.class))).thenReturn(customer);

        Long result = customerService.createCustomer(customerDto);

        assertEquals(1L, result);
    }

    @Test
    public void DeleteCustomerCorrect() {
        Long id = 1L;

        customerService.deleteCustomer(id);

        verify(customerRepository, times(1)).deleteById(id);

    }


    // Testing the something went wrong!/////
    @Test
    public void getCustomerNotFound(){
        when(customerRepository.findById(any(Long.class))).thenReturn(Optional.empty());

        try{
            customerService.getCustomer(1L);
        } catch (RecordNotFoundException e){
            assertEquals("No customer found with id: 1",e.getMessage());
        }

    }

    @Test
    public void getCustomerByLastNameNotFound(){
        when(customerRepository.findByLastNameContainingIgnoreCase("van den Berg"))
                .thenReturn(null);

            Customer customer = customerService.getCustomerByLastName("van den Berg");

            assertNull(customer);
        }

    }
