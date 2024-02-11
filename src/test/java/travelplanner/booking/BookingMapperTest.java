package travelplanner.booking;

import travelplanner.booking.query.BookingDTO;
import travelplanner.booking.query.BookingDTOGet;
import travelplanner.customer.query.SimpleCustomerQueryDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import travelplanner.destination.query.SimpleDestinationQueryDto;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;


@SpringBootTest
class BookingMapperTest {

    @Autowired
    private BookingMapper bookingMapper;
    private Booking booking1;
    private Booking booking2;
    private SimpleCustomerQueryDto customer;
    private SimpleDestinationQueryDto destination;



    private void dataForTests() {
        customer = new SimpleCustomerQueryDto(1L, "firstName", "lastName", new Date(2020, 02, 02), "string", "string", "string", "string", "example12@email.com", 1231231);
        destination = new SimpleDestinationQueryDto(1L,1231L,"name","countryCode","currency",1231);
        booking1 = new Booking(1L, new Date(2020, 12, 12), new Date(2020, 12, 13), customer, destination);
        booking2 = new Booking(1L, new Date(2020, 12, 12), new Date(2020, 12, 13), customer, destination);
    }

    @Test
    public void shouldMapToBookingDTOGet() {
        //Given
        dataForTests();
        //When
        BookingDTOGet mapToDTOGET = bookingMapper.mapToBookingDTOGet(booking2);
        //When
        assertEquals(BookingDTOGet.class,mapToDTOGET.getClass());
        assertEquals(booking2.getCustomer().getFirstName(), mapToDTOGET.getCustomerFirstName());
        assertEquals(booking2.getCustomer().getLastName(), mapToDTOGET.getCustomerLastName());
        assertEquals(booking2.getCustomer().getCustomerId(), mapToDTOGET.getCustomerId());
    }

    @Test
    public void shouldMapToDTOListGet() {
        //Given
        dataForTests();
        List<Booking> listOfBookings = List.of(booking1, booking2);
        //When
        List<BookingDTOGet> mappinglist = bookingMapper.mapToDTOListGet(listOfBookings);
        //Then
        assertFalse(mappinglist.isEmpty());
        assertEquals(listOfBookings.get(0).getCustomer().getFirstName(), mappinglist.get(0).getCustomerFirstName());
    }

    @Test
    public void shouldMapToBookingDTO() {
        //Given
        dataForTests();
        //When
        BookingDTO mappingDTO = bookingMapper.mapToBookingDTO(booking2);
        //Then
        assertEquals(booking2.getCustomer().getFirstName(), mappingDTO.getCustomer().getFirstName());
    }
}
