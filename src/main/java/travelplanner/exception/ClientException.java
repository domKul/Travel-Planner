package travelplanner.exception;

import travelplanner.enums.ExceptionMessages;

public class ClientException extends RuntimeException{
    private final ExceptionMessages exceptionMessages;

    public ClientException(ExceptionMessages exceptionMessages) {
        this.exceptionMessages = exceptionMessages;
    }

    public ExceptionMessages getExceptionMessages() {
        return exceptionMessages;
    }
}
