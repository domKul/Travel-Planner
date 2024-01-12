package com.planner.travelplanner.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
class GlobalHttpErrorHandler{

//    @ExceptionHandler(CustomerNotFoundException.class)
//    ResponseEntity<Object> handleCustomerNotFoundException(CustomerNotFoundException customerNotFoundException) {
//        return new ResponseEntity<>("Customer with given Id not found", HttpStatus.BAD_REQUEST);
//    }
//
//    @ExceptionHandler(BookingNotFoundException.class)
//    ResponseEntity<Object> handleBookingNotFoundException(BookingNotFoundException bookingNotFoundException) {
//        return new ResponseEntity<>("Booking with given Id not found", HttpStatus.BAD_REQUEST);
//    }
//
//    @ExceptionHandler(HotelNotFoundException.class)
//    ResponseEntity<Object> handleHotelNotFoundException(HotelNotFoundException hotelNotFoundException) {
//        return new ResponseEntity<>("Destination with given Id not found", HttpStatus.BAD_REQUEST);
//    }
//
//    @ExceptionHandler(ComplaintNotFoundException.class)
//    ResponseEntity<Object> handleComplaintNotFoundException(ComplaintNotFoundException complaintNotFoundException) {
//        return new ResponseEntity<>("Complaint with given Id not found", HttpStatus.BAD_REQUEST);
//    }

//    @ExceptionHandler(LocationNotFoundException.class)
//    ResponseEntity<Object> handleLocationNotFoundException(LocationNotFoundException locationNotFoundException) {
//        return new ResponseEntity<>("Location with given Id not found in DB", HttpStatus.BAD_REQUEST);
//    }



    @ExceptionHandler(NotFoundException.class)
    ResponseEntity<ErrorsMessages> handleNotFoundException(NotFoundException notFoundException) {
        ErrorsMessages errorsMessages = new ErrorsMessages(notFoundException.getExceptionMessages().getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorsMessages);
    }

}
