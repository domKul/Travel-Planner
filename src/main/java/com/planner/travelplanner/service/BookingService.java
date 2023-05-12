package com.planner.travelplanner.service;

import com.planner.travelplanner.domain.Booking;
import com.planner.travelplanner.domain.Customer;
import com.planner.travelplanner.domain.Hotel;
import com.planner.travelplanner.domain.Tour;
import com.planner.travelplanner.domain.dto.booking.BookingDTO;
import com.planner.travelplanner.domain.dto.booking.BookingDTOCreate;
import com.planner.travelplanner.domain.dto.booking.BookingDTOGet;
import com.planner.travelplanner.domain.dto.booking.BookingDTOModify;
import com.planner.travelplanner.domain.exception.BookingNotFoundException;
import com.planner.travelplanner.domain.exception.CustomerNotFoundException;
import com.planner.travelplanner.domain.exception.HotelNotFoundException;
import com.planner.travelplanner.domain.exception.TourNotFoundException;
import com.planner.travelplanner.mapper.BookingMapper;
import com.planner.travelplanner.repository.BookingRepository;
import com.planner.travelplanner.repository.CustomerRepository;
import com.planner.travelplanner.repository.HotelRepository;
import com.planner.travelplanner.repository.TourRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BookingService {

    private final BookingRepository bookingRepository;
    private final BookingMapper bookingMapper;
    private final CustomerRepository customerRepository;
    private final HotelRepository hotelRepository;
    private final TourRepository tourRepository;

    public BookingService(BookingRepository bookingRepository, BookingMapper bookingMapper, CustomerRepository customerRepository, HotelRepository hotelRepository, TourRepository tourRepository) {
        this.bookingRepository = bookingRepository;
        this.bookingMapper = bookingMapper;
        this.customerRepository = customerRepository;
        this.hotelRepository = hotelRepository;
        this.tourRepository = tourRepository;
    }

    public Booking addBooking(final BookingDTOCreate bookingDTOCreate)throws BookingNotFoundException{
        Customer findCustomer = customerRepository.findById(bookingDTOCreate.getCustomerId())
                .orElseThrow(CustomerNotFoundException::new);
        Hotel findHotel = hotelRepository.findById(bookingDTOCreate.getHotelId())
                .orElseThrow(HotelNotFoundException::new);
        Tour findTour = tourRepository.findById(bookingDTOCreate.getTourId())
                .orElseThrow(TourNotFoundException::new);

        Booking booking = new Booking();
        booking.setCustomer(findCustomer);
        booking.setStartDate(bookingDTOCreate.getStartDate());
        booking.setEndDate(bookingDTOCreate.getEndDate());
        booking.setHotels(findHotel);
        booking.setTours(findTour);
        return bookingRepository.save(booking);
    }

    public List<BookingDTOGet> showAllBookings(){
        return bookingMapper.mapToDTOListGet(bookingRepository.findAll());
    }

    public BookingDTOGet showBookingById(final long bookingId)throws BookingNotFoundException {
        if (bookingRepository.existsById(bookingId)){
            return bookingMapper.mapToBookingDTOGet(bookingRepository.findById(bookingId).get());
        }else {
            throw  new BookingNotFoundException();
        }
    }

    public BookingDTO modifyBooking(final long bookingId, final BookingDTOCreate bookingDTOCreate) throws BookingNotFoundException {
        if (bookingRepository.existsById(bookingId)) {
            Booking booking = bookingMapper.mapToBookingForUpdate(bookingId, bookingDTOCreate);
            Booking saveUpdatedBooking = bookingRepository.save(booking);
            return bookingMapper.mapToBookingDTO(saveUpdatedBooking);
        } else {
            throw new BookingNotFoundException();
        }
    }
    public void deleteBookingById(final long bookingId) throws BookingNotFoundException{
        Optional<Booking> booking = bookingRepository.findById(bookingId);
        if (booking.isPresent()){
            bookingRepository.deleteById(bookingId);
        }else {
            throw new BookingNotFoundException();
        }
    }


}
