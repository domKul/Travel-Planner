package travelplanner.complaint;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/complaints")
class ComplaintController {

    private final ComplaintService complaintService;

    ComplaintController(ComplaintService complaintService) {
        this.complaintService = complaintService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Void> addComplaints(@RequestBody @Valid ComplaintDTOCreate complaintDTOCreate) {
        complaintService.createComplaint(complaintDTOCreate);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping()
    ResponseEntity<List<ComplaintDTO>> getAllComplaints() {
        return ResponseEntity.ok(complaintService.showAllComplaints());
    }

    @GetMapping(value = "{complaintId}")
    ResponseEntity<ComplaintDTO> getComplaintsById(@PathVariable long complaintId) {
        return ResponseEntity.ok(complaintService.showComplaintById(complaintId));
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ComplaintDTO> updateComplaintsStatus(@RequestParam long complaintId, @RequestBody ComplaintDTOUpdate complaintDTOUpdate) {
        return ResponseEntity.ok(complaintService.putComplaint(complaintId, complaintDTOUpdate));
    }

    @DeleteMapping(value = "{complaintId}")
    ResponseEntity<Void> deleteComplaints(@PathVariable long complaintId) {
        complaintService.deleteComplainByID(complaintId);
        return ResponseEntity.ok().build();
    }
}
