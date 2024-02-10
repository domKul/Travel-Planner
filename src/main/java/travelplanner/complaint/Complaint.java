package travelplanner.complaint;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import travelplanner.complaint.query.SimpleComplaintQueryDto;
import travelplanner.customer.query.SimpleCustomerQueryDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "complaints")
 class Complaint {

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

    public Complaint() {
    }

    public Complaint(long complaintId, String title, String description, LocalDateTime complaintDate, String status, long customerId) {
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

    public void setComplaintId(Long complaintId) {
        this.complaintId = complaintId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getComplaintDate() {
        return complaintDate;
    }

    public void setComplaintDate(LocalDateTime complaintDate) {
        this.complaintDate = complaintDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public SimpleCustomerQueryDto getCustomer() {
        return customer;
    }

    public void setCustomer(SimpleCustomerQueryDto customer) {
        this.customer = customer;
    }

    Complaint mapFromSimpleQueryDto(SimpleComplaintQueryDto simpleComplaintQueryDto){
        return new Complaint(simpleComplaintQueryDto.getComplaintId(),
                simpleComplaintQueryDto.getTitle(),
                simpleComplaintQueryDto.getDescription(),
                simpleComplaintQueryDto.getComplaintDate(),
                simpleComplaintQueryDto.getStatus(),
                simpleComplaintQueryDto.getComplaintId()
        );
    }
    List<Complaint> mapFromSimpleQueryDtoList(List<SimpleComplaintQueryDto> simpleComplaintQueryDto){
        return simpleComplaintQueryDto.stream().map(this::mapFromSimpleQueryDto).toList();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Complaint that = (Complaint) o;
        return Objects.equals(complaintId, that.complaintId) && Objects.equals(title, that.title) && Objects.equals(description, that.description) && Objects.equals(complaintDate, that.complaintDate) && Objects.equals(status, that.status) && Objects.equals(customer, that.customer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(complaintId, title, description, complaintDate, status, customer);
    }
}
