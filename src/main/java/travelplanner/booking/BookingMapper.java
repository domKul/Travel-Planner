package travelplanner.booking;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
class BookingMapper {

    BookingDTO mapToBookingDTO(Booking booking) {
        return new BookingDTO(
                booking.getStartDate(),
                booking.getEndDate(),
                booking.getCustomer(),
                booking.getDestinations());
    }

    BookingDTOGet mapToBookingDTOGet(Booking booking) {
        return new BookingDTOGet.Builder()
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
                .hotelId(booking.getDestinations().getDestinationId())
                .hotelName(booking.getDestinations().getName())
                .hotelId(booking.getDestinations().getIdName())
                .startBooking(booking.getStartDate())
                .endBooking(booking.getEndDate())
                .hotelPrice(String.valueOf(booking.getDestinations().getDestinationPrice()))
                .currency(booking.getDestinations().getCurrency())
                .build();
    }

    List<BookingDTOGet> mapToDTOListGet(final List<Booking> bookings) {
        return bookings.stream()
                .map(this::mapToBookingDTOGet)
                .toList();
    }
}
