package com.planner.travelplanner.domain.dto.booking;

import com.planner.travelplanner.domain.Customer;
import com.planner.travelplanner.domain.Hotel;

import java.util.Date;


public class BookingDTO {

    private Date startDate;

    private Date endDate;

    private Customer customer;



    private Hotel hotel;

    public BookingDTO(Date startDate, Date endDate, Customer customer, Hotel hotel) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.customer = customer;
        this.hotel = hotel;
    }



    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Hotel getHotel() {
        return hotel;
    }
}
