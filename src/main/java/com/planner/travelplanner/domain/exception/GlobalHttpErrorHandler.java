package com.planner.travelplanner.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalHttpErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<Object> handleCustomerNotFoundException(CustomerNotFoundException customerNotFoundException) {
        return new ResponseEntity<>("Customer with given Id not found", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BookingNotFoundException.class)
    public ResponseEntity<Object> handleBookingNotFoundException(BookingNotFoundException bookingNotFoundException) {
        return new ResponseEntity<>("Booking with given Id not found", HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(HotelNotFoundException.class)
    public ResponseEntity<Object> handleHotelNotFoundException(HotelNotFoundException hotelNotFoundException) {
        return new ResponseEntity<>("Hotel with given Id not found", HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(TourNotFoundException.class)
    public ResponseEntity<Object> handleTourNotFoundException(TourNotFoundException tourNotFoundException) {
        return new ResponseEntity<>("Tour with given Id not found", HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(ComplaintNotFoundException.class)
    public ResponseEntity<Object> handleComplaintNotFoundException(ComplaintNotFoundException complaintNotFoundException) {
        return new ResponseEntity<>("Complaint with given Id not found", HttpStatus.BAD_REQUEST);
    }
}
