package com.planner.travelplanner.complaint;

import com.planner.travelplanner.customer.CustomerRepository;
import com.planner.travelplanner.exception.ComplaintNotFoundException;
import com.planner.travelplanner.exception.CustomerNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComplaintService {

    private final ComplaintRepository complaintRepository;
    private final CustomerRepository customerRepository;
    private final ComplaintMapper complaintMapper;

    public ComplaintService(ComplaintRepository complaintRepository, CustomerRepository customerRepository, ComplaintMapper complaintMapper) {
        this.complaintRepository = complaintRepository;
        this.customerRepository = customerRepository;
        this.complaintMapper = complaintMapper;
    }

    @Transactional
    public Complaint createComplaint(final ComplaintDTOCreate complaintDTOCreate) {
        if (customerRepository.existsById(complaintDTOCreate.getCustomerId())) {
            Complaint complaint = ComplaintMapper.mapFromComplaintCreate( complaintDTOCreate);
            return complaintRepository.save(complaint);
        } else {
            throw new CustomerNotFoundException();
        }
    }

    public List<ComplaintDTO> showAllComplaints() {
        return complaintMapper.mapToListDTO(complaintRepository.findAll());
    }

    public ComplaintDTO showComplaintById(final long complaintId) {
        if (complaintRepository.existsById(complaintId)) {
            return complaintMapper.mapToComplaintDTOFormShow(complaintRepository.findById(complaintId).get());
        } else {
            throw new ComplaintNotFoundException();
        }
    }

    @Transactional
    public ComplaintDTO modifyComplaintStatus(final long complaintId, final ComplaintDTOUpdate complaintDTOUpdate) {
        Complaint complaint = complaintRepository.findById(complaintId).orElseThrow(
                ComplaintNotFoundException::new);
        if (complaint != null) {
            complaint.setComplaintId(complaintId);
            complaint.setTitle(complaintDTOUpdate.getTitle());
            complaint.setStatus(complaintDTOUpdate.getStatus());
            complaint.setDescription(complaintDTOUpdate.getDescription());
            Complaint update = complaintRepository.save(complaint);
            return complaintMapper.mapToComplaintDTO(update);
        } else {
            throw new ComplaintNotFoundException();
        }
    }

    @Transactional
    public void deleteComplainByID(final long complaintId) {
        Complaint findComplaint = complaintRepository.findById(complaintId).orElseThrow(ComplaintNotFoundException::new);
        complaintRepository.deleteById(findComplaint.getComplaintId());
    }


}
