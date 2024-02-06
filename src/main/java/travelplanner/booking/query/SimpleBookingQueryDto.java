package travelplanner.booking.query;

import jakarta.persistence.*;
import travelplanner.customer.Customer;
import travelplanner.destination.Destination;

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
    private Customer customer;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "destination_id")
    private Destination destination;

    public SimpleBookingQueryDto() {
    }

    public SimpleBookingQueryDto(long bookingId, Date startDate, Date endDate, Customer customer, Destination destination) {
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

    public Customer getCustomer() {
        return customer;
    }

    public Destination getDestination() {
        return destination;
    }
}
