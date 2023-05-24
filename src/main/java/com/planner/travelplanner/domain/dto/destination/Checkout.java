package com.planner.travelplanner.domain.dto.destination;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Checkout {

    @JsonProperty("untilTime")
    private String untilTime;
    @JsonProperty("fromTime")
    private String fromTime;

    @JsonProperty("untilTime")
    public String getUntilTime() {
        return untilTime;
    }

    @JsonProperty("fromTime")
    public String getFromTime() {
        return fromTime;
    }

}
