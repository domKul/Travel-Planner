package com.planner.travelplanner.mapper;

import com.planner.travelplanner.domain.Complaint;
import com.planner.travelplanner.domain.dto.ComplaintDTO;
import org.springframework.stereotype.Component;

@Component
public class ComplaintMapper {

    public Complaint mapToComplaint(final ComplaintDTO complaintDTO){
        return new Complaint(complaintDTO.getComplaintId(),
                complaintDTO.getTitle(),
                complaintDTO.getDescription(),
                complaintDTO.getComplaintDate(),
                complaintDTO.getStatus(),
                complaintDTO.getCustomer());
    }

    public ComplaintDTO mapToComplaintDTO(final Complaint complaint){
        return new ComplaintDTO(complaint.getComplaintId(),
                complaint.getTitle(),
                complaint.getDescription(),
                complaint.getComplaintDate(),
                complaint.getStatus(),
                complaint.getCustomer());
    }
}
