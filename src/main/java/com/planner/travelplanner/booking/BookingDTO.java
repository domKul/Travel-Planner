package com.planner.travelplanner.booking;

import com.planner.travelplanner.customer.Customer;
import com.planner.travelplanner.destination.Destination;

import java.util.Date;


public class BookingDTO {

    private final Date startDate;
    private final Date endDate;
    private final Customer customer;
    private final Destination destination;

    public BookingDTO(Date startDate, Date endDate, Customer customer, Destination destination) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.customer = customer;
        this.destination = destination;
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

    public Destination getHotel() {
        return destination;
    }
}
