package com.planner.travelplanner.enums;

public enum ExceptionMessages {

    ENTITY_NOT_FOUND("Record not found with given id"),
    BODY_IS_NULL("No body found"),
    BOOKING_ALREADY_EXIST("Booking with given destination id already exist in booking list with your id");
    private final String message;

    ExceptionMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
