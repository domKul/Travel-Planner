package com.planner.travelplanner.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
    ResponseEntity<ErrorsMessages> handleAlreadyExist(AlreadyExistException alreadyExistException) {
        ErrorsMessages errorsMessages = new ErrorsMessages(alreadyExistException.getErrorsMessages().getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorsMessages);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<ErrorsMessages> handleWrongMethodArgument(MethodArgumentNotValidException methodArgumentNotValidException) {
        FieldError fieldError = methodArgumentNotValidException.getBindingResult().getFieldError("email");
        String errorMessage = fieldError != null ? fieldError.getDefaultMessage() : "Validation failed";
        ErrorsMessages errorsMessages = new ErrorsMessages(errorMessage);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorsMessages);
    }
}
