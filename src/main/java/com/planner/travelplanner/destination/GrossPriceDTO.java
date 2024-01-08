package com.planner.travelplanner.destination;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "currency",
        "value"
})
class GrossPriceDTO {
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