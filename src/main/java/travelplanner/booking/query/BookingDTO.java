package travelplanner.booking.query;


import travelplanner.customer.query.SimpleCustomerQueryDto;
import travelplanner.destination.query.SimpleDestinationQueryDto;

import java.util.Date;


public class BookingDTO {

    private final Date startDate;
    private final Date endDate;
    private final SimpleCustomerQueryDto customer;
    private final SimpleDestinationQueryDto destination;

    public BookingDTO(Date startDate, Date endDate, SimpleCustomerQueryDto customer, SimpleDestinationQueryDto destination) {
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

    public SimpleCustomerQueryDto getCustomer() {
        return customer;
    }

    public SimpleDestinationQueryDto getHotel() {
        return destination;
    }
}
