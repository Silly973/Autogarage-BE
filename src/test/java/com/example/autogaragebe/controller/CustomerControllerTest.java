package com.example.autogaragebe.controller;

import com.example.autogaragebe.dto.CustomerDto;
import com.example.autogaragebe.model.Customer;
import com.example.autogaragebe.security.JwtUtil;
import com.example.autogaragebe.service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CustomerController.class)
@AutoConfigureMockMvc(addFilters = false)
@WithMockUser(username = "testuser", roles = "USER")
@ActiveProfiles("test")
class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JwtUtil jwtUtil;


    @MockBean
    private CustomerService customerService;


    @Test
    public void getCustomerByIdTest() throws Exception {
        Customer customer = new Customer();
        customer.setId(2L);
        customer.setFirstName("John");
        customer.setLastName("van de Heuvel");

        given(customerService.getCustomer(2L)).willReturn(customer);

        mockMvc.perform(get("/customers/2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(2)))
                .andExpect(jsonPath("$.lastName", is("van de Heuvel")));
    }


    @Test
    public void getAllCustomersTest() throws Exception {
        Customer customer1 = new Customer();
        customer1.setLastName("van Lingen");
        Customer customer2 = new Customer();
        customer2.setLastName("van de Heuvel");
        List<Customer> allCustomers = Arrays.asList(customer1, customer2);

        given(customerService.getAllCustomers()).willReturn(allCustomers);

        mockMvc.perform(get("/customers")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].lastName", is("van Lingen")))
                .andExpect(jsonPath("$[1].lastName", is("van de Heuvel")));
    }

    @Test
    public void getCustomerByLastNameTest() throws Exception {
        Customer customer = new Customer();
        customer.setFirstName("John");
        customer.setLastName("van de Heuvel");

        given(customerService.getCustomerByLastName("van de Heuvel")).willReturn(customer);

        mockMvc.perform(get("/customers?lastName=van de Heuvel")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.lastName", is("van de Heuvel")));
    }

    @Test
    public void createCustomerTest() throws Exception {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setFirstName("Silvia");
        customerDto.setLastName("van Lingen");
        customerDto.setPhoneNumber("0612345678");
        customerDto.setEmail("email@Gmail.com");

        when(customerService.createCustomer(customerDto)).thenReturn(1L);

        mockMvc.perform(post("/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(customerDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
