package com.planner.travelplanner.service;

import com.planner.travelplanner.domain.Complaint;
import com.planner.travelplanner.domain.dto.ComplaintDTO;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ComplaintService {

    private final ComplaintService complaintService;

    public Complaint createComplaint(ComplaintDTO complaintDTO){

    }


}
