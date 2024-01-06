package com.planner.travelplanner.booking;

import com.planner.travelplanner.customer.Customer;
import com.planner.travelplanner.customer.CustomerRepository;
import com.planner.travelplanner.destination.Destination;
import com.planner.travelplanner.destination.DestinationRepository;
import com.planner.travelplanner.exception.BookingNotFoundException;
import com.planner.travelplanner.exception.CustomerNotFoundException;
import com.planner.travelplanner.exception.HotelNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final BookingMapper bookingMapper;
    private final CustomerRepository customerRepository;
    private final DestinationRepository destinationRepository;

    public BookingService(BookingRepository bookingRepository, BookingMapper bookingMapper, CustomerRepository customerRepository, DestinationRepository destinationRepository) {
        this.bookingRepository = bookingRepository;
        this.bookingMapper = bookingMapper;
        this.customerRepository = customerRepository;
        this.destinationRepository = destinationRepository;
    }

    @Transactional
    public Booking addBooking(final BookingDTOCreate bookingDTOCreate) {
        Booking booking = new Booking();

        if (bookingDTOCreate != null) {
            Customer findCustomer = customerRepository.findById(bookingDTOCreate.getCustomerId())
                    .orElseThrow(CustomerNotFoundException::new);
            Destination findDestination = destinationRepository.findById(bookingDTOCreate.getDestinationId())
                    .orElseThrow(HotelNotFoundException::new);

            booking.setCustomer(findCustomer);
            booking.setStartDate(bookingDTOCreate.getStartDate());
            booking.setEndDate(bookingDTOCreate.getEndDate());
            booking.setDestinations(findDestination);
        }

        return bookingRepository.save(booking);
    }

    public List<BookingDTOGet> showAllBookings() {
        return bookingMapper.mapToDTOListGet(bookingRepository.findAll());
    }

    public BookingDTOGet showBookingById(final long bookingId) {
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

    public Booking mapToBookingForUpdate(final long bookingId, BookingDTOCreate bookingDTOCreate) {
        Booking booking = bookingRepository.findById(bookingId).orElseThrow(
                BookingNotFoundException::new);
        if (booking != null) {
            booking.setStartDate(bookingDTOCreate.getStartDate());
            booking.setEndDate(bookingDTOCreate.getEndDate());
            booking.setCustomer(customerRepository.findById(bookingDTOCreate.getCustomerId()).orElseThrow(CustomerNotFoundException::new));
            booking.setDestinations(destinationRepository.findById(bookingDTOCreate.getDestinationId()).orElseThrow(HotelNotFoundException::new));
            return booking;
        } else {
            throw new BookingNotFoundException();
        }
    }


}
