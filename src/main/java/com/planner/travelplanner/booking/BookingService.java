package com.planner.travelplanner.booking;

import com.planner.travelplanner.customer.Customer;
import com.planner.travelplanner.customer.CustomerService;
import com.planner.travelplanner.destination.DestinationService;
import com.planner.travelplanner.exception.BookingNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final BookingMapper bookingMapper;
    private final CustomerService customerService;
    private final DestinationService destinationService;

    BookingService(BookingRepository bookingRepository, BookingMapper bookingMapper,
                    CustomerService customerService, DestinationService destinationService) {
        this.bookingRepository = bookingRepository;
        this.bookingMapper = bookingMapper;
        this.customerService = customerService;
        this.destinationService = destinationService;
    }

    @Transactional
    public Booking addBooking(final BookingDTOCreate bookingDTOCreate) {
        var booking = new Booking();
        if (bookingDTOCreate == null) {
            throw new BookingNotFoundException();
        }
        var customerOrThrow = customerService.findCustomerOrThrow(bookingDTOCreate.getCustomerId());
        var destinationOrElseThrow = destinationService.getDestinationOrElseThrow(bookingDTOCreate.getDestinationId());
        booking.setCustomer(customerOrThrow);
            booking.setStartDate(bookingDTOCreate.getStartDate());
            booking.setEndDate(bookingDTOCreate.getEndDate());
            booking.setDestinations(destinationOrElseThrow);

        return bookingRepository.save(booking);
    }

    List<BookingDTOGet> showAllBookings() {
        return bookingMapper.mapToDTOListGet(bookingRepository.findAll());
    }

    BookingDTOGet showBookingById(final long bookingId) {
        bookingRepository.existsById(bookingId);
        if (bookingRepository.existsById(bookingId)) {
            return bookingMapper.mapToBookingDTOGet(bookingRepository.findById(bookingId).get());
        } else {
            throw new BookingNotFoundException();
        }
    }

    @Transactional
    public BookingDTO modifyBooking(final long bookingId, final BookingDTOCreate bookingDTOCreate) {
        if (bookingRepository.existsById(bookingId)) {
            Booking booking = mapToBookingForUpdate(bookingId, bookingDTOCreate);
            Booking saveUpdatedBooking = bookingRepository.save(booking);
            return bookingMapper.mapToBookingDTO(saveUpdatedBooking);
        } else {
            throw new BookingNotFoundException();
        }
    }

    @Transactional
    public void deleteBookingById(final long bookingId) {
        Optional<Booking> booking = bookingRepository.findById(bookingId);
        if (booking.isPresent()) {
            bookingRepository.deleteById(bookingId);
        } else {
            throw new BookingNotFoundException();
        }
    }

    Booking mapToBookingForUpdate(final long bookingId, BookingDTOCreate bookingDTOCreate) {
        var booking = bookingRepository.findById(bookingId).orElseThrow(
                BookingNotFoundException::new);
        Customer customerOrThrow = customerService.findCustomerOrThrow(bookingDTOCreate.getCustomerId());
        if (booking != null) {
            booking.setStartDate(bookingDTOCreate.getStartDate());
            booking.setEndDate(bookingDTOCreate.getEndDate());
            booking.setCustomer(customerOrThrow);
            booking.setDestinations(destinationService.getDestinationOrElseThrow(bookingDTOCreate.getDestinationId()));
            return booking;
        } else {
            throw new BookingNotFoundException();
        }
    }
}
