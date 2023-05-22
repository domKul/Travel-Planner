package com.planner.travelplanner.mapper;

import com.planner.travelplanner.domain.Booking;
import com.planner.travelplanner.domain.dto.booking.BookingDTO;
import com.planner.travelplanner.domain.dto.booking.BookingDTOGet;
import com.planner.travelplanner.repository.BookingRepository;
import com.planner.travelplanner.repository.CustomerRepository;
import com.planner.travelplanner.repository.HotelRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookingMapper {
    private final BookingRepository bookingRepository;
    private final CustomerRepository customerRepository;
    private final HotelRepository hotelRepository;

    public BookingMapper(BookingRepository bookingRepository, CustomerRepository customerRepository, HotelRepository hotelRepository) {
        this.bookingRepository = bookingRepository;
        this.customerRepository = customerRepository;
        this.hotelRepository = hotelRepository;
    }

    public BookingDTO mapToBookingDTO(Booking booking){
        return new BookingDTO(
                booking.getStartDate(),
                booking.getEndDate(),
                booking.getCustomer(),
                booking.getHotels());
    }
    public BookingDTOGet mapToBookingDTOGet(Booking booking) {
        return new BookingDTOGet.Builder()
                .bookTime(booking.getStartDate())
                .customerId(booking.getCustomer().getCustomerId())
                .customerFirstName(booking.getCustomer().getFirstName())
                .customerLastName(booking.getCustomer().getLastName())
                .birthDate(booking.getCustomer().getBirthdate())
                .country(booking.getCustomer().getCountry())
                .city(booking.getCustomer().getCity())
                .streetName(booking.getCustomer().getStreetName())
                .postalCode(booking.getCustomer().getPostalCode())
                .email(booking.getCustomer().getEmail())
                .phoneNumber(booking.getCustomer().getPhoneNumber())
                .hotelId(booking.getHotels().getHotelId())
                .hotelName(booking.getHotels().getName())
                .hotelId(booking.getHotels().getIdName())
                .startBooking(booking.getStartDate())
                .endBooking(booking.getEndDate())
                .hotelPrice(String.valueOf(booking.getHotels().getHotelPrice()))
                .build();
    }

    public List<BookingDTOGet> mapToDTOListGet(final List<Booking>bookings){
        return bookings.stream()
                .map(this::mapToBookingDTOGet)
                .toList();
    }

}
