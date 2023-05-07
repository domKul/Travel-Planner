package com.planner.travelplanner.controller;


import com.planner.travelplanner.domain.dto.BookingDTO;
import com.planner.travelplanner.domain.dto.BookingDTOCreate;
import com.planner.travelplanner.service.BookingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/bookings")
public class BookingsController {
    private final BookingService bookingService;

    public BookingsController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addBooking(@RequestBody BookingDTOCreate bookingDTOCreate){
        bookingService.addBooking(bookingDTOCreate);
        return  ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping()
    public ResponseEntity<List<BookingDTO>>getAllBookings(){
        return ResponseEntity.ok(bookingService.showAllBookings());
    }

    @GetMapping(value = "bookingId")
    public ResponseEntity<BookingDTO>getBookingById(@PathVariable Long bookingId){
        return ResponseEntity.ok(bookingService.showBookingById(bookingId));
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookingDTO>updateBooking(@RequestParam Long bookingId, @RequestBody BookingDTO bookingDTO){
        return ResponseEntity.ok(bookingService.modifyBooking(bookingId, bookingDTO));
    }

    @DeleteMapping
    public ResponseEntity<Void>deleteBooking(@PathVariable Long bookingId){
        return ResponseEntity.ok().build();
    }

}
