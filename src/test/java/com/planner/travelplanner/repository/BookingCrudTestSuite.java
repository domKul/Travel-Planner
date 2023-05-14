package com.planner.travelplanner.repository;


import com.planner.travelplanner.domain.Booking;
import com.planner.travelplanner.domain.Customer;
import com.planner.travelplanner.domain.Hotel;
import com.planner.travelplanner.mapper.IdType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class BookingCrudTestSuite {

    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private HotelRepository hotelRepository;


    @Autowired
    private CustomerRepository customerRepository;

    private Customer customer1 = null;
    private Customer customer2 = null;
    private Booking booking1 = null;
    private Booking booking2 = null;
    private Hotel hotel1 = null;
    private Hotel hotel2 = null;


    private void dataForTests() {
        customer1 = new Customer(IdType.EMPTY_ID.getId(), "firstName1", "lastName1", new Date(2000, 2, 11), "country", "city", "streetName", "postalCode", "email", 1231231, new ArrayList<>(), null);
        customer2 = new Customer(IdType.EMPTY_ID.getId(), "firstName2", "lastName2", new Date(2000, 2, 11), "country", "city", "streetName", "postalCode", "email", 1231231, new ArrayList<>(), null);
        hotel1 = new Hotel(IdType.EMPTY_ID.getId(), IdType.EMPTY_ID.getId(), null, null, null);
        hotel2 = new Hotel(IdType.EMPTY_ID.getId(),IdType.EMPTY_ID.getId(), null, null, null);
        booking1 = new Booking(IdType.EMPTY_ID.getId(), new Date(2020, 12, 12), new Date(2020, 12, 12), null, null);
        booking2 = new Booking(IdType.EMPTY_ID.getId(), new Date(2020, 12, 12), new Date(2020, 12, 12), null, null);
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
        //Given
        dataForTests();

        //When
        Booking saveBooking1 = bookingRepository.save(booking1);
        Booking saveBooking2 = bookingRepository.save(booking2);
        long getId1 = booking1.getBookingId();
        long getId2 = saveBooking2.getBookingId();
        boolean result = booking1.equals(booking2);

        //Then
        assertFalse(result);
        assertEquals(getId1, saveBooking1.getBookingId());
        assertEquals(getId2, saveBooking2.getBookingId());

        //CealnUp
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
        Hotel saveHotel1 = hotelRepository.save(hotel1);
        Hotel savedHotel2 = hotelRepository.save(hotel2);
        Booking saveBooking1 = bookingRepository.save(booking1);
        Booking saveBooking2 = bookingRepository.save(booking2);

        //When
        saveBooking1.setHotels(hotel1);
        saveBooking1.setCustomer(customer1);
        Booking modifiedBooking1 = bookingRepository.save(saveBooking1);
        saveBooking2.setHotels(hotel2);
        saveBooking2.setCustomer(customer2);
        Booking modifiedBooking2 = bookingRepository.save(saveBooking2);

        // Then
        assertEquals(customer1.getFirstName(), modifiedBooking1.getCustomer().getFirstName());
        assertEquals(hotel1.getName(), modifiedBooking1.getHotels().getName());
        assertEquals(saveBooking1.getBookingId(), modifiedBooking1.getBookingId());
        assertEquals(saveBooking2.getBookingId(), modifiedBooking2.getBookingId());
        assertEquals(saveBooking2.getCustomer().getCustomerId(), modifiedBooking2.getCustomer().getCustomerId());

        //CleanUp
        long getId1 = saveBooking1.getBookingId();
        long getId2 = saveBooking2.getBookingId();
        long getId3 = saveCustomer1.getCustomerId();
        long getId4 = saveCustomer2.getCustomerId();
        long getId5 = saveHotel1.getHotelId();
        long getId6 = savedHotel2.getHotelId();
        bookingRepository.deleteById(getId1);
        bookingRepository.deleteById(getId2);
        customerRepository.deleteById(getId3);
        customerRepository.deleteById(getId4);
        hotelRepository.deleteById(getId5);
        hotelRepository.deleteById(getId6);
    }
}