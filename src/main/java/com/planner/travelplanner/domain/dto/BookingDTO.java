package com.planner.travelplanner.domain.dto;

import com.planner.travelplanner.domain.Customer;
import com.planner.travelplanner.domain.Hotel;
import com.planner.travelplanner.domain.Tour;

import java.time.LocalDate;

public class BookingDTO {


    private long bookingId;

    private LocalDate startDate;

    private LocalDate endDate;

    private Customer customer;

    private Tour tour;

    private Hotel hotel;

    public BookingDTO(long bookingId, LocalDate startDate, LocalDate endDate, Customer customer, Tour tour, Hotel hotel) {
        this.bookingId = bookingId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.customer = customer;
        this.tour = tour;
        this.hotel = hotel;
    }

    public long getBookingId() {
        return bookingId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Tour getTour() {
        return tour;
    }

    public Hotel getHotel() {
        return hotel;
    }
}
