package travelplanner.complaint.query;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import travelplanner.customer.query.SimpleCustomerQueryDto;

import java.time.LocalDateTime;

@Entity
@Table(name = "complaints")
@Getter
public class SimpleComplaintQueryDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long complaintId;
    private String title;
    private String description;
    private LocalDateTime complaintDate;
    private String status;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id")
    @JsonBackReference
    private SimpleCustomerQueryDto customer;

    public SimpleComplaintQueryDto() {
    }

    public SimpleComplaintQueryDto(long complaintId, String title, String description, LocalDateTime complaintDate, String status, long customerId) {
        this.complaintId = complaintId;
        this.title = title;
        this.description = description;
        this.complaintDate = complaintDate;
        this.status = status;
        this.customer = new SimpleCustomerQueryDto();
        this.customer.setCustomerId(customerId);
    }
}
