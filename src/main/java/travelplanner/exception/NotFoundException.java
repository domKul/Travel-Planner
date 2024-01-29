package travelplanner.exception;

import travelplanner.enums.ExceptionMessages;

public class NotFoundException extends RuntimeException {
    private final ExceptionMessages exceptionMessages;

    public NotFoundException(ExceptionMessages exceptionMessages) {
        this.exceptionMessages = exceptionMessages;
    }

    public ExceptionMessages getExceptionMessages() {
        return exceptionMessages;
    }
}
