package com.planner.travelplanner.destination;

import com.fasterxml.jackson.annotation.JsonProperty;

record GrossPrice(@JsonProperty("currency")
                  String currency,
                  @JsonProperty("value")
                  int value) {
}