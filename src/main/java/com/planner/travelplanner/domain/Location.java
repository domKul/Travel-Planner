package com.planner.travelplanner.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
@Table(name = "location")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long locationId;
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

    public Location() {
    }

    public Location(long locationId, String label, String region, String name, String country, Integer hotels, String timezone) {
        this.locationId = locationId;
        this.label = label;
        this.region = region;
        this.name = name;
        this.country = country;
        this.hotels = hotels;
        this.timezone = timezone;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getHotels() {
        return hotels;
    }

    public void setHotels(Integer hotels) {
        this.hotels = hotels;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }


}
