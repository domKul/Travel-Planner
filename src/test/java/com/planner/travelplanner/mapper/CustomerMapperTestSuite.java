package com.planner.travelplanner.mapper;

import com.planner.travelplanner.domain.Customer;
import com.planner.travelplanner.domain.dto.customer.CustomerDTO;
import com.planner.travelplanner.domain.dto.customer.CustomerDTOGet;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CustomerMapperTestSuite {

    @Autowired
    private CustomerMapper customerMapper;

    private Customer customer;

    private CustomerDTO customerDTO;

    public void customerTestData(){
        customer = new Customer(1, "firstName", "lastName", new Date(2020,02,02), "string","string", "string", "string", "string", 1231231, new ArrayList<>(), null);
        customerDTO = new CustomerDTO( "firstNameDTO", "lastName", new Date(2020,02,02), "string","string", "string", "string", "string", 1231231);
    }

    @Test
    public void shouldMapCustomerToCustomerDTO(){
        //Given
        customerTestData();

        //When
        CustomerDTO mappingToDTO = customerMapper.mapToCustomerDTO(customer);

        //Then
        assertEquals(customer.getFirstName(),mappingToDTO.getFirstName());
        assertEquals(customer.getLastName(),mappingToDTO.getLastName());
        assertEquals(customer.getCity(),mappingToDTO.getCity());
    }

    @Test
    public void shouldMapFromCustomerDTOToCustomer(){
        //Given
        customerTestData();

        //When
        Customer mapCustomerFromDTO = customerMapper.mapToCustomer(customerDTO);

        //Then
        assertEquals(customerDTO.getFirstName(),mapCustomerFromDTO.getFirstName());
        assertEquals(customerDTO.getLastName(),mapCustomerFromDTO.getLastName());
        assertEquals(customerDTO.getFirstName(),mapCustomerFromDTO.getFirstName());

    }
    @Test
    public void shouldMapToCustomerForUpdate(){
        //Given
        customerTestData();

        //When
        Customer maptForUpdate = customerMapper.mapToCustomerForUpdate(IdType.EMPTY_ID.getId(), customerDTO);

        //Then
        assertEquals(customerDTO.getFirstName(),maptForUpdate.getFirstName());
        assertEquals(customerDTO.getBirthdate(),maptForUpdate.getBirthdate());
    }

    @Test
    public void shouldMapToCustomerDTOGet(){
        //Given
        customerTestData();

        //When
        CustomerDTOGet mapToCustomerDTOGet = customerMapper.mapToCustomerDTOGet(customer);

        //Then
        assertEquals(customer.getCustomerId(),mapToCustomerDTOGet.getCustomerId());
        assertEquals(customer.getPhoneNumber(),mapToCustomerDTOGet.getPhoneNumber());
        assertEquals(customer.getEmail(),mapToCustomerDTOGet.getEmail());
    }


}
