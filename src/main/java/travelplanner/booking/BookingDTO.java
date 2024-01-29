package travelplanner.booking;

import travelplanner.customer.Customer;
import travelplanner.destination.Destination;

import java.util.Date;


class BookingDTO {

    private final Date startDate;
    private final Date endDate;
    private final Customer customer;
    private final Destination destination;

    BookingDTO(Date startDate, Date endDate, Customer customer, Destination destination) {
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

    Customer getCustomer() {
        return customer;
    }

    Destination getHotel() {
        return destination;
    }
}
