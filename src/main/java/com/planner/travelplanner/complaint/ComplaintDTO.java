package com.planner.travelplanner.complaint;

import java.time.LocalDateTime;

record ComplaintDTO(Long complaintId,
        String title,
        String description,
        LocalDateTime complaintDate,
        String status,
        Long customerId) {

}
