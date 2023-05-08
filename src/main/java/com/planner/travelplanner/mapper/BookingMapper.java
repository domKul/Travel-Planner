package com.planner.travelplanner.mapper;

import com.planner.travelplanner.domain.Booking;
import com.planner.travelplanner.domain.dto.booking.BookingDTO;
import com.planner.travelplanner.domain.dto.booking.BookingDTOCreate;
import com.planner.travelplanner.domain.dto.booking.BookingDTOGet;
import com.planner.travelplanner.domain.dto.booking.BookingDTOModify;
import com.planner.travelplanner.domain.exception.BookingNotFoundException;
import com.planner.travelplanner.domain.exception.CustomerNotFoundException;
import com.planner.travelplanner.domain.exception.HotelNotFoundException;
import com.planner.travelplanner.domain.exception.TourNotFoundException;
import com.planner.travelplanner.repository.BookingRepository;
import com.planner.travelplanner.repository.CustomerRepository;
import com.planner.travelplanner.repository.HotelRepository;
import com.planner.travelplanner.repository.TourRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingMapper {
    private final BookingRepository bookingRepository;
    private final CustomerRepository customerRepository;
    private final TourRepository tourRepository;
    private final HotelRepository hotelRepository;

    public BookingMapper(BookingRepository bookingRepository, CustomerRepository customerRepository, TourRepository tourRepository, HotelRepository hotelRepository) {
        this.bookingRepository = bookingRepository;
        this.customerRepository = customerRepository;
        this.tourRepository = tourRepository;
        this.hotelRepository = hotelRepository;
    }



    public Booking mapToBooking(BookingDTO bookingDTO){
        return new Booking(IdType.EMPTY_ID.getId(),
                bookingDTO.getStartDate(),
                bookingDTO.getEndDate(),
                bookingDTO.getCustomer(),
                bookingDTO.getTour(),
                bookingDTO.getHotel()
                );
    }

    public BookingDTO mapToBookingDTO(Booking booking){
        return new BookingDTO(
                booking.getStartDate(),
                booking.getEndDate(),
                booking.getCustomer(),
                booking.getTours(),
                booking.getHotels());
    }
    public BookingDTOModify mapToBookingDTOModify(Booking booking){
        return new BookingDTOModify(booking.getBookingId(),
                booking.getTours().getTourId(),
                booking.getHotels().getHotelId(),
                booking.getStartDate(),
                booking.getEndDate()
                );
    }
    public BookingDTOCreate mapToBookingDTOCreate(Booking booking){
        return new BookingDTOCreate(
                booking.getStartDate(),
                booking.getEndDate(),
                booking.getCustomer().getCustomerId(),
                booking.getTours().getTourId(),
                booking.getHotels().getHotelId());
    }
    public BookingDTOGet mapToBookingDTOGet(Booking booking){
        return new BookingDTOGet(
                booking.getStartDate(),
                booking.getCustomer().getCustomerId(),
                booking.getCustomer().getFirstName(),
                booking.getCustomer().getLastName(),
                booking.getCustomer().getBirthdate(),
                booking.getCustomer().getCountry(),
                booking.getCustomer().getCity(),
                booking.getCustomer().getStreetName(),
                booking.getCustomer().getPostalCode(),
                booking.getCustomer().getEmail(),
                booking.getCustomer().getPhoneNumber(),
                booking.getCustomer().getLogin(),
                booking.getTours().getTourId(),
                booking.getTours().getTourName(),
                booking.getTours().getTourDescription(),
                booking.getTours().getStartDate(),
                booking.getTours().getEndDate(),
                booking.getTours().getTourPrice(),
                booking.getHotels().getHotelId(),
                booking.getHotels().getHotelName(),
                booking.getHotels().getHotelAddress(),
                booking.getHotels().getHotelPrice());
    }




    public Booking mapToBookingForUpdate(final long bookingId, BookingDTOCreate bookingDTOCreate) {
        Booking booking = bookingRepository.findById(bookingId).orElseThrow(
                BookingNotFoundException::new);
        if (booking != null) {
            booking.setStartDate(bookingDTOCreate.getStartDate());
            booking.setEndDate(bookingDTOCreate.getEndDate());
            booking.setCustomer(customerRepository.findById(bookingDTOCreate.getCustomerId()).orElseThrow(CustomerNotFoundException::new));
            booking.setTours(tourRepository.findById(bookingDTOCreate.getTourId()).orElseThrow(TourNotFoundException::new));
            booking.setHotels(hotelRepository.findById(bookingDTOCreate.getHotelId()).orElseThrow(HotelNotFoundException::new));
            return booking;
        } else {
            throw new BookingNotFoundException();
        }
    }
    public List<BookingDTOGet> mapToDTOListGet(final List<Booking>bookings){
        return bookings.stream()
                .map(this::mapToBookingDTOGet)
                .toList();
    }

}