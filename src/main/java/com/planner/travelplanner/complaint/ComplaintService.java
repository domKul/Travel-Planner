package com.planner.travelplanner.complaint;

import com.planner.travelplanner.customer.CustomerService;
import com.planner.travelplanner.enums.ExceptionMessages;
import com.planner.travelplanner.exception.NotFoundException;
import com.planner.travelplanner.jpa.AbstractRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ComplaintService extends AbstractRepository<ComplaintRepository, Complaint> {

    private final ComplaintRepository complaintRepository;
    private final ComplaintMapper complaintMapper;
    private final CustomerService customerService;

     ComplaintService(ComplaintRepository complaintRepository, ComplaintMapper complaintMapper, CustomerService customerService) {
        this.complaintRepository = complaintRepository;
        this.complaintMapper = complaintMapper;
        this.customerService = customerService;
    }


    Complaint createComplaint(final ComplaintDTOCreate complaintDTOCreate) {
        if (!customerService.isCustomerExistById(complaintDTOCreate.getCustomerId())) {
            throw new NotFoundException(ExceptionMessages.ENTITY_NOT_FOUND);
        }
        Complaint complaint = ComplaintMapper.mapFromComplaintCreate(complaintDTOCreate);
        return complaintRepository.save(complaint);
    }

    List<ComplaintDTO> showAllComplaints() {
        return complaintMapper.mapToListDTO(complaintRepository.findAll());
    }

     ComplaintDTO showComplaintById(final long complaintId) {
        Complaint entity = findEntity(complaintRepository, complaintId);
        return complaintMapper.mapToComplaintDTO(entity);
    }

    ComplaintDTO putComplaintStatus(final long complaintId, final ComplaintDTOUpdate complaintDTOUpdate) {
        return complaintRepository.findById(complaintId).map(comp -> {
            Optional.ofNullable(complaintDTOUpdate.title())
                    .ifPresent(comp::setTitle);
            Optional.ofNullable(complaintDTOUpdate.description())
                    .ifPresent(comp::setDescription);
            Optional.ofNullable(complaintDTOUpdate.complaintDate())
                    .ifPresent(comp::setComplaintDate);
            Optional.ofNullable(complaintDTOUpdate.status())
                    .ifPresent(comp::setStatus);
            Complaint update = complaintRepository.save(comp);
            return complaintMapper.mapToComplaintDTO(update);
        }).orElseThrow(() -> new NotFoundException(ExceptionMessages.ENTITY_NOT_FOUND));
    }

    void deleteComplainByID(final long complaintId) {
        Complaint entity = findEntity(complaintRepository, complaintId);
        complaintRepository.deleteById(entity.getComplaintId());
    }
}
