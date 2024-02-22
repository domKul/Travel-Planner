package travelplanner.booking;


import jakarta.persistence.*;
import lombok.*;
import travelplanner.booking.query.SimpleBookingQueryDto;
import travelplanner.customer.query.SimpleCustomerQueryDto;
import travelplanner.destination.query.SimpleDestinationQueryDto;


import java.util.Date;


@Entity
@Table(name = "bookings")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
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

    SimpleBookingQueryDto toSimpleQuery() {
        return new SimpleBookingQueryDto(
                this.bookingId,
                this.startDate,
                this.endDate,
                this.customer,
                this.destination
        );
    }
}
