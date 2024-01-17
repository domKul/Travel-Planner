package com.planner.travelplanner.destination;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "currency",
        "value"
})
record GrossPriceDTO(
        @JsonProperty("currency")
        String currency,
        @JsonProperty("value")
        int value
) {
}