package com.planner.travelplanner.destination;

import com.fasterxml.jackson.annotation.JsonProperty;


record Checkout(@JsonProperty("untilTime")
                String untilTime,
                @JsonProperty("fromTime")
                String fromTime) {

}
