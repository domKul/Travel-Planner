package com.planner.travelplanner.domain.dto.destination;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class DestinationlDTO {

    @JsonProperty("results")
    private List<Result> results;

    @JsonProperty("results")
    public List<Result> getResults() {
        return results;
    }

    @JsonProperty("results")
    public void setResults(List<Result> results) {
        this.results = results;
    }
}