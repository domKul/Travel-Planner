package com.planner.travelplanner.booking;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.planner.travelplanner.customer.Customer;
import com.planner.travelplanner.destination.Destination;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringJUnitWebConfig
@WebMvcTest(BookingsController.class)
class BookingControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private BookingService bookingService;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private BookingsController bookingsController;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(bookingsController)
                .setMessageConverters(new MappingJackson2HttpMessageConverter(objectMapper))
                .build();
    }

    private String asJsonString(Object obj) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }


    @Test
    void shouldGetALLBooking() throws Exception {
        //Given
        List<BookingDTOGet> bookingDTOGetList = List.of(
                new BookingDTOGet.Builder().build(),
                new BookingDTOGet.Builder().build());

        given(bookingService.showAllBookings()).willReturn(bookingDTOGetList);
        // When & Then
        mockMvc.perform(get("/v1/bookings")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2));
    }

    @Test
    void shouldSaveBooking() throws Exception {
        //Given
        Customer customer = new Customer(1, "firstName", "lastName", Calendar.getInstance().getTime(), "string", "string", "string", "string", "string", 1231231, new ArrayList<>());
        Destination destination = new Destination();
        BookingDTOCreate create = new BookingDTOCreate.Builder().build();
        Booking booking = new Booking(1L, new Date(), new Date(), customer, destination);
        // When & Then
        mockMvc.perform(post("/v1/bookings")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(create)))
                .andExpect(status().isCreated());
    }

    @Test
    void shouldFindBookingByGivenId() throws Exception {
        // Given
        Customer customer = new Customer(1, "firstName", "lastName", Calendar.getInstance().getTime(), "string", "string", "string", "string", "string", 1231231, new ArrayList<>());
        Destination destination = new Destination();
        BookingDTOCreate create = new BookingDTOCreate.Builder().build();
        Booking booking = new Booking(1L, new Date(), new Date(), customer, destination);
        when(bookingService.showBookingById(booking.getBookingId())).thenReturn(new BookingDTOGet.Builder()
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
                .hotelName(booking.getDestinations().getName())
                .hotelId(booking.getDestinations().getIdName())
                .startBooking(booking.getStartDate())
                .endBooking(booking.getEndDate())
                .hotelPrice(String.valueOf(booking.getDestinations().getDestinationPrice()))
                .currency(booking.getDestinations().getCurrency())
                .build());

        // When
        mockMvc.perform(get("/v1/bookings/{bookingId}", booking.getBookingId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customerId").value(customer.getCustomerId()))
                .andExpect(jsonPath("$.email").value(customer.getEmail()));
    }

    @Test
    void shouldDeleteBooking() throws Exception {
        //Given
        long bookingId = 1L;

        //When
        mockMvc.perform(delete("/v1/bookings/{bookingId}", bookingId))
                .andExpect(status().isAccepted());

        //Then
        verify(bookingService,times(1)).deleteBookingById(bookingId);
    }



}
