package com.planner.travelplanner.domain.dto.destination;


import com.fasterxml.jackson.annotation.JsonProperty;

public class Checkin {

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
