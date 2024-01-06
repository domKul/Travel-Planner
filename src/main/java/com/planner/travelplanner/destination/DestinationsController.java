package com.planner.travelplanner.destination;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/destinations")
class DestinationsController {

    private final DestinationService destinationService;

    DestinationsController(DestinationService destinationService) {
        this.destinationService = destinationService;
    }

    @GetMapping()
    ResponseEntity<List<DestinationDTOForGet>> getAllDestinationsFromDB() {
        return ResponseEntity.ok(destinationService.getAllDestinationsInDB());
    }

    @GetMapping(value = "{destinationId}")
    ResponseEntity<DestinationDTOForGet> getDestinationById(@PathVariable long destinationId) {
        return ResponseEntity.ok(destinationService.getDestinationById(destinationId));
    }

    @DeleteMapping(value = "{destinationId}")
    ResponseEntity<Void> deleteDestination(@PathVariable long destinationId) {
        destinationService.deleteDestinationById(destinationId);
        return ResponseEntity.ok().build();
    }
}
