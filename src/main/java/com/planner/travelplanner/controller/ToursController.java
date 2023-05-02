package com.planner.travelplanner.controller;

import com.planner.travelplanner.domain.dto.ToursDTO;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("v1/tours")
public class ToursController {

    public ToursController() {
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addTour(@RequestBody ToursDTO toursDTO){
        return  ResponseEntity.ok().build();
    }

    @GetMapping()
    public ResponseEntity<List<ToursDTO>>getAllTours(){
        return ResponseEntity.ok(Collections.emptyList());
    }

    @GetMapping(value = "tourId")
    public ResponseEntity<ToursDTO>getTourById(@PathVariable Long tourId){
        return ResponseEntity.ok(null);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ToursDTO>updateTour(@RequestBody ToursDTO toursDTO){
        return ResponseEntity.ok(null);
    }

    @DeleteMapping
    public ResponseEntity<Void>deleteTour(@PathVariable Long tourId){
        return ResponseEntity.ok().build();
    }

}
