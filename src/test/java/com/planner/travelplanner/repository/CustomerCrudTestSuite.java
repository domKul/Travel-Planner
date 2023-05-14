package com.planner.travelplanner.repository;

import com.planner.travelplanner.domain.Booking;
import com.planner.travelplanner.domain.Complaint;
import com.planner.travelplanner.domain.Customer;
import com.planner.travelplanner.mapper.IdType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CustomerCrudTestSuite {

    @Autowired
    private CustomerRepository customerRepository;

    private Customer customer1 = null;
    private Customer customer2 = null;
    private Booking booking1 = null;
    private Booking booking2 = null;
    private Complaint complaint2 = null;
    private Complaint complaint1 = null;


    private void dataForTests() {
        customer1 = new Customer(IdType.EMPTY_ID.getId(), "firstName1", "lastName1", new Date(2000, 2, 11), "country", "city", "streetName", "postalCode", "email", 1231231, new ArrayList<>(), null);
        customer2 = new Customer(IdType.EMPTY_ID.getId(), "firstName2", "lastName2", new Date(2000, 2, 11), "country", "city", "streetName", "postalCode", "email", 1231231, new ArrayList<>(), null);
        booking1 = new Booking(IdType.EMPTY_ID.getId(), new Date(2020, 12, 12), new Date(2020, 12, 12), null, null);
        booking2 = new Booking(IdType.EMPTY_ID.getId(), new Date(2020, 12, 12), new Date(2020, 12, 12), null, null);
        complaint1 = new Complaint(IdType.EMPTY_ID.getId(), "title1", "description1", LocalDateTime.now(), "status1", null);
        complaint2 = new Complaint(IdType.EMPTY_ID.getId(), "title2", "description2", LocalDateTime.now(), "status2", null);
    }

    @Test
    public void shouldCreateCustomer() {
        //Given
        dataForTests();

        //When
        Customer createCustomer = customerRepository.save(customer1);

        Long getId1 = createCustomer.getCustomerId();

        //Then
        assertEquals(1, customerRepository.count());
        assertNotNull(customer1);
        assertEquals("firstName1", createCustomer.getFirstName());
        assertEquals(customer1.getComplaints().size(), createCustomer.getComplaints().size());

        //CleanUp
        customerRepository.deleteById(getId1);
    }

    @Test
    public void shouldDeleteCustomerById() {

        //Given
        dataForTests();

        //When
        Customer newCustomer1 = customerRepository.save(customer1);
        Customer newCustomer2 = customerRepository.save(customer2);
        customerRepository.deleteById(newCustomer2.getCustomerId());

        Long getId1 = newCustomer1.getCustomerId();
        Long getId2 = newCustomer2.getCustomerId();

        //Then
        assertEquals(1, customerRepository.count());
        assertEquals("firstName1", newCustomer1.getFirstName());

        //CleanUp
        customerRepository.deleteById(getId1);
        customerRepository.deleteById(getId2);

    }

    @Test
    public void shouldFinaAllCustomers() {
        //Given
        dataForTests();

        //When
        Customer newCustomer1 = customerRepository.save(customer1);
        Customer newCustomer2 = customerRepository.save(customer2);
        List<Customer> findAll = customerRepository.findAll();

        Long getId1 = newCustomer1.getCustomerId();
        Long getId2 = newCustomer2.getCustomerId();

        boolean result = getId1.equals(getId2);


        //Then
        assertFalse(result);
        assertEquals(2, findAll.size());

        //CleanUp
        customerRepository.deleteById(getId1);
        customerRepository.deleteById(getId2);
    }

    @Test
    public void shouldModifyCustomer() {
        //Given
        dataForTests();

        //When
        Customer savedCustomer1 = customerRepository.save(customer1);
        Customer savedCustomer2 = customerRepository.save(customer2);
        savedCustomer1.setFirstName("UpdatedFirstName");
        savedCustomer1.setBookings(booking1);
        savedCustomer2.setBookings(booking2);

        Long getId1 = savedCustomer1.getCustomerId();
        Long getId2 = savedCustomer2.getCustomerId();

        //Then
        assertEquals(booking1.getBookingId(), savedCustomer1.getBookings().getBookingId());
        assertEquals("UpdatedFirstName", savedCustomer1.getFirstName());
        assertEquals("firstName2", savedCustomer2.getFirstName());

        //CleanUp
        customerRepository.deleteById(getId1);
        customerRepository.deleteById(getId2);
    }

    @Test
    public void shouldModifyCustomerByAddingComplaints() {
        //Given
        dataForTests();

        //When
        Customer savedCustomer1 = customerRepository.save(customer1);
        Customer savedCustomer2 = customerRepository.save(customer2);
        savedCustomer1.setFirstName("UpdatedFirstName");
        savedCustomer1.getComplaints().add(complaint1);
        savedCustomer2.getComplaints().add(complaint2);


        Long getId1 = savedCustomer1.getCustomerId();
        Long getId2 = savedCustomer2.getCustomerId();

        //Then
        assertEquals(1, savedCustomer1.getComplaints().size());
        assertEquals(1, savedCustomer2.getComplaints().size());
        assertEquals("UpdatedFirstName", savedCustomer1.getFirstName());
        assertEquals("firstName2", savedCustomer2.getFirstName());

        //CleanUp
        customerRepository.deleteById(getId1);
        customerRepository.deleteById(getId2);
    }
}
