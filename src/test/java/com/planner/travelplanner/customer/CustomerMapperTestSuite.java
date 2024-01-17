package com.planner.travelplanner.customer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
class CustomerMapperTestSuite {

    @Autowired
    private CustomerMapper customerMapper;
    private Customer customer;
    private Customer customer2;
    private CustomerDTO customerDTO;

    public void customerTestData() {
        customer = new Customer(1, "firstName", "lastName", new Date(2020, 02, 02), "string", "string", "string", "string", "string", 1231231, new ArrayList<>());
        customer2 = new Customer(1, "firstName", "lastName", new Date(2020, 02, 02), "string", "string", "string", "string", "string", 1231231, new ArrayList<>());
        customerDTO = new CustomerDTO("firstNameDTO", "lastName", new Date(2020, 02, 02), "string", "string", "string", "string", "string", 1231231);
    }

    @Test
    public void shouldMapCustomerToCustomerDTO() {
        //Given
        customerTestData();
        //When
        CustomerDTO mappingToDTO1 = customerMapper.mapToCustomerDTO(customer);
        CustomerDTO mappingToDTO2 = customerMapper.mapToCustomerDTO(customer2);
        //Then
        assertNotEquals(mappingToDTO1, mappingToDTO2);
        assertEquals(customer.getFirstName(), mappingToDTO1.getFirstName());
        assertEquals(customer.getLastName(), mappingToDTO1.getLastName());
        assertEquals(customer.getCity(), mappingToDTO1.getCity());
    }

    @Test
    public void shouldMapFromCustomerDTOToCustomer() {
        //Given
        customerTestData();
        //When
        Customer mapCustomerFromDTO = customerMapper.mapToCustomer(customerDTO);
        //Then
        assertEquals(customerDTO.getFirstName(), mapCustomerFromDTO.getFirstName());
        assertEquals(customerDTO.getLastName(), mapCustomerFromDTO.getLastName());
        assertEquals(customerDTO.getFirstName(), mapCustomerFromDTO.getFirstName());
    }

    @Test
    public void shouldMapToCustomerForUpdate() {
        //Given
        customerTestData();
        //When
        Customer mapForUpdate = customerMapper.mapToCustomerForUpdate(customer.getCustomerId(), customerDTO);
        //Then
        assertEquals(customerDTO.getFirstName(), mapForUpdate.getFirstName());
        assertEquals(customerDTO.getBirthdate(), mapForUpdate.getBirthdate());
    }

    @Test
    public void shouldMapToCustomerDTOGet() {
        //Given
        customerTestData();
        //When
        CustomerDTOGet mapToCustomerDTOGet = customerMapper.mapToCustomerDTOGet(customer);
        //Then
        assertEquals(customer.getCustomerId(), mapToCustomerDTOGet.customerId());
        assertEquals(customer.getPhoneNumber(), mapToCustomerDTOGet.phoneNumber());
        assertEquals(customer.getEmail(), mapToCustomerDTOGet.email());
    }

    @Test
    public void shouldMapListOfCustomerToDTO() {
        //Given
        customerTestData();
        //When
        List<Customer> customersList = new ArrayList<>();
        customersList.add(customer);
        customersList.add(customer2);
        List<CustomerDTOGet> mapListToDTO = customerMapper.mapToDTOList(customersList);
        //Then
        assertEquals(customer.getCustomerId(), mapListToDTO.get(0).customerId());
        assertEquals(customersList.size(), mapListToDTO.size());
        assertEquals(customer.getComplaints(), mapListToDTO.get(0).complaints());
        assertEquals(customer.getCity(), mapListToDTO.get(0).city());
        assertEquals(customer2.getCustomerId(), mapListToDTO.get(1).customerId());
    }
}
