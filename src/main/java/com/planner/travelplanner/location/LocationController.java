package com.planner.travelplanner.location;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/locations")
class LocationController {

    private final LocationRestService locationRestService;
    private final LocationService locationService;

    LocationController(LocationRestService locationRestService, LocationService locationService) {
        this.locationRestService = locationRestService;
        this.locationService = locationService;
    }

    @PostMapping("/getHotelslocation")
    ResponseEntity<Void> searchHotels(@RequestParam String name, @RequestParam String locale) {
        locationRestService.searchLocations(name, locale);
        return ResponseEntity.ok().build();
    }

    @GetMapping("getLocations")
    ResponseEntity<List<LocationDTO>> findLocationsInDB() {
        return ResponseEntity.ok(locationService.getLocationsFromDB());
    }

    @GetMapping("findById")
    ResponseEntity<LocationDTO> findLocationFromDataBaseById(long locationId) {
        return ResponseEntity.ok(locationService.getLocationById(locationId));
    }
}
