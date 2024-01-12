package com.planner.travelplanner.enums;

public enum ExceptionMessages {

    ENTITY_NOT_FOUND("Record not found with given id"),
    BODY_IS_NULL("No body found");
    private final String message;

    ExceptionMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
