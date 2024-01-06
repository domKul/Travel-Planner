package com.planner.travelplanner.destination;

import com.fasterxml.jackson.annotation.JsonProperty;


class Checkout {

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
