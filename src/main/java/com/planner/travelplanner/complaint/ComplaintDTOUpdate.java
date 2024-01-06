package com.planner.travelplanner.complaint;

import java.time.LocalDateTime;

public class ComplaintDTOUpdate {

    private final String title;
    private final String description;
    private final LocalDateTime complaintDate;
    private final String status;

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
