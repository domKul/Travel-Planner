package com.planner.travelplanner.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "hotels")
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long hotelId;

    @JsonProperty("id")
    private long idName;

    @JsonProperty("name")
    private String name;

    @JsonProperty("countryCode")
    private String countryCode;


    @JsonProperty("currency")
    private String currency;


    public Hotel() {
    }

    public Hotel(long hotelId, long idName, String name, String countryCode, String currency) {
        this.hotelId = hotelId;
        this.idName = idName;
        this.name = name;
        this.countryCode = countryCode;
        this.currency = currency;
    }

    public long getHotelId() {
        return hotelId;
    }

    public void setHotelId(long hotelId) {
        this.hotelId = hotelId;
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





    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hotel hotel = (Hotel) o;
        return hotelId == hotel.hotelId && Objects.equals(idName, hotel.idName) && Objects.equals(name, hotel.name) && Objects.equals(countryCode, hotel.countryCode) && Objects.equals(currency, hotel.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hotelId, idName, name, countryCode, currency);
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "hotelId=" + hotelId +
                ", idName=" + idName +
                ", name='" + name + '\'' +
                ", countryCode='" + countryCode + '\'' +
                ", currency='" + currency + '\'' +
                '}';
    }
}
