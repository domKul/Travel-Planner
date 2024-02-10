package travelplanner.booking;


import travelplanner.customer.query.SimpleCustomerQueryDto;
import travelplanner.destination.query.SimpleDestinationQueryDto;

import java.util.Date;


class BookingDTO {

    private final Date startDate;
    private final Date endDate;
    private final SimpleCustomerQueryDto customer;
    private final SimpleDestinationQueryDto destination;

    BookingDTO(Date startDate, Date endDate, SimpleCustomerQueryDto customer, SimpleDestinationQueryDto destination) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.customer = customer;
        this.destination = destination;
    }

    Date getStartDate() {
        return startDate;
    }

     Date getEndDate() {
        return endDate;
    }

    SimpleCustomerQueryDto getCustomer() {
        return customer;
    }

    SimpleDestinationQueryDto getHotel() {
        return destination;
    }
}
