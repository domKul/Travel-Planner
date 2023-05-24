package com.planner.travelplanner.mapper;

import com.planner.travelplanner.domain.Complaint;
import com.planner.travelplanner.domain.Customer;
import com.planner.travelplanner.domain.dto.complaint.ComplaintDTO;
import com.planner.travelplanner.domain.dto.complaint.ComplaintDTOCreate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ComplaintMapper {

    public static Complaint mapFromComplaintCreate(long customerId, final ComplaintDTOCreate complaintDTOCreate) {
        Complaint complaint = new Complaint();
        complaint.setComplaintId(IdType.EMPTY_ID.getId());
        complaint.setTitle(complaintDTOCreate.getTitle());
        complaint.setDescription(complaintDTOCreate.getDescription());
        complaint.setComplaintDate(complaintDTOCreate.getComplaintDate());
        complaint.setStatus(complaintDTOCreate.getStatus());

        Customer customer = new Customer();
        customer.setCustomerId(customerId);
        complaint.setCustomer(customer);

        return complaint;
    }

    public ComplaintDTO mapToComplaintDTO(final Complaint complaint) {
        long customerId = (complaint.getCustomer() != null) ? complaint.getCustomer().getCustomerId() : 0;

        return new ComplaintDTO(
                complaint.getComplaintId(),
                complaint.getTitle(),
                complaint.getDescription(),
                complaint.getComplaintDate(),
                complaint.getStatus(),
                customerId);
    }

    public ComplaintDTO mapToComplaintDTOFormShow(final Complaint complaint) {
        return new ComplaintDTO(
                complaint.getComplaintId(),
                complaint.getTitle(),
                complaint.getDescription(),
                complaint.getComplaintDate(),
                complaint.getStatus(),
                complaint.getCustomer().getCustomerId());
    }

    public List<ComplaintDTO> mapToListDTO(final List<Complaint> complaints) {
        return complaints.stream()
                .map(this::mapToComplaintDTOFormShow)
                .toList();
    }
}
