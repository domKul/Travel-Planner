package travelplanner.complaint;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import travelplanner.complaint.query.SimpleComplaintQueryDto;
import travelplanner.customer.query.SimpleCustomerQueryDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "complaints")
@Getter
@Setter
@EqualsAndHashCode
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

    Complaint mapFromSimpleQueryDto(SimpleComplaintQueryDto simpleComplaintQueryDto) {
        return new Complaint(simpleComplaintQueryDto.getComplaintId(),
                simpleComplaintQueryDto.getTitle(),
                simpleComplaintQueryDto.getDescription(),
                simpleComplaintQueryDto.getComplaintDate(),
                simpleComplaintQueryDto.getStatus(),
                simpleComplaintQueryDto.getComplaintId()
        );
    }

    List<Complaint> mapFromSimpleQueryDtoList(List<SimpleComplaintQueryDto> simpleComplaintQueryDto) {
        return simpleComplaintQueryDto.stream().map(this::mapFromSimpleQueryDto).toList();
    }
}
