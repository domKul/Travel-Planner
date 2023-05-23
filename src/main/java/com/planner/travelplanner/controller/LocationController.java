package com.planner.travelplanner.controller;

import com.planner.travelplanner.domain.dto.location.LocationDTO;
import com.planner.travelplanner.service.LocationService;
import com.planner.travelplanner.service.restTemplate.LocationRestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/locations")
public class LocationController {

    private final LocationRestService locationRestService;
    private final LocationService locationService;


    public LocationController(LocationRestService locationRestService, LocationService locationService) {
        this.locationRestService = locationRestService;
        this.locationService = locationService;
    }

    @PostMapping("/getHotelslocation")
    public ResponseEntity<Void> searchHotels(@RequestParam String name, @RequestParam String locale) {
        locationRestService.searchLocations(name, locale);
        return ResponseEntity.ok().build();
    }

    @GetMapping("getLocations")
    public ResponseEntity<List<LocationDTO>>findLocationsInDB(){
        return ResponseEntity.ok(locationService.getLocationsFromDB());
    }

    @GetMapping("findById")
    public ResponseEntity<LocationDTO> findLocationFromDataBaseById(long locationId) {
        return ResponseEntity.ok(locationService.getLocationById(locationId));
    }

}
