package travelplanner.booking.query;

import lombok.Builder;
import lombok.Getter;

import java.util.Date;
@Builder
@Getter
public class BookingDTOGet {

    private Long bookingId;
    private Date bookTime;
    private Long customerId;
    private String customerFirstName;
    private String customerLastName;
    private Date birthDate;
    private String country;
    private String city;
    private String streetName;
    private String postalCode;
    private String email;
    private int phoneNumber;
    private String hotelName;
    private long hotelId;
    private Date startBooking;
    private Date endBooking;
    private String hotelPrice;
    private String currency;
}
