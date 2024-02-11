package travelplanner.booking;


import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import travelplanner.booking.query.BookingDTO;
import travelplanner.booking.query.BookingDTOGet;

import java.util.List;

@RestController
@RequestMapping("v1/bookings")
class BookingsController {

    private final BookingService bookingService;

    BookingsController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Void> addBooking(@RequestBody BookingDTOCreate bookingDTOCreate) {
        bookingService.addBooking(bookingDTOCreate);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping()
    ResponseEntity<List<BookingDTOGet>> getAllBookings() {
        return ResponseEntity.ok(bookingService.showAllBookings());
    }

    @GetMapping(value = "{bookingId}")
    ResponseEntity<BookingDTOGet> getBookingById(@PathVariable long bookingId) {
        return ResponseEntity.ok(bookingService.showBookingById(bookingId));
    }

    @PutMapping(value = "{bookingId}",consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<BookingDTO> updateBooking(@PathVariable long bookingId, @RequestBody BookingDTOCreate bookingDTOCreate) {
        return ResponseEntity.accepted().body(bookingService.modifyBooking(bookingId, bookingDTOCreate));
    }

    @DeleteMapping(value = "{bookingId}")
    ResponseEntity<Void> deleteBooking(@PathVariable long bookingId) {
        bookingService.deleteBookingById(bookingId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
