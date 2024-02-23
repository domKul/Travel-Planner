package travelplanner.complaint;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
@AllArgsConstructor
@Getter
class ComplaintDTO {

    private Long complaintId;
    private String title;
    private String description;
    private LocalDateTime complaintDate;
    private String status;
    private Long customerId;
}
