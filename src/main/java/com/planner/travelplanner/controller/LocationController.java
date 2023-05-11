package com.planner.travelplanner.controller;

import com.planner.travelplanner.domain.dto.customer.LocationDTO;
import com.planner.travelplanner.mapper.LocationMapper;
import com.planner.travelplanner.service.restTemplate.LocationRestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("v1/location")
public class LocationController {

    private final LocationRestService locationRestService;
    private final LocationMapper locationMapper;

    public LocationController(LocationRestService locationRestService, LocationMapper locationMapper) {
        this.locationRestService = locationRestService;
        this.locationMapper = locationMapper;
    }

//    @PostMapping("/save")
//    public ResponseEntity<Void> saveLocations(@RequestBody List<LocationDTO> locationDTOs) {
//        locationRestService.saveLocations(locationDTOs);
//        return new ResponseEntity<>(HttpStatus.CREATED);
//    }
}
