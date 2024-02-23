package travelplanner.booking;

import org.springframework.stereotype.Component;
import travelplanner.booking.query.BookingDTO;
import travelplanner.booking.query.BookingDTOGet;

import java.util.List;

@Component
class BookingMapper {

    BookingDTO mapToBookingDTO(Booking booking) {
        return new BookingDTO(
                booking.getStartDate(),
                booking.getEndDate(),
                booking.getCustomer(),
                booking.getDestination());
    }

    BookingDTOGet mapToBookingDTOGet(Booking booking) {
        return  BookingDTOGet.builder()
                .bookingId(booking.getBookingId())
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
                .hotelId(booking.getDestination().getDestinationId())
                .hotelName(booking.getDestination().getName())
                .hotelId(booking.getDestination().getIdName())
                .startBooking(booking.getStartDate())
                .endBooking(booking.getEndDate())
                .hotelPrice(String.valueOf(booking.getDestination().getDestinationPrice()))
                .currency(booking.getDestination().getCurrency())
                .build();
    }

    List<BookingDTOGet> mapToDTOListGet(final List<Booking> bookings) {
        return bookings.stream()
                .map(this::mapToBookingDTOGet)
                .toList();
    }
}
