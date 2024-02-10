package travelplanner.booking.query;

import jakarta.persistence.*;
import travelplanner.customer.query.SimpleCustomerQueryDto;
import travelplanner.destination.query.SimpleDestinationQueryDto;

import java.util.Date;

@Entity
@Table(name = "bookings")
public class SimpleBookingQueryDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;
    private Date startDate;
    @Column(name = "end_date")
    private Date endDate;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id")
    private SimpleCustomerQueryDto customer;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "destination_id")
    private SimpleDestinationQueryDto destination;

    public SimpleBookingQueryDto() {
    }

    public SimpleBookingQueryDto(long bookingId, Date startDate, Date endDate, SimpleCustomerQueryDto customer, SimpleDestinationQueryDto destination) {
        this.bookingId = bookingId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.customer = customer;
        this.destination = destination;
    }

    public Long getBookingId() {
        return bookingId;
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

    public SimpleDestinationQueryDto getDestination() {
        return destination;
    }
}
