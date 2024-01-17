package com.planner.travelplanner.complaint;

import java.time.LocalDateTime;

record ComplaintDTOCreate(String title,
        String description,
        LocalDateTime complaintDate,
        String status,
        Long customerId) {

}
