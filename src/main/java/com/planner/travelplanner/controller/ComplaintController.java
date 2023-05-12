package com.planner.travelplanner.controller;

import com.planner.travelplanner.domain.dto.complaint.ComplaintDTO;
import com.planner.travelplanner.domain.dto.complaint.ComplaintDTOCreate;
import com.planner.travelplanner.domain.dto.complaint.ComplaintDTOUpdate;
import com.planner.travelplanner.service.ComplaintService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/complaints")
public class ComplaintController {
    private final ComplaintService complaintService;

    public ComplaintController(ComplaintService complaintService) {
        this.complaintService = complaintService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addComplaints(@RequestParam Long customerId,@RequestBody ComplaintDTOCreate complaintDTOCreate){
        complaintService.createComplaint(customerId,complaintDTOCreate);
        return  ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping()
    public ResponseEntity<List<ComplaintDTO>>getAllComplaints(){
        return ResponseEntity.ok(complaintService.showAllComplaints());
    }

    @GetMapping(value = "{complaintId}")
    public ResponseEntity<ComplaintDTO>getComplaintsById(@PathVariable Long complaintId){
        return ResponseEntity.ok(complaintService.showComplaintById(complaintId));
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ComplaintDTO> updateComplaintsStatus(@RequestParam long complaintId,@RequestBody ComplaintDTOUpdate complaintDTOUpdate){
        return ResponseEntity.ok(complaintService.modifyComplaintStatus(complaintId,complaintDTOUpdate));
    }

    @DeleteMapping(value = "{coplaintId}")
    public ResponseEntity<Void>deleteComplaints(@PathVariable Long coplaintId){
        complaintService.deleteComplainByID(coplaintId);
        return ResponseEntity.ok().build();
    }
}
