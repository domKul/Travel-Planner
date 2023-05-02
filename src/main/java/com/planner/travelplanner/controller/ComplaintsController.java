package com.planner.travelplanner.controller;

import com.planner.travelplanner.domain.dto.ComplaintsDTO;
import com.planner.travelplanner.domain.dto.HotelsDTO;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("v1/complaints")
public class ComplaintsController {

    public ComplaintsController() {
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addComplaints(@RequestBody ComplaintsDTO complaintsDTO){
        return  ResponseEntity.ok().build();
    }

    @GetMapping()
    public ResponseEntity<List<ComplaintsDTO>>getAllComplaints(){
        return ResponseEntity.ok(Collections.emptyList());
    }

    @GetMapping(value = "hotelId")
    public ResponseEntity<HotelsDTO>getComplaintsById(@PathVariable Long complaintId){
        return ResponseEntity.ok(null);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ComplaintsDTO>updateComplaints(@RequestBody ComplaintsDTO complaintsDTO){
        return ResponseEntity.ok(null);
    }

    @DeleteMapping
    public ResponseEntity<Void>deleteComplaints(@PathVariable Long complaintsDTO){
        return ResponseEntity.ok().build();
    }
}
