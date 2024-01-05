package com.planner.travelplanner.service;

import com.planner.travelplanner.domain.Customer;
import com.planner.travelplanner.domain.dto.customer.CustomerDTO;
import com.planner.travelplanner.domain.dto.customer.CustomerDTOGet;
import com.planner.travelplanner.domain.exception.CustomerNotFoundException;
import com.planner.travelplanner.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class CustomerServiceTestSuite {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private CustomerRepository customerRepository;
    private Customer customer;
    private Customer customer2;
    private CustomerDTO customerDTO1;
    private CustomerDTO customerDTO2;

    public void dataForTests() {
        customer = new Customer(1, "firstName", "lastName", new Date(2020, 02, 02), "string", "string", "string", "string", "string", 1231231, new ArrayList<>(), null);
        customer2 = new Customer(1, "firstName", "lastName", new Date(2020, 02, 02), "string", "string", "string", "string", "string", 1231231, new ArrayList<>(), null);
        customerDTO1 = new CustomerDTO("firstNameDTO", "lastName", new Date(2020, 02, 02), "string", "string", "string", "string", "string", 1231231);
        customerDTO2 = new CustomerDTO("firstNameDTO", "lastName", new Date(2020, 02, 02), "string", "string", "string", "string", "string", 1231231);
    }

    private void clearData() {
        customerRepository.deleteAll();
    }

    @BeforeEach
    public void clear() {
        clearData();
    }

    @Test
    public void shouldSaveCustomerToDB() {
        //Given
        dataForTests();
        //When
        Customer saveCustomer1 = customerService.saveCustomer(customerDTO1);
        Customer saveCustomer2 = customerService.saveCustomer(customerDTO2);
        List<Customer> cusList = customerRepository.findAll();
        //Then
        assertEquals(2, cusList.size());
        //CLeanUp
        long getId1 = saveCustomer1.getCustomerId();
        long getId2 = saveCustomer2.getCustomerId();
        customerRepository.deleteById(getId1);
        customerRepository.deleteById(getId2);
    }

    @Test
    public void shouldDeleteCustomerFromDBById() {
        //Given
        dataForTests();
        Customer saveCustomer1 = customerService.saveCustomer(customerDTO1);
        Customer saveCustomer2 = customerService.saveCustomer(customerDTO2);
        //When
        long getId1 = saveCustomer1.getCustomerId();
        long getId2 = saveCustomer2.getCustomerId();
        customerService.deleteCustomerById(getId1);
        customerService.deleteCustomerById(getId2);
        List<Customer> cusList = customerRepository.findAll();
        //Then
        assertEquals(0, cusList.size());
    }

    @Test
    public void shouldThrowExceptionForNonExistingCustomerWhenDeleting() {
        //Given
        dataForTests();
        Customer saveCustomer1 = customerService.saveCustomer(customerDTO1);
        Customer saveCustomer2 = customerService.saveCustomer(customerDTO2);
        List<Customer> listOfCustomers = customerRepository.findAll();
        //When
        long wrongID = 1231231L;
        assertThrows(CustomerNotFoundException.class, () -> {
            customerService.deleteCustomerById(wrongID);
        });
        assertEquals(2, listOfCustomers.size());
        //CleanUp
        long getId1 = saveCustomer1.getCustomerId();
        long getId2 = saveCustomer2.getCustomerId();
        customerRepository.deleteById(getId1);
        customerRepository.deleteById(getId2);
    }

    @Test
    public void shouldShowAllCUstomers() {
        //Given
        dataForTests();
        Customer saveCustomer1 = customerService.saveCustomer(customerDTO1);
        Customer saveCustomer2 = customerService.saveCustomer(customerDTO2);
        //When
        List<CustomerDTOGet> cusList = customerService.showAllCustomers();
        int expected = 2;
        //Then
        assertEquals(expected, cusList.size());
        //CleanUp
        long getId1 = saveCustomer1.getCustomerId();
        long getId2 = saveCustomer2.getCustomerId();
        customerService.deleteCustomerById(getId1);
        customerService.deleteCustomerById(getId2);
    }

    @Test
    public void shouldUpdateExistingCustomer() {
        // Given
        dataForTests();
        Customer savedCustomer = customerRepository.save(customer);
        // When
        long getId = savedCustomer.getCustomerId();
        customerDTO1 = new CustomerDTO("Jane", "lastName", new Date(2020, 02, 02), "string", "string", "string", "string", "string", 1231231);
        customerService.updateCustomer(getId, customerDTO1);
        customerDTO2 = new CustomerDTO("Jane", "Smith", new Date(2020, 02, 02), "string", "string", "string", "string", "string", 1231231);
        CustomerDTO updatedCustomerDTO2 = customerService.updateCustomer(getId, customerDTO2);
        // Then
        assertEquals("Jane", updatedCustomerDTO2.getFirstName());
        assertEquals("Smith", updatedCustomerDTO2.getLastName());
        //CleanUp
        customerRepository.deleteById(getId);
    }

    @Test
    public void shouldThrowExceptionForNonExistingCustomerWhenUpdating() {
        // Given
        long customerId = 1L;
        customerDTO1 = new CustomerDTO("Jane", "lastName", new Date(2020, 02, 02), "string", "string", "string", "string", "string", 1231231);
        // When and Then
        assertThrows(CustomerNotFoundException.class, () -> {
            customerService.updateCustomer(customerId, customerDTO1);
        });
    }

    @Test
    public void shouldShowCustomerByexistingId() {
        //Given
        dataForTests();
        Customer savedCustomer = customerRepository.save(customer);
        // When
        long getId = savedCustomer.getCustomerId();
        CustomerDTOGet findCustomer = customerService.showCustomerGetById(getId);
        //Then
        assertEquals(customer.getFirstName(), findCustomer.getFirstName());
        //CleanUp
        customerRepository.deleteById(getId);
    }

    @Test
    public void shouldThrowExceptionForNonExistingCustomerWhenTryTOFInd() {
        //Given
        long idToFind = 123123L;
        //When &Then
        assertThrows(CustomerNotFoundException.class,
                () -> {
                    customerService.showCustomerGetById(idToFind);
                });
    }
}
