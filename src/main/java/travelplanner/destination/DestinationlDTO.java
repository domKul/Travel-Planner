package travelplanner.destination;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

class DestinationlDTO {

    @JsonProperty("results")
    private List<Result> results;

    @JsonProperty("results")
    List<Result> getResults() {
        return results;
    }

    @JsonProperty("results")
    void setResults(List<Result> results) {
        this.results = results;
    }
}