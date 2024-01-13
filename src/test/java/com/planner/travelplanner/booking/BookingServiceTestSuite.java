package com.planner.travelplanner.booking;

import com.planner.travelplanner.customer.Customer;
import com.planner.travelplanner.customer.CustomerRepository;
import com.planner.travelplanner.destination.Destination;
import com.planner.travelplanner.destination.DestinationRepository;
import com.planner.travelplanner.exception.NotFoundException;
import jakarta.mail.MessagingException;
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
class BookingServiceTestSuite {

    @Autowired
    private BookingService bookingService;
    private BookingDTOCreate bookingDTOCreate;
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private DestinationRepository destinationRepository;
    private Booking booking;
    private Booking booking2;
    private Customer customer;
    private Destination destination;

    private void dataForTests() {
        customer = new Customer(1L, "firstName", "lastName", new Date(2020, 02, 02), "string", "string", "string", "string", "string", 1231231, new ArrayList<>());
        destination = new Destination();
        booking = new Booking(1L, new Date(2020, 12, 12), new Date(2020, 12, 13), null, null);
        booking2 = new Booking(1L, new Date(2020, 12, 12), new Date(2020, 12, 13), null, null);
    }

    private void clearData() {
        customerRepository.deleteAll();
        destinationRepository.deleteAll();
        bookingRepository.deleteAll();
    }

    @BeforeEach
    public void clear() {
        clearData();
    }

    @Test
    void shouldAddBooking() throws MessagingException {
        //Given
        clearData();
        dataForTests();
        Customer saveCustomer = customerRepository.save(customer);
        Destination savedDestination = destinationRepository.save(destination);
        bookingDTOCreate = new BookingDTOCreate(new Date(2020, 12, 12), new Date(2020, 12, 13), saveCustomer.getCustomerId(), savedDestination.getDestinationId());
        //When
        Booking creatingBooking = bookingService.addBooking(bookingDTOCreate);
        //Then
        assertEquals(booking.getStartDate(), creatingBooking.getStartDate());
        assertEquals(savedDestination.getDestinationId(), creatingBooking.getDestinations().getDestinationId());
        //CleanUp
        customerRepository.deleteById(saveCustomer.getCustomerId());
        destinationRepository.deleteById(savedDestination.getDestinationId());
        bookingRepository.deleteById(creatingBooking.getBookingId());
    }

    @Test
    public void shouldModifyBooking() throws MessagingException {
        //Given
        clearData();
        dataForTests();
        Customer saveCustomer = customerRepository.save(customer);
        Destination savedDestination = destinationRepository.save(destination);
        bookingDTOCreate = new BookingDTOCreate(new Date(2020, 12, 12), new Date(2020, 12, 13), saveCustomer.getCustomerId(), destination.getDestinationId());
        Booking creatingBooking = bookingService.addBooking(bookingDTOCreate);
        //When
        BookingDTO modify = bookingService.modifyBooking(creatingBooking.getBookingId(), new BookingDTOCreate(new Date(2022, 12, 12), new Date(2020, 12, 13), saveCustomer.getCustomerId(), destination.getDestinationId()));
        //Then
        assertEquals(creatingBooking.getCustomer().getCustomerId(), modify.getCustomer().getCustomerId());
        //CleanUp
        customerRepository.deleteById(saveCustomer.getCustomerId());
        destinationRepository.deleteById(savedDestination.getDestinationId());
        bookingRepository.deleteById(creatingBooking.getBookingId());
    }

    @Test
    public void shouldDeleteBookingById() throws MessagingException {
        // Given
        clearData();
        dataForTests();
        Customer savedCustomer = customerRepository.save(customer);
        Destination savedDestination = destinationRepository.save(destination);
        BookingDTOCreate bookingDTOCreate = new BookingDTOCreate(new Date(2020, 12, 12), new Date(2020, 12, 13), savedCustomer.getCustomerId(), savedDestination.getDestinationId());
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
    public void testShowBookingById() throws MessagingException {
        // Given
        clearData();
        dataForTests();
        Customer savedCustomer = customerRepository.save(customer);
        Destination savedDestination = destinationRepository.save(destination);
        BookingDTOCreate bookingDTOCreate = new BookingDTOCreate(new Date(2020, 12, 12), new Date(2020, 12, 13), savedCustomer.getCustomerId(), savedDestination.getDestinationId());
        Booking createdBooking1 = bookingService.addBooking(bookingDTOCreate);
        // When
        BookingDTOGet bookingDTO = bookingService.showBookingById(createdBooking1.getBookingId());
        // Then
        assertEquals(savedCustomer.getFirstName(), bookingDTO.getCustomerFirstName());
    }

    @Test
    public void shouldShowAllBookings() {
        //Given
        dataForTests();
        Destination savedDes = destinationRepository.save(destination);
        Customer savedCustomer = customerRepository.save(customer);
        booking.setCustomer(savedCustomer);
        booking.setDestinations(savedDes);
        bookingRepository.save(booking);
        bookingRepository.save(booking);
        //When
        List<BookingDTOGet> findAllList = bookingService.showAllBookings();
        //Then
        assertEquals(2, findAllList.size());
        //CleanUp
        bookingRepository.deleteAll();
        destinationRepository.deleteAll();
        customerRepository.deleteAll();
    }

    @Test
    public void testShowBookingByIdThrowsException() {
        // Given
        clearData();
        long bookingId = 123;
        // When & Then
        assertThrows(NotFoundException.class, () -> {
            bookingService.showBookingById(bookingId);
        });
    }
}
