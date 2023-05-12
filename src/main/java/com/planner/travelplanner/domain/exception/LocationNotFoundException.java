package com.planner.travelplanner.domain.exception;

import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonProcessingException;

public class LocationNotFoundException extends JsonProcessingException {
    protected LocationNotFoundException(String msg, JsonLocation loc, Throwable rootCause) {
        super(msg, loc, rootCause);
    }
}
