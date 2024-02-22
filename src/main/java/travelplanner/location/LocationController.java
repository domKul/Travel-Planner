package travelplanner.location;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/locations")
@RequiredArgsConstructor
class LocationController {

    private final LocationClientService locationClientService;
    private final LocationService locationService;

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
