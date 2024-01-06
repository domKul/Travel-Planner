package com.planner.travelplanner.destination;

import com.fasterxml.jackson.annotation.JsonProperty;

class GrossPrice {
    @JsonProperty("currency")
    private String currency;
    @JsonProperty("value")
    private int value;

    @JsonProperty("currency")
    String getCurrency() {
        return currency;
    }

    @JsonProperty("value")
    int getValue() {
        return value;
    }
}