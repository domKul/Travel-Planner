package travelplanner.location;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/locations")
class LocationController {

    private final LocationClientService locationClientService;
    private final LocationService locationService;

    LocationController(LocationClientService locationClientService, LocationService locationService) {
        this.locationClientService = locationClientService;
        this.locationService = locationService;
    }

    @PostMapping("/getHotelslocation")
    ResponseEntity<Void> searchHotels(@RequestParam String name, @RequestParam String locale) {
        locationClientService.searchLocations(name, locale);
        return ResponseEntity.ok().build();
    }

    @GetMapping("getLocations")
    ResponseEntity<List<LocationDTO>> findLocationsInDB() {
        return ResponseEntity.ok(locationService.getLocationsFromDB());
    }

    @GetMapping("{locationId}")
    ResponseEntity<LocationDTO> findLocationFromDataBaseById(@PathVariable long locationId) {
        return ResponseEntity.ok(locationService.getLocationById(locationId));
    }
}
