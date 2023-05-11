package com.planner.travelplanner.domain.dto.complaint;

import com.planner.travelplanner.domain.Customer;

import java.time.LocalDateTime;

public class ComplaintDTO {


    private Long complaintId;

    private String title;

    private String description;

    private LocalDateTime complaintDate;

    private String status;

    private long customer;

    public ComplaintDTO(Long complaintId, String title, String description, LocalDateTime complaintDate, String status, long customerId) {
        this.complaintId = complaintId;
        this.title = title;
        this.description = description;
        this.complaintDate = complaintDate;
        this.status = status;
        this.customer = customerId;
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

    public long getCustomer() {
        return customer;
    }
}
