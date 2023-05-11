package com.planner.travelplanner.domain.dto.complaint;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.planner.travelplanner.domain.Customer;

import java.time.LocalDateTime;

public class ComplaintDTOUpdate {


    private String title;

    private String description;

    private LocalDateTime complaintDate;

    private String status;


    public ComplaintDTOUpdate(String title, String description, LocalDateTime complaintDate, String status) {
        this.title = title;
        this.description = description;
        this.complaintDate = complaintDate;
        this.status = status;
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
}
