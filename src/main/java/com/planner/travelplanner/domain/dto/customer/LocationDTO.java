package com.planner.travelplanner.domain.dto.customer;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.annotation.processing.Generated;


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
@Generated("jsonschema2pojo")
public class LocationDTO {
    @JsonProperty("label")
    private String label;
    @JsonProperty("region")
    private String region;
    @JsonProperty("name")
    private String name;
    @JsonProperty("country")
    private String country;

    @JsonProperty("hotels")
    private Integer hotels;
    @JsonProperty("timezone")
    private String timezone;

    public LocationDTO(String label, String region, String name, String country, String destId, Integer hotels, String timezone, String cityName, Integer nrHotels) {
        this.label = label;
        this.region = region;
        this.name = name;
        this.country = country;
        this.hotels = hotels;
        this.timezone = timezone;
    }


    public LocationDTO() {
    }

    @JsonProperty("label")
    public String getLabel() {
        return label;
    }

    @JsonProperty("label")
    public void setLabel(String label) {
        this.label = label;
    }

    @JsonProperty("region")
    public String getRegion() {
        return region;
    }

    @JsonProperty("region")
    public void setRegion(String region) {
        this.region = region;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("country")
    public String getCountry() {
        return country;
    }

    @JsonProperty("country")
    public void setCountry(String country) {
        this.country = country;
    }



    public Integer getHotels() {
        return hotels;
    }

    public String getTimezone() {
        return timezone;
    }


}

