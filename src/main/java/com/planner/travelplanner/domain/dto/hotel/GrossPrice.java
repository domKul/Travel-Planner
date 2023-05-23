package com.planner.travelplanner.domain.dto.hotel;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GrossPrice {
    @JsonProperty("currency")
    private String currency;
    @JsonProperty("value")
    private int value;

    @JsonProperty("currency")
    public String getCurrency() {
        return currency;
    }

    @JsonProperty("value")
    public int getValue() {
        return value;
    }


}