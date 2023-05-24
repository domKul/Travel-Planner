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
        return ResponseEntity.ok(destinationService.getAllHotelsInDB());
    }

    @GetMapping(value = "{destinationId}")
    public ResponseEntity<DestinationDTOForGet> getDestinationById(@PathVariable Long destinationId) {
        return ResponseEntity.ok(destinationService.getHotelById(destinationId));
    }

    @DeleteMapping(value = "{destinationId}")
    public ResponseEntity<Void> deleteDestination(@PathVariable Long destinationId) {
        destinationService.deleteHotelById(destinationId);
        return ResponseEntity.ok().build();
    }
}
