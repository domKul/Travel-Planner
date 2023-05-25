package com.planner.travelplanner.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "destination")
public class Destination {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long destinationId;

    @JsonProperty("id")
    private long idName;

    @JsonProperty("name")
    private String name;

    @JsonProperty("countryCode")
    private String countryCode;


    @JsonProperty("currency")
    private String currency;

    @JsonProperty("value")
    private int HotelPrice;


    public Destination() {
    }

    public Destination(long hotelId, long idName, String name, String countryCode, String currency, int hotelPrice) {
        this.destinationId = hotelId;
        this.idName = idName;
        this.name = name;
        this.countryCode = countryCode;
        this.currency = currency;
        this.HotelPrice = hotelPrice;
    }

    public long getDestinationId() {
        return destinationId;
    }

    public void setDestinationId(long destinationId) {
        this.destinationId = destinationId;
    }

    public long getIdName() {
        return idName;
    }

    public void setIdName(long idName) {
        this.idName = idName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }


    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public int getHotelPrice() {
        return HotelPrice;
    }

    public void setHotelPrice(int hotelPrice) {
        HotelPrice = hotelPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Destination destination = (Destination) o;
        return destinationId == destination.destinationId && idName == destination.idName && HotelPrice == destination.HotelPrice && Objects.equals(name, destination.name) && Objects.equals(countryCode, destination.countryCode) && Objects.equals(currency, destination.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(destinationId, idName, name, countryCode, currency, HotelPrice);
    }

    @Override
    public String toString() {
        return "Destination{" +
                "destinationId=" + destinationId +
                ", idName=" + idName +
                ", name='" + name + '\'' +
                ", countryCode='" + countryCode + '\'' +
                ", currency='" + currency + '\'' +
                ", HotelPrice=" + HotelPrice +
                '}';
    }
}
