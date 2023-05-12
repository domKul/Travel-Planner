package com.planner.travelplanner.controller;

import com.planner.travelplanner.service.restTemplate.LocationRestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/locations")
public class LocationController {

    private final LocationRestService locationRestService;


    public LocationController(LocationRestService locationRestService) {
        this.locationRestService = locationRestService;
    }

    @PostMapping("/getHotels")
    public ResponseEntity<Void> searchHotels(@RequestParam String name, @RequestParam String locale) {
        locationRestService.searchLocations(name, locale);
        return ResponseEntity.ok().build();
    }

}
