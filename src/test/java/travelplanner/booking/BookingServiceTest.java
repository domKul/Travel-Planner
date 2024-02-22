package travelplanner.booking;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import travelplanner.booking.query.BookingDTO;
import travelplanner.booking.query.BookingDTOGet;
import travelplanner.customer.CustomerService;
import travelplanner.customer.query.SimpleCustomerQueryDto;
import travelplanner.destination.DestinationService;
import travelplanner.destination.query.SimpleDestinationQueryDto;
import travelplanner.exception.NotFoundException;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class BookingServiceTest {

    @InjectMocks
    private BookingService bookingService;
    private BookingDTOCreate bookingDTOCreate;
    @Mock
    private BookingRepository bookingRepository;
    @Mock
    private DestinationService destinationService;
    @Mock
    private CustomerService customerService;
    @Mock
    private BookingMapper bookingMapper;
    private Booking booking;
    private SimpleCustomerQueryDto customer;
    private SimpleDestinationQueryDto destination;

    @Test
    public void testAddBooking_ValidInput_Success() {
        // Given
        SimpleCustomerQueryDto customer = new SimpleCustomerQueryDto();
        BookingDTOCreate bookingDTOCreate = new BookingDTOCreate(new Date(2024, 1, 1), new Date(2024, 1, 2), 1L, 1L);
        SimpleDestinationQueryDto destination = new SimpleDestinationQueryDto();
        Booking booking = new Booking(1L, new Date(2020, 12, 12), new Date(2020, 12, 13), customer, destination);
        when(customerService.findCustomerOrThrow(bookingDTOCreate.getCustomerId())).thenReturn(customer);
        when(destinationService.getDestinationOrElseThrow(bookingDTOCreate.getDestinationId())).thenReturn(destination);
        when(bookingRepository.save(any(Booking.class))).thenReturn(booking);
        // When
        Booking result = bookingService.addBooking(bookingDTOCreate);
        // Then
        Assertions.assertNotNull(result);
        assertEquals(customer, result.getCustomer());
        assertEquals(destination, result.getDestination());
    }

    @Test
    public void shouldModifyBooking() {
        // Given
        SimpleDestinationQueryDto destination = new SimpleDestinationQueryDto();
        Booking booking = new Booking(1L, new Date(2020, 12, 12), new Date(2020, 12, 13), customer, destination);
        BookingDTO bookingUpdate = new BookingDTO(new Date(2020, 12, 12), new Date(2020, 12, 13), customer, destination);
        BookingDTOCreate create = new BookingDTOCreate(new Date(2022, 12, 12), new Date(2020, 12, 13), 2L, 2L);

        when(bookingRepository.existsById(booking.getBookingId())).thenReturn(true);
        when(bookingRepository.findById(booking.getBookingId())).thenReturn(Optional.of(booking));
        when(customerService.findCustomerOrThrow(create.getCustomerId())).thenReturn(customer);
        when(destinationService.getDestinationOrElseThrow(create.getDestinationId())).thenReturn(destination);
        when(bookingRepository.save(any(Booking.class))).thenReturn(booking);
        when(bookingMapper.mapToBookingDTO(booking)).thenReturn(bookingUpdate);
        // When
        BookingDTO modify = bookingService.modifyBooking(booking.getBookingId(), create);
        // Then
        assertEquals(bookingUpdate.customer(), modify.customer());
        assertEquals(bookingUpdate.startDate(), modify.startDate());
        assertEquals(bookingUpdate.endDate(), modify.endDate());
        verify(bookingRepository).existsById(booking.getBookingId());
        verify(bookingRepository).findById(booking.getBookingId());
        verify(customerService).findCustomerOrThrow(create.getCustomerId());
        verify(destinationService).getDestinationOrElseThrow(create.getDestinationId());
        verify(bookingRepository).save(any(Booking.class));
        verify(bookingMapper).mapToBookingDTO(booking);
    }

    @Test
    public void shouldDeleteBookingById() {
        // Given
        Booking booking = new Booking(1L, new Date(2020, 12, 12), new Date(2020, 12, 13), customer, destination);
        long getIdForDelete = booking.getBookingId();
        when(bookingRepository.findById(getIdForDelete)).thenReturn(Optional.of(booking));
        doNothing().when(bookingRepository).deleteById(getIdForDelete);
        // When
        bookingService.deleteBookingById(getIdForDelete);
        //Then
        verify(bookingRepository,times(1)).findById(getIdForDelete);
        verify(bookingRepository,times(1)).deleteById(getIdForDelete);
    }

    @Test
    public void testShowBookingById() {
        // Given
        customer = new SimpleCustomerQueryDto(1L,
                "firstName",
                "lastName",
                new Date(2020, 02, 02),
                "string", "string",
                "string",
                "string",
                "string@gmail.com",
                1231231);
        destination = new SimpleDestinationQueryDto(1L, 1231L, "name", "countryCode", "currency", 1231);
        Booking booking = new Booking(1L, new Date(2020, 12, 12), new Date(2020, 12, 13), customer, destination);
        BookingDTOGet getBooking =  BookingDTOGet.builder()
                .bookingId(1L)
                .bookTime(new Date(2024, 1, 1))
                .customerId(customer.getCustomerId())
                .customerFirstName(customer.getFirstName())
                .customerLastName(customer.getLastName())
                .birthDate(customer.getBirthdate())
                .country(customer.getCountry())
                .city(customer.getCity())
                .streetName(customer.getStreetName())
                .postalCode(customer.getPostalCode())
                .email(customer.getEmail())
                .phoneNumber(customer.getPhoneNumber())
                .hotelId(destination.getDestinationId())
                .hotelName(destination.getName())
                .startBooking(new Date(2024, 1, 1))
                .endBooking(new Date(2024, 1, 2))
                .hotelPrice(String.valueOf(destination.getDestinationPrice()))
                .currency(destination.getCurrency())
                .build();
        long bookingId = booking.getBookingId();
        when(bookingRepository.findById(bookingId)).thenReturn(Optional.of(booking));
        when(bookingMapper.mapToBookingDTOGet(booking)).thenReturn(getBooking);
        // When
        BookingDTOGet bookingDTO = bookingService.showBookingById(bookingId);
        // Then
        assertEquals(booking.getCustomer().getCustomerId(), bookingDTO.getCustomerId());
        verify(bookingRepository,times(1)).findById(bookingId);
        verify(bookingMapper,times(1)).mapToBookingDTOGet(booking);
    }

    @Test
    public void shouldShowAllBookings() {
        //Given
        Booking booking1 = new Booking();
        Booking booking2 = new Booking();
        Booking booking3 = new Booking();
        BookingDTOGet mappedBooking1 =  BookingDTOGet.builder().build();
        BookingDTOGet mappedBooking2 =  BookingDTOGet.builder().build();
        BookingDTOGet mappedBooking3 =  BookingDTOGet.builder().build();
        List<Booking> bookingsList = List.of(booking1, booking2, booking3);
        List<BookingDTOGet> mappedBookingsList = List.of(mappedBooking1, mappedBooking2, mappedBooking3);
        when(bookingRepository.findAll()).thenReturn(bookingsList);
        when(bookingMapper.mapToDTOListGet(bookingsList)).thenReturn(mappedBookingsList);
        //When
        List<BookingDTOGet> findAllList = bookingService.showAllBookings();
        //Then
        assertEquals(3, findAllList.size());
        verify(bookingRepository,times(1)).findAll();
        verify(bookingMapper,times(1)).mapToDTOListGet(bookingsList);
    }

    @Test
    public void testShowBookingByIdThrowsException() {
        // Given
        long bookingId = 123;
        // When & Then
        assertThrows(NotFoundException.class, () -> {
            bookingService.showBookingById(bookingId);
        });
    }
}
