package com.planner.travelplanner.destination;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/hotel")
class DestinationSearchController {

    private final DestinationRestService destinationRestService;

    DestinationSearchController(DestinationRestService destinationRestService) {
        this.destinationRestService = destinationRestService;
    }

    @GetMapping("/destinationSave")
    @ResponseBody
    ResponseEntity<DestinationlDTO> searchHotelsSave(@RequestParam String orderedby, @RequestParam int adults_number, @RequestParam String checkin_date,
                                                     @RequestParam String filter_by_currency, @RequestParam int dest_id,
                                                     @RequestParam String locale, @RequestParam String checkout_date, @RequestParam String units, @RequestParam int room_number, @RequestParam String dest_type) {
        return destinationRestService.searchHotelWithSaveToData(orderedby, adults_number, checkin_date,
                filter_by_currency, dest_id,
                locale, checkout_date, units, room_number, dest_type);
    }

    @GetMapping("/destinations")
    @ResponseBody
    ResponseEntity<DestinationlDTO> searchHotels(@RequestParam String orderedby, @RequestParam int adults_number, @RequestParam String checkin_date,
                                                 @RequestParam String filter_by_currency, @RequestParam int dest_id,
                                                 @RequestParam String locale, @RequestParam String checkout_date, @RequestParam String units, @RequestParam int room_number, @RequestParam String dest_type) {
        return destinationRestService.searchHotel(orderedby, adults_number, checkin_date,
                filter_by_currency, dest_id,
                locale, checkout_date, units, room_number, dest_type);
    }
}
