package com.planner.travelplanner.location;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "destId",
        "label",
        "country",
        "region",
        "name",
        "type",
        "timezone",
        "hotels",
        "nrHotels"
})
public class LocationDTO {
    @JsonProperty("label")
    private String label;
    @JsonProperty("region")
    private String region;
    @JsonProperty("dest_id")
    private String dest_id;
    @JsonProperty("dest_type")
    private String destination_type;
    @JsonProperty("name")
    private String name;
    @JsonProperty("country")
    private String country;
    @JsonProperty("hotels")
    private int hotels;
    @JsonProperty("timezone")
    private String timezone;

    LocationDTO(String label, String region, String dest_id,String destination_type, String name, String country, Integer hotels, String timezone) {
        this.label = label;
        this.region = region;
        this.dest_id = dest_id;
        this.destination_type = destination_type;
        this.name = name;
        this.country = country;
        this.hotels = hotels;
        this.timezone = timezone;
    }

    LocationDTO() {
    }

    @JsonProperty("label")
    String getLabel() {
        return label;
    }

    @JsonProperty("label")
    void setLabel(String label) {
        this.label = label;
    }

    @JsonProperty("region")
    String getRegion() {
        return region;
    }

    @JsonProperty("region")
    void setRegion(String region) {
        this.region = region;
    }

    @JsonProperty("name")
    String getName() {
        return name;
    }

    @JsonProperty("name")
    void setName(String name) {
        this.name = name;
    }

    @JsonProperty("country")
    String getCountry() {
        return country;
    }

    @JsonProperty("country")
    void setCountry(String country) {
        this.country = country;
    }

    @JsonProperty("hotels")
    Integer getHotels() {
        return hotels;
    }

    @JsonProperty("dest_id")
    String getDest_id() {
        return dest_id;
    }

    @JsonProperty("timezone")
    String getTimezone() {
        return timezone;
    }

    @JsonProperty("dest_type")
    String getDestination_type() {
        return destination_type;
    }
}
