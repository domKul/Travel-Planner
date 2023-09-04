package com.planner.travelplanner.repository;


import com.planner.travelplanner.domain.Booking;
import com.planner.travelplanner.domain.Customer;
import com.planner.travelplanner.domain.Destination;
import com.planner.travelplanner.mapper.IdType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class BookingCrudTestSuite {

    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private DestinationRepository destinationRepository;
    @Autowired
    private CustomerRepository customerRepository;

    private Customer customer1 = null;
    private Customer customer2 = null;
    private Booking booking1 = null;
    private Booking booking2 = null;
    private Destination destination1 = null;
    private Destination destination2 = null;


    private void dataForTests() {
        customer1 = new Customer(IdType.EMPTY_ID.getId(), "firstName1", "lastName1", new Date(2000, 2, 11), "country", "city", "streetName", "postalCode", "email", 1231231, new ArrayList<>(), null);
        customer2 = new Customer(IdType.EMPTY_ID.getId(), "firstName2", "lastName2", new Date(2000, 2, 11), "country", "city", "streetName", "postalCode", "email", 1231231, new ArrayList<>(), null);
        destination1 = new Destination(IdType.EMPTY_ID.getId(), IdType.EMPTY_ID.getId(), null, null, null, 231);
        destination2 = new Destination(IdType.EMPTY_ID.getId(), IdType.EMPTY_ID.getId(), null, null, null, 1231);
        booking1 = new Booking(IdType.EMPTY_ID.getId(), new Date(2020, 12, 12), new Date(2020, 01, 12), null, null);
        booking2 = new Booking(IdType.EMPTY_ID.getId(), new Date(2020, 12, 12), new Date(2020, 07, 12), null, null);
    }

    @BeforeEach
    public void deleteDAta() {
        bookingRepository.deleteAll();
        customerRepository.deleteAll();
        destinationRepository.deleteAll();
    }

    @Test
    public void shoulCreateEmptyBooking() {
        //Given
        dataForTests();

        //When
        Booking saveBooking1 = bookingRepository.save(booking1);
        Booking saveBooking2 = bookingRepository.save(booking2);

        //Then
        assertEquals(2, bookingRepository.count());
        assertEquals(booking1,saveBooking1);
        assertEquals(booking2,saveBooking2);
        assertNull(saveBooking2.getCustomer());
        assertNull(saveBooking1.getCustomer());

        //CleanUp
        long getId1 = saveBooking1.getBookingId();
        long getId2 = saveBooking2.getBookingId();
        bookingRepository.deleteById(getId1);
        bookingRepository.deleteById(getId2);
    }

    @Test
    public void shouldDeleteBooking() {
        //Given
        dataForTests();

        //When
        Booking saveBooking1 = bookingRepository.save(booking1);
        Booking saveBooking2 = bookingRepository.save(booking2);
        long getId1 = saveBooking1.getBookingId();
        long getId2 = saveBooking2.getBookingId();
        bookingRepository.deleteById(getId1);
        bookingRepository.deleteById(getId2);

        //Then
        assertFalse(bookingRepository.existsById(getId1));
        assertFalse(bookingRepository.existsById(getId2));
    }

    @Test
    public void shouldSaveAndRetrieveBookingById() {
        // Given
        dataForTests();
        Customer saveCustomer1 = customerRepository.save(customer1);
        Customer saveCustomer2 = customerRepository.save(customer2);
        booking1.setCustomer(saveCustomer1);
        booking2.setCustomer(saveCustomer2);
        Booking saveBooking1 = bookingRepository.save(booking1);
        Booking saveBooking2 = bookingRepository.save(booking2);

        // When
        long getId1 = saveBooking1.getBookingId();
        long getId2 = saveBooking2.getBookingId();
        Optional<Booking> bookingId1 = bookingRepository.findById(getId1);
        Optional<Booking> bookingId2 = bookingRepository.findById(getId2);

        // Then
        assertTrue(bookingId1.isPresent());
        assertTrue(bookingId2.isPresent());


        assertEquals(booking1.getCustomer().getFirstName(), bookingId1.get().getCustomer().getFirstName());
        assertEquals(booking2.getCustomer().getFirstName(), bookingId2.get().getCustomer().getFirstName());

        // CleanUp
        bookingRepository.deleteById(getId1);
        bookingRepository.deleteById(getId2);
    }

    @Test
    public void shouldSaveAndRetrieveAllBookings() {
        //Given
        dataForTests();

        //When
        Booking saveBooking1 = bookingRepository.save(booking1);
        Booking saveBooking2 = bookingRepository.save(booking2);

        //Then
        assertEquals(2, bookingRepository.count());

        //CealnUp
        long getId1 = saveBooking1.getBookingId();
        long getId2 = saveBooking2.getBookingId();
        bookingRepository.deleteById(getId1);
        bookingRepository.deleteById(getId2);
    }

    @Test
    public void shoulModifyExistingBooking() {
        //Given
        dataForTests();
        Customer saveCustomer1 = customerRepository.save(customer1);
        Customer saveCustomer2 = customerRepository.save(customer2);
        Destination saveDestination1 = destinationRepository.save(destination1);
        Destination savedDestination2 = destinationRepository.save(destination2);
        Booking saveBooking1 = bookingRepository.save(booking1);
        Booking saveBooking2 = bookingRepository.save(booking2);

        //When
        saveBooking1.setDestinations(saveDestination1);
        saveBooking1.setCustomer(saveCustomer1);
        Booking modifiedBooking1 = bookingRepository.save(saveBooking1);
        saveBooking2.setDestinations(savedDestination2);
        saveBooking2.setCustomer(saveCustomer2);
        Booking modifiedBooking2 = bookingRepository.save(saveBooking2);

        // Then
        assertEquals(customer1.getFirstName(), modifiedBooking1.getCustomer().getFirstName());
        assertEquals(destination1.getName(), modifiedBooking1.getDestinations().getName());
        assertEquals(saveBooking1.getBookingId(), modifiedBooking1.getBookingId());
        assertEquals(saveBooking2.getBookingId(), modifiedBooking2.getBookingId());
        assertEquals(saveBooking2.getCustomer().getCustomerId(), modifiedBooking2.getCustomer().getCustomerId());

        //CleanUp
        long getId1 = saveBooking1.getBookingId();
        long getId2 = saveBooking2.getBookingId();
        long getId3 = saveCustomer1.getCustomerId();
        long getId4 = saveCustomer2.getCustomerId();
        long getId5 = saveDestination1.getDestinationId();
        long getId6 = savedDestination2.getDestinationId();
        bookingRepository.deleteById(getId1);
        bookingRepository.deleteById(getId2);
        customerRepository.deleteById(getId3);
        customerRepository.deleteById(getId4);
        destinationRepository.deleteById(getId5);
        destinationRepository.deleteById(getId6);
    }
}