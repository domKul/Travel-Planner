package travelplanner.complaint;

import org.springframework.stereotype.Component;
import travelplanner.customer.CustomerService;
import travelplanner.enums.IdType;

import java.util.List;

@Component
class ComplaintMapper {

    Complaint mapFromComplaintCreate(final ComplaintDTOCreate complaintDTOCreate, CustomerService customerService) {
        Complaint complaint = new Complaint();
        complaint.setComplaintId(IdType.EMPTY_ID.getId());
        complaint.setTitle(complaintDTOCreate.getTitle());
        complaint.setDescription(complaintDTOCreate.getDescription());
        complaint.setComplaintDate(complaintDTOCreate.getComplaintDate());
        complaint.setStatus(complaintDTOCreate.getStatus());
        complaint.setCustomer(customerService.findCustomerOrThrow(complaintDTOCreate.getCustomerId()));
        return complaint;
    }

     ComplaintDTO mapToComplaintDTO(final Complaint complaint) {
        long customerId = (complaint.getCustomer() != null) ? complaint.getCustomer().getCustomerId() : 0;
        return new ComplaintDTO(
                complaint.getComplaintId(),
                complaint.getTitle(),
                complaint.getDescription(),
                complaint.getComplaintDate(),
                complaint.getStatus(),
                customerId);
    }

     ComplaintDTO mapToComplaintDTOFormShow(final Complaint complaint) {
        return new ComplaintDTO(
                complaint.getComplaintId(),
                complaint.getTitle(),
                complaint.getDescription(),
                complaint.getComplaintDate(),
                complaint.getStatus(),
                complaint.getCustomer().getCustomerId());
    }

     List<ComplaintDTO> mapToListDTO(final List<Complaint> complaints) {
        return complaints.stream()
                .map(this::mapToComplaintDTOFormShow)
                .toList();
    }
}
