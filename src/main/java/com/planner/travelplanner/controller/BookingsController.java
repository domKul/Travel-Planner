package com.planner.travelplanner.controller;


import com.planner.travelplanner.domain.dto.HotelsDTO;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("v1/bookings")
public class BookingsController {
    public BookingsController() {
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addBooking(@RequestBody BookingsDTO bookingsDTO){
        return  ResponseEntity.ok().build();
    }

    @GetMapping()
    public ResponseEntity<List<BookingsDTO>>getAllBookings(){
        return ResponseEntity.ok(Collections.emptyList());
    }

    @GetMapping(value = "bookingId")
    public ResponseEntity<BookingsDTO>getBookingById(@PathVariable Long bookingId){
        return ResponseEntity.ok(null);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HotelsDTO>updateBooking(@RequestBody BookingsDTO bookingsDTO){
        return ResponseEntity.ok(null);
    }

    @DeleteMapping
    public ResponseEntity<Void>deleteBooking(@PathVariable Long bookingId){
        return ResponseEntity.ok().build();
    }

}
