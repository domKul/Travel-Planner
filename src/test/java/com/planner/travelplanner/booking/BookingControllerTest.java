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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

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
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/");
        viewResolver.setSuffix(".jsp");
        mockMvc = MockMvcBuilders.standaloneSetup(bookingsController)
                .setViewResolvers(viewResolver)
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
        when(bookingService.addBooking(create)).thenReturn(booking);
        // When & Then
        mockMvc.perform(post("/v1/bookings")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(create)))
                .andExpect(status().isCreated());
    }

    @Test
    void shouldFindBookingByGivenId() throws Exception {
        Customer customer = new Customer(1, "firstName", "lastName", Calendar.getInstance().getTime(), "string", "string", "string", "string", "string", 1231231, new ArrayList<>());
        Destination destination = new Destination();
        BookingDTOCreate create = new BookingDTOCreate.Builder().build();
        Booking booking = new Booking(1L, new Date(), new Date(), customer, destination);
        BookingDTOGet getBooking = BookingDTOGet.builder()
                .bookingId(booking.getBookingId())
                .customerFirstName(booking.getCustomer().getFirstName())
                .customerLastName(booking.getCustomer().getLastName())
                .build();

        when(bookingService.addBooking(create)).thenReturn(booking);
        when(bookingService.showBookingById(getBooking.getBookingId())).thenReturn(getBooking);
        // When & Then
        mockMvc.perform(get("/v1/bookings/" + getBooking.getBookingId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.bookingId").value(getBooking.getBookingId()))
                .andExpect(jsonPath("$.customerFirstName").value(getBooking.getCustomerFirstName()))
                .andExpect(jsonPath("$.customerLastName").value(getBooking.getCustomerLastName()));
    }

    @Test
    void shouldDeleteBookingByGivenId() throws Exception {
        //Given
        BookingDTOCreate create = new BookingDTOCreate.Builder().build();
        Booking booking = new Booking(1L, new Date(), new Date(), new Customer(), new Destination());
        when(bookingService.addBooking(create)).thenReturn(booking);

        //When
        mockMvc.perform(delete("/v1/bookings/" + booking.getBookingId()))
                .andExpect(status().isAccepted());
        //Then
        verify(bookingService).deleteBookingById(booking.getBookingId());
    }

    @Test
    void shouldModifyBooking() throws Exception {
        //Given
        long bookingId = 1L;
        BookingDTOCreate userUpdateDTO = new BookingDTOCreate(new Date(),new Date(),12,12);

        // When
        mockMvc.perform(MockMvcRequestBuilders.put("/v1/bookings/" + bookingId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userUpdateDTO)))
                .andExpect(status().isAccepted());
    }

}
