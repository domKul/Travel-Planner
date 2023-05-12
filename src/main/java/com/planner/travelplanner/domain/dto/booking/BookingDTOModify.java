package com.planner.travelplanner.domain.dto.booking;

import java.time.LocalDate;

public class BookingDTOModify {

    private long customerId;

    private long tourId;

    private long hotelId;
    private LocalDate startDate;

    private LocalDate endDate;

    public BookingDTOModify(long customerId, long tourId, long hotelId, LocalDate startDate, LocalDate endDate) {
        this.customerId = customerId;
        this.tourId = tourId;
        this.hotelId = hotelId;
        this.startDate = startDate;
        this.endDate = endDate;
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

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }
}
