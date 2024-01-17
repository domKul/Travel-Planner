package com.planner.travelplanner.complaint;

import com.planner.travelplanner.customer.Customer;
import com.planner.travelplanner.enums.IdType;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
class ComplaintMapper {

    public static Complaint mapFromComplaintCreate( final ComplaintDTOCreate complaintDTOCreate) {
        Complaint complaint = new Complaint();
        complaint.setComplaintId(IdType.EMPTY_ID.getId());
        complaint.setTitle(complaintDTOCreate.title());
        complaint.setDescription(complaintDTOCreate.description());
        complaint.setComplaintDate(complaintDTOCreate.complaintDate());
        complaint.setStatus(complaintDTOCreate.status());

        Customer customer = new Customer();
        customer.setCustomerId(complaintDTOCreate.customerId());
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
