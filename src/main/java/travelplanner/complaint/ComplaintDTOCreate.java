package travelplanner.complaint;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDateTime;
@Getter
class ComplaintDTOCreate {
    @NotNull(message = "Title required")
    private final String title;
    @NotNull(message = "Description required")
    private final String description;
    @NotNull(message = "Date required")
    private final LocalDateTime complaintDate;

    private final String status;
    @NotNull(message = "Traveler id required")
    private final Long customerId;

    ComplaintDTOCreate(String title, String description, String status, long customerId) {
        this.title = title;
        this.description = description;
        this.complaintDate = LocalDateTime.now();
        this.status = status;
        this.customerId = customerId;
    }
}
