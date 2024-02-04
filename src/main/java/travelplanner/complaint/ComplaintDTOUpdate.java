package travelplanner.complaint;

import java.time.LocalDateTime;

record ComplaintDTOUpdate(String title,
                          String description,
                          LocalDateTime complaintDate,
                          String status) {

}
