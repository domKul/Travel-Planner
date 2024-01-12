package com.planner.travelplanner.customer;

import com.planner.travelplanner.exception.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
class CustomerServiceTestSuite {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private CustomerRepository customerRepository;
    private Customer customer;
    private Customer customer2;
    private CustomerDTO customerDTO1;
    private CustomerDTO customerDTO2;

    public void dataForTests() {
        customer = new Customer(1, "firstName", "lastName", new Date(2020, 02, 02), "string", "string", "string", "string", "string", 1231231, new ArrayList<>());
        customer2 = new Customer(1, "firstName", "lastName", new Date(2020, 02, 02), "string", "string", "string", "string", "string", 1231231, new ArrayList<>() );
        customerDTO1 = new CustomerDTO("firstNameDTO", "lastName", new Date(2020, 02, 02), "string", "string", "string", "string", "string", 1231231);
        customerDTO2 = new CustomerDTO("firstNameDTO", "lastName", new Date(2020, 02, 02), "string", "string", "string", "string", "string", 1231231);
    }

    @BeforeEach
    void clearData() {
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
        assertThrows(NotFoundException.class, () -> {
            customerService.deleteCustomerById(wrongID);
        });
        assertEquals(2, listOfCustomers.size());
    }

    @Test
    public void shouldShowAllCustomers() {
        //Given
        dataForTests();
        Customer saveCustomer1 = customerService.saveCustomer(customerDTO1);
        Customer saveCustomer2 = customerService.saveCustomer(customerDTO2);
        //When
        List<CustomerDTOGet> cusList = customerService.showAllCustomers();
        int expected = 2;
        //Then
        assertEquals(expected, cusList.size());
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
    }

    @Test
    public void shouldThrowExceptionForNonExistingCustomerWhenUpdating() {
        // Given
        long customerId = 1L;
        customerDTO1 = new CustomerDTO("Jane", "lastName", new Date(2020, 02, 02), "string", "string", "string", "string", "string", 1231231);
        // When and Then
        assertThrows(NotFoundException.class, () -> {
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
        assertThrows(NotFoundException.class,
                () -> {
                    customerService.showCustomerGetById(idToFind);
                });
    }
}
