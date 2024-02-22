package travelplanner.booking.query;


import travelplanner.customer.query.SimpleCustomerQueryDto;
import travelplanner.destination.query.SimpleDestinationQueryDto;

import java.util.Date;

public record BookingDTO(Date startDate, Date endDate, SimpleCustomerQueryDto customer,
                         SimpleDestinationQueryDto destination) {

}
