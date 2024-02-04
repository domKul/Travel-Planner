package travelplanner.exception;

public class NotFoundException extends RuntimeException {
    private final ExceptionMessages exceptionMessages;

    public NotFoundException(ExceptionMessages exceptionMessages) {
        this.exceptionMessages = exceptionMessages;
    }

    public ExceptionMessages getExceptionMessages() {
        return exceptionMessages;
    }
}
