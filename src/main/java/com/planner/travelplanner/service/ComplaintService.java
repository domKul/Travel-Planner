package com.planner.travelplanner.service;

import com.planner.travelplanner.domain.Complaint;
import com.planner.travelplanner.domain.dto.complaint.ComplaintDTO;
import com.planner.travelplanner.domain.dto.complaint.ComplaintDTOCreate;
import com.planner.travelplanner.domain.dto.complaint.ComplaintDTOUpdate;
import com.planner.travelplanner.domain.exception.ComplaintNotFoundException;
import com.planner.travelplanner.domain.exception.CustomerNotFoundException;
import com.planner.travelplanner.mapper.ComplaintMapper;
import com.planner.travelplanner.repository.ComplaintRepository;
import com.planner.travelplanner.repository.CustomerRepository;
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
    public Complaint createComplaint(final long customerId, final ComplaintDTOCreate complaintDTOCreate){
        if (customerRepository.existsById(customerId)){
        Complaint complaint = complaintMapper.mapToComplaintCreate(complaintDTOCreate);

        return complaintRepository.save(complaint);
        }else {
            throw new CustomerNotFoundException();
        }
    }
    public List<ComplaintDTO>showAllComplaints(){
        return complaintMapper.mapToListDTO(complaintRepository.findAll());
    }

    public ComplaintDTO showComplaintById(final long complaintId){
        if (complaintRepository.existsById(complaintId)){
            return complaintMapper.mapToComplaintDTOFormShow(complaintRepository.findById(complaintId).get());
        }else {
            throw new ComplaintNotFoundException();
        }
    }
    @Transactional
    public ComplaintDTO modifyComplaintStatus(final long complaintId, final ComplaintDTOUpdate complaintDTOUpdate){
        Complaint complaint = complaintRepository.findById(complaintId).orElseThrow(
                ComplaintNotFoundException::new);
        if (complaint != null){
            complaint.setComplaintId(complaintId);
                complaint.setTitle(complaintDTOUpdate.getTitle());
                complaint.setStatus(complaintDTOUpdate.getStatus());
                complaint.setDescription(complaintDTOUpdate.getDescription());
            Complaint update = complaintRepository.save(complaint);
            return complaintMapper.mapToComplaintDTO(update);
        }else {
            throw new ComplaintNotFoundException();
        }
    }

    public void deleteComplainByID(final long complaintId){
        Complaint findComplaint = complaintRepository.findById(complaintId).orElseThrow(ComplaintNotFoundException::new);
        complaintRepository.deleteById(findComplaint.getComplaintId());
    }



}
