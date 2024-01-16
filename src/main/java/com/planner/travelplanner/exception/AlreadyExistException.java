package com.planner.travelplanner.exception;

import com.planner.travelplanner.enums.ExceptionMessages;

public class AlreadyExistException extends RuntimeException{
    private final ExceptionMessages errorsMessages;

    public AlreadyExistException(ExceptionMessages errorsMessages) {
        this.errorsMessages = errorsMessages;
    }

    public ExceptionMessages getErrorsMessages() {
        return errorsMessages;
    }
}
