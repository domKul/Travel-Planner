package com.planner.travelplanner.mapper;

import com.planner.travelplanner.domain.Booking;
import com.planner.travelplanner.domain.Customer;
import com.planner.travelplanner.domain.Destination;
import com.planner.travelplanner.domain.dto.booking.BookingDTO;
import com.planner.travelplanner.domain.dto.booking.BookingDTOGet;
import com.planner.travelplanner.repository.BookingRepository;
import com.planner.travelplanner.repository.CustomerRepository;
import com.planner.travelplanner.repository.DestinationRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
public class BookingMapperTestSuite {

    @Autowired
    private BookingMapper bookingMapper;
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private DestinationRepository destinationRepository;
    private Booking booking1;
    private Booking booking2;
    private Customer customer;
    private Destination destination;

    private void dataForTests() {
        customer = new Customer(1L, "firstName", "lastName", new Date(2020, 02, 02), "string", "string", "string", "string", "string", 1231231, new ArrayList<>(), new ArrayList<>());
        destination = new Destination();
        booking1 = new Booking(1L, new Date(2020, 12, 12), new Date(2020, 12, 13), null, null);
        booking2 = new Booking(1L, new Date(2020, 12, 12), new Date(2020, 12, 13), null, null);
    }

    @Test
    public void shouldMapToBookingDTOGet() {
        //Given
        dataForTests();
        Customer saveCustomer = customerRepository.save(customer);
        Destination saveDestination = destinationRepository.save(destination);
        booking2.setDestinations(saveDestination);
        booking2.setCustomer(saveCustomer);
        Booking saveBooking = bookingRepository.save(booking2);
        //When
        BookingDTOGet mapToDTOGET = bookingMapper.mapToBookingDTOGet(saveBooking);
        //When
        assertEquals(booking2.getCustomer().getFirstName(), mapToDTOGET.getCustomerFirstName());
        assertEquals(booking2.getCustomer().getLastName(), mapToDTOGET.getCustomerLastName());
        assertEquals(booking2.getCustomer().getCustomerId(), mapToDTOGET.getCustomerId());
    }

    @Test
    public void shouldMapToDTOListGet() {
        //Given
        dataForTests();
        Destination saveDest = destinationRepository.save(destination);
        Customer saveCustomer = customerRepository.save(customer);
        booking2.setDestinations(saveDest);
        booking2.setCustomer(saveCustomer);
        booking1.setDestinations(saveDest);
        booking1.setCustomer(saveCustomer);
        Booking saveBooking1 = bookingRepository.save(booking1);
        Booking saveBooking2 = bookingRepository.save(booking2);
        List<Booking> listOfBookings = List.of(saveBooking1, saveBooking2);
        //When
        List<BookingDTOGet> mappinglist = bookingMapper.mapToDTOListGet(listOfBookings);
        //Then
        assertFalse(mappinglist.isEmpty());
        assertEquals(listOfBookings.get(0).getCustomer().getFirstName(), mappinglist.get(0).getCustomerFirstName());
    }

    @Test
    public void shouldMapToBookingDTO() {
        //Given
        dataForTests();
        Customer saveCustomer = customerRepository.save(customer);
        booking2.setCustomer(saveCustomer);
        Booking save = bookingRepository.save(booking2);
        //When
        BookingDTO mappingDTO = bookingMapper.mapToBookingDTO(save);
        //Then
        assertEquals(booking2.getCustomer().getFirstName(), mappingDTO.getCustomer().getFirstName());
    }
}
