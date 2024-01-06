package com.planner.travelplanner.destination;


import com.fasterxml.jackson.annotation.JsonProperty;

class Checkin {

    @JsonProperty("fromTime")
    private String fromTime;
    @JsonProperty("untilTime")
    private String untilTime;

    @JsonProperty("fromTime")
    public String getFromTime() {
        return fromTime;
    }

    @JsonProperty("untilTime")
    public String getUntilTime() {
        return untilTime;
    }
}
