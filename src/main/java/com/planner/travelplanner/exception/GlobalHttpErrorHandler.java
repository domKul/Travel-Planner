package com.planner.travelplanner.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
class GlobalHttpErrorHandler{

    @ExceptionHandler(NotFoundException.class)
    ResponseEntity<ErrorsMessages> handleNotFoundException(NotFoundException notFoundException) {
        ErrorsMessages errorsMessages = new ErrorsMessages(notFoundException.getExceptionMessages().getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorsMessages);
    }

    @ExceptionHandler(AlreadyExistException.class)
    ResponseEntity<ErrorsMessages> handleAlreadyExistException(AlreadyExistException notFoundException) {
        ErrorsMessages errorsMessages = new ErrorsMessages(notFoundException.getErrorsMessages().getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorsMessages);
    }
}
