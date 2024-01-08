package com.planner.travelplanner.complaint;

import java.time.LocalDateTime;

public class ComplaintDTO {

    private final Long complaintId;
    private final String title;
    private final String description;
    private final LocalDateTime complaintDate;
    private final String status;
    private final Long customerId;

    public ComplaintDTO(Long complaintId, String title, String description, LocalDateTime complaintDate, String status, long customerId) {
        this.complaintId = complaintId;
        this.title = title;
        this.description = description;
        this.complaintDate = complaintDate;
        this.status = status;
        this.customerId = customerId;
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

    public long getCustomerId() {
        return customerId;
    }
}
