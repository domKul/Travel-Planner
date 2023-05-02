package com.planner.travelplanner.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

@Entity
@Table(name = "hotels")
public class Hotels {

    public Hotels() {
    }

    public Hotels(long hotelId, String hotelName) {
        this.hotelId = hotelId;
        this.hotelName = hotelName;
    }

    @Id
    @GeneratedValue
    private long hotelId;

    @NotNull
    private String hotelName;

    public long getHotelId() {
        return hotelId;
    }

    public void setHotelId(long hotelId) {
        this.hotelId = hotelId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hotels hotels = (Hotels) o;
        return hotelId == hotels.hotelId && Objects.equals(hotelName, hotels.hotelName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hotelId, hotelName);
    }

    @Override
    public String toString() {
        return "Hotels{" +
                "hotelId=" + hotelId +
                ", hotelName='" + hotelName + '\'' +
                '}';
    }
}
