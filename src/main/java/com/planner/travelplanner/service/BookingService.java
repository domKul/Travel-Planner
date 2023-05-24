package com.planner.travelplanner.service;

import com.planner.travelplanner.domain.Booking;
import com.planner.travelplanner.domain.Customer;
import com.planner.travelplanner.domain.Destination;
import com.planner.travelplanner.domain.dto.booking.BookingDTO;
import com.planner.travelplanner.domain.dto.booking.BookingDTOCreate;
import com.planner.travelplanner.domain.dto.booking.BookingDTOGet;
import com.planner.travelplanner.domain.exception.BookingNotFoundException;
import com.planner.travelplanner.domain.exception.CustomerNotFoundException;
import com.planner.travelplanner.domain.exception.HotelNotFoundException;
import com.planner.travelplanner.mapper.BookingMapper;
import com.planner.travelplanner.repository.BookingRepository;
import com.planner.travelplanner.repository.CustomerRepository;
import com.planner.travelplanner.repository.DestinationRepository;
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
            Destination findDestination = destinationRepository.findById(bookingDTOCreate.getHotelId())
                    .orElseThrow(HotelNotFoundException::new);

            booking.setCustomer(findCustomer);
            booking.setStartDate(bookingDTOCreate.getStartDate());
            booking.setEndDate(bookingDTOCreate.getEndDate());
            booking.setHotels(findDestination);
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
            booking.setHotels(destinationRepository.findById(bookingDTOCreate.getHotelId()).orElseThrow(HotelNotFoundException::new));
            return booking;
        } else {
            throw new BookingNotFoundException();
        }
    }


}
