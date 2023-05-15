package com.planner.travelplanner.mapper;

import com.planner.travelplanner.domain.Complaint;
import com.planner.travelplanner.domain.dto.complaint.ComplaintDTO;
import com.planner.travelplanner.domain.dto.complaint.ComplaintDTOCreate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ComplaintMapper {

    public Complaint mapFromComplaintCreate(final ComplaintDTOCreate complaintDTOCreate){
        return new Complaint(IdType.EMPTY_ID.getId(),
                complaintDTOCreate.getTitle(),
                complaintDTOCreate.getDescription(),
                complaintDTOCreate.getComplaintDate(),
                complaintDTOCreate.getStatus(),
                complaintDTOCreate.getCustomerId());
    }

    public ComplaintDTO mapToComplaintDTO(final Complaint complaint){
        return new ComplaintDTO(
                complaint.getComplaintId(),
                complaint.getTitle(),
                complaint.getDescription(),
                complaint.getComplaintDate(),
                complaint.getStatus(),
                complaint.getComplaintId());
    }
    public ComplaintDTO mapToComplaintDTOFormShow(final Complaint complaint){
        return new ComplaintDTO(
                complaint.getComplaintId(),
                complaint.getTitle(),
                complaint.getDescription(),
                complaint.getComplaintDate(),
                complaint.getStatus(),
                complaint.getComplaintId());
    }

    public List<ComplaintDTO> mapToListDTO(final List<Complaint>complaints){
        return complaints.stream()
                .map(this::mapToComplaintDTOFormShow)
                .toList();
    }
}
