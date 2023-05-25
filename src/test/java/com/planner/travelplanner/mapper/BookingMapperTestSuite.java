package com.planner.travelplanner.mapper;

import com.planner.travelplanner.domain.Booking;
import com.planner.travelplanner.domain.Customer;
import com.planner.travelplanner.domain.Destination;
import com.planner.travelplanner.domain.dto.booking.BookingDTO;
import com.planner.travelplanner.domain.dto.booking.BookingDTOGet;
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

    private Booking booking;

    private Customer customer;
    private Destination destination;

    private void dataForTests(){
        customer = new Customer(1, "firstName", "lastName", new Date(2020,02,02), "string","string", "string", "string", "string", 1231231, new ArrayList<>(), new ArrayList<>());
        destination = new Destination();
        booking = new Booking(1l,new Date(2020,12,12),new Date(2020,12,13),customer, destination);
    }

    @Test
    public void shouldMapToBookingDTOGet(){
        //Given
        dataForTests();

        //When
        BookingDTOGet mapToDTOGET = bookingMapper.mapToBookingDTOGet(booking);

        //When
        assertEquals(booking.getCustomer().getFirstName(),mapToDTOGET.getCustomerFirstName());
        assertEquals(booking.getCustomer().getLastName(),mapToDTOGET.getCustomerLastName());
        assertEquals(booking.getDestinations().getDestinationId(),mapToDTOGET.getHotelId());
    }

    @Test
    public void shouldMapToDTOListGet(){
        //Given
        dataForTests();
        customer.getBookings().add(booking);

        //When
        List<BookingDTOGet>mappinglist = bookingMapper.mapToDTOListGet(customer.getBookings());

        //Then
        assertFalse(mappinglist.isEmpty());
        assertEquals(customer.getBookings().get(0).getCustomer().getFirstName(),mappinglist.get(0).getCustomerFirstName());
    }

    @Test
    public void shouldMapToBookingDTO(){
        //Given
        dataForTests();

        //When
        BookingDTO mappingDTO = bookingMapper.mapToBookingDTO(booking);

        //Then
        assertEquals(booking.getCustomer().getFirstName(),mappingDTO.getCustomer().getFirstName());
    }
}
