package com.planner.travelplanner.controller;

import com.planner.travelplanner.domain.dto.destination.DestinationDTOForGet;
import com.planner.travelplanner.service.DestinationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/destinations")
public class DestinationsController {
    private final DestinationService destinationService;

    public DestinationsController(DestinationService destinationService) {
        this.destinationService = destinationService;
    }

    @GetMapping()
    public ResponseEntity<List<DestinationDTOForGet>> getAllDestinationsFromDB() {
        return ResponseEntity.ok(destinationService.getAllDestinationsInDB());
    }

    @GetMapping(value = "{destinationId}")
    public ResponseEntity<DestinationDTOForGet> getDestinationById(@PathVariable long destinationId) {
        return ResponseEntity.ok(destinationService.getDestinationById(destinationId));
    }

    @DeleteMapping(value = "{destinationId}")
    public ResponseEntity<Void> deleteDestination(@PathVariable long destinationId) {
        destinationService.deleteDestinationById(destinationId);
        return ResponseEntity.ok().build();
    }
}
