package travelplanner.complaint.query;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import travelplanner.customer.query.SimpleCustomerQueryDto;


import java.time.LocalDateTime;

@Entity
@Table(name = "complaints")
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

    public Long getComplaintId() {
        return complaintId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getComplaintDate() {
        return complaintDate;
    }

    public String getStatus() {
        return status;
    }

    public SimpleCustomerQueryDto getCustomer() {
        return customer;
    }
}
