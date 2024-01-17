package com.planner.travelplanner.destination;

import com.fasterxml.jackson.annotation.JsonProperty;

record DestinationDTOForGet(
        @JsonProperty("hotelId")
        Long hotelId,
        @JsonProperty("id")
        Long idName,
        @JsonProperty("name")
        String name,
        @JsonProperty("countryCode")
        String countryCode,
        @JsonProperty("currency")
        String currency,
        @JsonProperty("value")
        int hotelPrice
) {
}
