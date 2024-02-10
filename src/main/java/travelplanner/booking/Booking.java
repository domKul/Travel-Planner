package travelplanner.booking;


import jakarta.persistence.*;
import travelplanner.booking.query.SimpleBookingQueryDto;
import travelplanner.customer.query.SimpleCustomerQueryDto;
import travelplanner.destination.query.SimpleDestinationQueryDto;


import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "bookings")
 class Booking {

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

    public Booking() {
    }

    Booking(long bookingId, Date startDate, Date endDate, SimpleCustomerQueryDto customer, SimpleDestinationQueryDto destination) {
        this.bookingId = bookingId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.customer = customer;
        this.destination = destination;
    }

    long getBookingId() {
        return bookingId;
    }

    Date getStartDate() {
        return startDate;
    }

    void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    Date getEndDate() {
        return endDate;
    }

    void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    SimpleCustomerQueryDto getCustomer() {
        return customer;
    }

    void setCustomer(SimpleCustomerQueryDto customer) {
        this.customer = customer;
    }

    SimpleDestinationQueryDto getDestinations() {
        return destination;
    }

    void setDestinations(SimpleDestinationQueryDto destination) {
        this.destination = destination;
    }
     SimpleBookingQueryDto toSimpleQuery() {
        return new SimpleBookingQueryDto(
                this.bookingId,
                this.startDate,
                this.endDate,
                this.customer,
                this.destination
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return  Objects.equals(startDate, booking.startDate) &&
                Objects.equals(endDate, booking.endDate) &&
                Objects.equals(customer, booking.customer) &&
                Objects.equals(destination, booking.destination);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookingId, startDate, endDate, customer, destination);
    }
}
