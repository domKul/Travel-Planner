package com.planner.travelplanner.controller;

import com.planner.travelplanner.domain.dto.hotel.HotelDTOForGet;
import com.planner.travelplanner.service.HotelService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/hotels")
public class HotelsController {
    private final HotelService hotelService;

    public HotelsController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @GetMapping()
    public ResponseEntity<List<HotelDTOForGet>> getAllHotelsFromDB() {
        return ResponseEntity.ok(hotelService.getAllHotelsInDB());
    }

    @GetMapping(value = "{hotelId}")
    public ResponseEntity<HotelDTOForGet> getHotelById(@PathVariable Long hotelId) {
        return ResponseEntity.ok(hotelService.getHotelById(hotelId));
    }

    @DeleteMapping(value = "{hotelId}")
    public ResponseEntity<Void> deleteHotel(@PathVariable Long hotelId) {
        hotelService.deleteHotelById(hotelId);
        return ResponseEntity.ok().build();
    }
}
