package com.planner.travelplanner.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
@Table(name = "location")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long locationId;
    @JsonProperty("label")
    private String label;
    @JsonProperty("dest_id")
    private String dest_id;
    @JsonProperty("dest_type")
    private String destination_type;
    @JsonProperty("region")
    private String region;
    @JsonProperty("name")
    private String name;
    @JsonProperty("country")
    private String country;
    @JsonProperty("hotels")
    private int hotels;
    @JsonProperty("timezone")
    private String timezone;

    public Location() {
    }

    public Location(long locationId, String label, String dest_id,String destination_type, String region, String name, String country, int hotels, String timezone) {
        this.locationId = locationId;
        this.label = label;
        this.dest_id = dest_id;
        this.destination_type = destination_type;
        this.region = region;
        this.name = name;
        this.country = country;
        this.hotels = hotels;
        this.timezone = timezone;
    }

    public String getLabel() {
        return label;
    }

    public String getRegion() {
        return region;
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

    public String getTimezone() {
        return timezone;
    }

    public String getDest_id() {
        return dest_id;
    }

    public String getDestination_type() {
        return destination_type;
    }
}
