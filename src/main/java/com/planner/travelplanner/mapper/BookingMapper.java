package com.planner.travelplanner.mapper;

import com.planner.travelplanner.domain.Booking;
import com.planner.travelplanner.domain.dto.BookingDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingMapper {

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

    public List<BookingDTO> mapToDTOList(final List<Booking>bookings){
        return bookings.stream()
                .map(this::mapToBookingDTO)
                .toList();
    }

}
