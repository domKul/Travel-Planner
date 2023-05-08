package com.planner.travelplanner.domain.dto.booking;

import java.time.LocalDate;

public class BookingDTOCreate {
    private LocalDate startDate;

    private LocalDate endDate;

    private long customerId;

    private long tourId;

    private long hotelId;

    public BookingDTOCreate(LocalDate startDate, LocalDate endDate, long customerId, long tourId, long hotelId) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.customerId = customerId;
        this.tourId = tourId;
        this.hotelId = hotelId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public long getCustomerId() {
        return customerId;
    }

    public long getTourId() {
        return tourId;
    }

    public long getHotelId() {
        return hotelId;
    }
}
