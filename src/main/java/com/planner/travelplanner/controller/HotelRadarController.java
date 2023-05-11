package com.planner.travelplanner.controller;

import com.planner.travelplanner.service.restTemplate.LocationRestService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HotelRadarController {


    private final LocationRestService locationRestService;


    public HotelRadarController(LocationRestService locationRestService) {
        this.locationRestService = locationRestService;
    }

    @GetMapping("/hotels")
    public ResponseEntity<Void> searchHotels(@RequestParam String name, @RequestParam String locale) {
        return locationRestService.searchLocations(name, locale);
    }




}
