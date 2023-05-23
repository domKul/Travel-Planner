package com.planner.travelplanner.controller;

import com.planner.travelplanner.domain.dto.hotel.HotelDTO;
import com.planner.travelplanner.service.restTemplate.HotelRestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/hotel")
public class HotelRadarController {

    private final HotelRestService hotelRestService;

    public HotelRadarController(HotelRestService hotelRestService) {
        this.hotelRestService = hotelRestService;
    }


    @GetMapping("/hotelsSave")
    @ResponseBody
    public ResponseEntity<HotelDTO> searchHotelsSave(@RequestParam String orderedby, @RequestParam int adults_number, @RequestParam String checkin_date,
                                                     @RequestParam String filter_by_currency, @RequestParam int dest_id,
                                                     @RequestParam String locale, @RequestParam String checkout_date, @RequestParam String units, @RequestParam int room_number, @RequestParam String dest_type) {
        return hotelRestService.searchHotelWithSaveToData(orderedby, adults_number, checkin_date,
                filter_by_currency, dest_id,
                locale, checkout_date, units, room_number, dest_type);
    }

    @GetMapping("/hotels")
    @ResponseBody
    public ResponseEntity<HotelDTO> searchHotels(@RequestParam String orderedby, @RequestParam int adults_number, @RequestParam String checkin_date,
                                                 @RequestParam String filter_by_currency, @RequestParam int dest_id,
                                                 @RequestParam String locale, @RequestParam String checkout_date, @RequestParam String units, @RequestParam int room_number, @RequestParam String dest_type) {
        return hotelRestService.searchHotel(orderedby, adults_number, checkin_date,
                filter_by_currency, dest_id,
                locale, checkout_date, units, room_number, dest_type);
    }


}
