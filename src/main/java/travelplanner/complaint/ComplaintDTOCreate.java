package travelplanner.complaint;

import java.time.LocalDateTime;

class ComplaintDTOCreate {

    private final String title;
    private final String description;
    private final LocalDateTime complaintDate;
    private final String status;
    private final Long customerId;

    ComplaintDTOCreate(String title, String description, LocalDateTime complaintDate, String status, long customerId) {
        this.title = title;
        this.description = description;
        this.complaintDate = LocalDateTime.now();
        this.status = status;
        this.customerId = customerId;
    }

    long getCustomer() {
        return customerId;
    }

    String getTitle() {
        return title;
    }

    String getDescription() {
        return description;
    }

    LocalDateTime getComplaintDate() {
        return complaintDate;
    }

    String getStatus() {
        return status;
    }


    long getCustomerId() {
        return customerId;
    }
}
