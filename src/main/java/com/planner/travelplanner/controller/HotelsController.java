package com.planner.travelplanner.controller;

import com.planner.travelplanner.domain.dto.HotelsDTO;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("v1/hotels")
public class HotelsController {
    public HotelsController() {
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addHotel(@RequestBody HotelsDTO hotelsDTO){
        return  ResponseEntity.ok().build();
    }

    @GetMapping()
    public ResponseEntity<List<HotelsDTO>>getAllHotels(){
        return ResponseEntity.ok(Collections.emptyList());
    }

    @GetMapping(value = "hotelId")
    public ResponseEntity<HotelsDTO>getHotelById(@PathVariable Long hotelId){
        return ResponseEntity.ok(null);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HotelsDTO>updateHotel(@RequestBody HotelsDTO hotelsDTO){
        return ResponseEntity.ok(null);
    }

    @DeleteMapping
    public ResponseEntity<Void>deleteHotel(@PathVariable Long hotelId){
        return ResponseEntity.ok().build();
    }
}
