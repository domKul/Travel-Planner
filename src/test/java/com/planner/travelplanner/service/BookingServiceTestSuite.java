package com.planner.travelplanner.service;

import com.planner.travelplanner.domain.Booking;
import com.planner.travelplanner.domain.Customer;
import com.planner.travelplanner.domain.Hotel;
import com.planner.travelplanner.domain.dto.booking.BookingDTO;
import com.planner.travelplanner.domain.dto.booking.BookingDTOCreate;
import com.planner.travelplanner.domain.dto.booking.BookingDTOGet;
import com.planner.travelplanner.domain.exception.BookingNotFoundException;
import com.planner.travelplanner.repository.BookingRepository;
import com.planner.travelplanner.repository.CustomerRepository;
import com.planner.travelplanner.repository.HotelRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class BookingServiceTestSuite {

    @Autowired
    private BookingService bookingService;
    private BookingDTOCreate bookingDTOCreate;
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private HotelRepository hotelRepository;
    private Booking booking;

    private Customer customer;
    private Hotel hotel;

    private void dataForTests() {
        customer = new Customer(1, "firstName", "lastName", new Date(2020, 02, 02), "string", "string", "string", "string", "string", 1231231, new ArrayList<>(), new ArrayList<>());
        hotel = new Hotel();
        booking = new Booking(1L, new Date(2020, 12, 12), new Date(2020, 12, 13), customer, hotel);
    }


    private void clearData(){
        customerRepository.deleteAll();
        hotelRepository.deleteAll();
        bookingRepository.deleteAll();
    }

    @Test
    public void shouldAddBooking() {
        //Given
        clearData();
        dataForTests();
        Customer saveCustomer = customerRepository.save(customer);
        Hotel savedHotel = hotelRepository.save(hotel);
        bookingDTOCreate = new BookingDTOCreate(new Date(2020, 12, 12), new Date(2020, 12, 13), saveCustomer.getCustomerId(), hotel.getHotelId());

        //When
        Booking creatingBooking = bookingService.addBooking(bookingDTOCreate);

        //Then
        assertEquals(booking.getStartDate(), creatingBooking.getStartDate());
        assertEquals(booking.getHotels().getHotelId(), creatingBooking.getHotels().getHotelId());

        //CleanUp
        customerRepository.deleteById(saveCustomer.getCustomerId());
        hotelRepository.deleteById(savedHotel.getHotelId());
        bookingRepository.deleteById(creatingBooking.getBookingId());
    }

    @Test
    public void shouldModifyBooking() {
        //Given
        clearData();
        dataForTests();
        Customer saveCustomer = customerRepository.save(customer);
        Hotel savedHotel = hotelRepository.save(hotel);
        bookingDTOCreate = new BookingDTOCreate(new Date(2020, 12, 12), new Date(2020, 12, 13), saveCustomer.getCustomerId(), hotel.getHotelId());
        Booking creatingBooking = bookingService.addBooking(bookingDTOCreate);


        //When
        BookingDTO modify = bookingService.modifyBooking(creatingBooking.getBookingId(), new BookingDTOCreate(new Date(2022, 12, 12), new Date(2020, 12, 13), saveCustomer.getCustomerId(), hotel.getHotelId()));

        //Then
        assertEquals(creatingBooking.getCustomer().getCustomerId(), modify.getCustomer().getCustomerId());


        //CleanUp
        customerRepository.deleteById(saveCustomer.getCustomerId());
        hotelRepository.deleteById(savedHotel.getHotelId());
        bookingRepository.deleteById(creatingBooking.getBookingId());
    }

    @Test
    public void shouldDeleteBookingById() {
        // Given
        clearData();
        dataForTests();
        Customer savedCustomer = customerRepository.save(customer);
        Hotel savedHotel = hotelRepository.save(hotel);
        BookingDTOCreate bookingDTOCreate = new BookingDTOCreate(new Date(2020, 12, 12), new Date(2020, 12, 13), savedCustomer.getCustomerId(), savedHotel.getHotelId());
        Booking createdBooking = bookingService.addBooking(bookingDTOCreate);

        // When
        long getIdForDelete = createdBooking.getBookingId();
        long initialBookingCount = bookingRepository.count();
        bookingService.deleteBookingById(getIdForDelete);
        long afterDeleteBookingCount = bookingRepository.count();

        // Then
        assertEquals(1, initialBookingCount);
        assertEquals(0, afterDeleteBookingCount);
    }


    @Test
    public void testShowBookingById() {
        // Given
        clearData();
        dataForTests();
        Customer savedCustomer = customerRepository.save(customer);
        Hotel savedHotel = hotelRepository.save(hotel);
        BookingDTOCreate bookingDTOCreate = new BookingDTOCreate(new Date(2020, 12, 12), new Date(2020, 12, 13), savedCustomer.getCustomerId(), savedHotel.getHotelId());
        Booking createdBooking1 = bookingService.addBooking(bookingDTOCreate);

        // When
        BookingDTOGet bookingDTO = bookingService.showBookingById(createdBooking1.getBookingId());

        // Then
        assertEquals(booking.getCustomer().getFirstName(), bookingDTO.getCustomerFirstName());
    }

    @Test
    public void testShowBookingByIdThrowsException() {
        // Given
        clearData();
        long bookingId = 123;

        // When & Then
        assertThrows(BookingNotFoundException.class, () -> {
            bookingService.showBookingById(bookingId);
        });
    }

}
