package com.planner.travelplanner.domain.dto.destination;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "currency",
        "value"
})
public class GrossPriceDTO {
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