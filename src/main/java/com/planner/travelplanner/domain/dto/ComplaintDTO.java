package com.planner.travelplanner.domain.dto;

import com.planner.travelplanner.domain.Customer;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class ComplaintDTO {


    private Long complaintId;

    private String title;

    private String description;

    private LocalDateTime complaintDate;

    private String status;

    private Customer customer;

    public ComplaintDTO(Long complaintId, String title, String description, LocalDateTime complaintDate, String status, Customer customer) {
        this.complaintId = complaintId;
        this.title = title;
        this.description = description;
        this.complaintDate = complaintDate;
        this.status = status;
        this.customer = customer;
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

    public Customer getCustomer() {
        return customer;
    }
}
