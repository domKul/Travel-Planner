package com.planner.travelplanner.controller;

import com.planner.travelplanner.domain.dto.location.LocationDTO;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("v1/hotels")
public class HotelsController {

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addHotel(@RequestBody LocationDTO locationDTO){
        return  ResponseEntity.ok().build();
    }

    @GetMapping()
    public ResponseEntity<List<LocationDTO>>getAllHotels(){
        return ResponseEntity.ok(Collections.emptyList());
    }

    @GetMapping(value = "hotelId")
    public ResponseEntity<LocationDTO>getHotelById(@PathVariable Long hotelId){
        return ResponseEntity.ok(null);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LocationDTO>updateHotel(@RequestBody LocationDTO locationDTO){
        return ResponseEntity.ok(null);
    }

    @DeleteMapping
    public ResponseEntity<Void>deleteHotel(@PathVariable Long hotelId){
        return ResponseEntity.ok().build();
    }
}
