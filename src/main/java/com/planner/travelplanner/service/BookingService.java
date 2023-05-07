package com.planner.travelplanner.service;

import com.planner.travelplanner.domain.Booking;
import com.planner.travelplanner.domain.Customer;
import com.planner.travelplanner.domain.Hotel;
import com.planner.travelplanner.domain.Tour;
import com.planner.travelplanner.domain.dto.BookingDTO;
import com.planner.travelplanner.domain.dto.BookingDTOCreate;
import com.planner.travelplanner.domain.dto.CustomerDTO;
import com.planner.travelplanner.domain.exception.BookingNotFoundException;
import com.planner.travelplanner.domain.exception.CustomerNotFoundException;
import com.planner.travelplanner.domain.exception.HotelNotFoundException;
import com.planner.travelplanner.domain.exception.TourNotFoundException;
import com.planner.travelplanner.mapper.BookingMapper;
import com.planner.travelplanner.repository.BookingRepository;
import com.planner.travelplanner.repository.CustomerRepository;
import com.planner.travelplanner.repository.HotelRepository;
import com.planner.travelplanner.repository.TourRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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

    public Booking addBooking(final BookingDTOCreate bookingDTOCreate)throws CustomerNotFoundException{
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

    public List<BookingDTO> showAllBookings(){
        return bookingMapper.mapToDTOList(bookingRepository.findAll());
    }

    public BookingDTO showBookingById(final long bookingId)throws BookingNotFoundException {
        if (bookingRepository.existsById(bookingId)){
            return bookingMapper.mapToBookingDTO(bookingRepository.findById(bookingId).get());
        }else {
            throw  new BookingNotFoundException();
        }
    }

    public BookingDTO modifyBooking(final long bookingId, final BookingDTO bookingDTO)throws BookingNotFoundException {
        if (bookingRepository.existsById(bookingId)){
            Booking getBooking = bookingMapper.mapToBooking(bookingDTO);
            Booking update = bookingRepository.save(getBooking);
            return bookingMapper.mapToBookingDTO(update);
        }else {
            throw  new BookingNotFoundException();
        }
    }


}
