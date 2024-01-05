package com.planner.travelplanner.mapper;

import com.planner.travelplanner.domain.Customer;
import com.planner.travelplanner.domain.dto.customer.CustomerDTO;
import com.planner.travelplanner.domain.dto.customer.CustomerDTOGet;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
public class CustomerMapperTestSuite {

    @Autowired
    private CustomerMapper customerMapper;
    private Customer customer;
    private Customer customer2;
    private CustomerDTO customerDTO;

    public void customerTestData() {
        customer = new Customer(1, "firstName", "lastName", new Date(2020, 02, 02), "string", "string", "string", "string", "string", 1231231, new ArrayList<>(), null);
        customer2 = new Customer(1, "firstName", "lastName", new Date(2020, 02, 02), "string", "string", "string", "string", "string", 1231231, new ArrayList<>(), null);
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
        assertEquals(customer.getCustomerId(), mapToCustomerDTOGet.getCustomerId());
        assertEquals(customer.getPhoneNumber(), mapToCustomerDTOGet.getPhoneNumber());
        assertEquals(customer.getEmail(), mapToCustomerDTOGet.getEmail());
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
        assertEquals(customer.getCustomerId(), mapListToDTO.get(0).getCustomerId());
        assertEquals(customersList.size(), mapListToDTO.size());
        assertEquals(customer.getComplaints(), mapListToDTO.get(0).getComplaints());
        assertEquals(customer.getCity(), mapListToDTO.get(0).getCity());
        assertEquals(customer2.getCustomerId(), mapListToDTO.get(1).getCustomerId());
    }
}
