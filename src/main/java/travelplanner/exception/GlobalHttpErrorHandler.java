package travelplanner.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
class GlobalHttpErrorHandler {

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
    ResponseEntity<ErrorsMessages> handleWrongMethodArgument(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        if (bindingResult.hasErrors()) {
            List<String> errorMessages = bindingResult.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .toList();
            ErrorsMessages errorsMessages = new ErrorsMessages(errorMessages.toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorsMessages);
        } else {
            String defaultMessage = "Validation failed";
            ErrorsMessages errorsMessages = new ErrorsMessages(defaultMessage);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorsMessages);
        }
    }
}
