package com.planner.travelplanner.destination;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

record DestinationDTO(@JsonProperty("results")
                       List<Result> results) {
}