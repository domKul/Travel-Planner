package travelplanner.destination;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/hotel")
@RequiredArgsConstructor
class DestinationSearchController {

    private final DestinationClientService destinationClientService;

    @GetMapping("/destinationSave")
    ResponseEntity<DestinationlDTO> searchHotelsSave(@RequestParam String orderedby, @RequestParam int adults_number, @RequestParam String checkin_date,
                                                     @RequestParam String filter_by_currency, @RequestParam int dest_id,
                                                     @RequestParam String locale, @RequestParam String checkout_date, @RequestParam String units, @RequestParam int room_number, @RequestParam String dest_type) {
        return destinationClientService.searchHotelWithSaveToData(orderedby, adults_number, checkin_date,
                filter_by_currency, dest_id,
                locale, checkout_date, units, room_number, dest_type);
    }

    @GetMapping("/destinations")
    ResponseEntity<DestinationlDTO> searchHotels(@RequestParam String orderedby, @RequestParam int adults_number, @RequestParam String checkin_date,
                                                 @RequestParam String filter_by_currency, @RequestParam int dest_id,
                                                 @RequestParam String locale, @RequestParam String checkout_date, @RequestParam String units, @RequestParam int room_number, @RequestParam String dest_type) {
        return destinationClientService.searchHotel(orderedby, adults_number, checkin_date,
                filter_by_currency, dest_id,
                locale, checkout_date, units, room_number, dest_type);
    }
}
