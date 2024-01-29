package travelplanner.enums;

public enum ExceptionMessages {

    ENTITY_NOT_FOUND("Record not found with given id"),
    BODY_IS_NULL("No body found"),
    TRAVELER_ALREADY_EXIST("Traveler already exist"),
    BOOKING_ALREADY_EXIST("Booking with given id already exist in booking list");
    private final String message;

    ExceptionMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
