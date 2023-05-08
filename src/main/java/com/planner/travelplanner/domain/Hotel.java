package com.planner.travelplanner.domain;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.*;

@Entity
@Table(name = "hotels")
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hotelId;


    private String hotelName;


    private String hotelAddress;

    private BigDecimal hotelPrice;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "room_id")
    private Room room;

    public Hotel() {
    }

    public Hotel(Long hotelId, String hotelName, String hotelAddress, BigDecimal hotelPrice, Room room) {
        this.hotelId = hotelId;
        this.hotelName = hotelName;
        this.hotelAddress = hotelAddress;
        this.hotelPrice = hotelPrice;
        this.room = room;
    }

    public Long getHotelId() {
        return hotelId;
    }

    public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getHotelAddress() {
        return hotelAddress;
    }

    public void setHotelAddress(String hotelAddress) {
        this.hotelAddress = hotelAddress;
    }

    public BigDecimal getHotelPrice() {
        return hotelPrice;
    }

    public void setHotelPrice(BigDecimal hotelPrice) {
        this.hotelPrice = hotelPrice;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hotel hotel = (Hotel) o;
        return Objects.equals(hotelId, hotel.hotelId) && Objects.equals(hotelName, hotel.hotelName) && Objects.equals(hotelAddress, hotel.hotelAddress) && Objects.equals(hotelPrice, hotel.hotelPrice) && Objects.equals(room, hotel.room);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hotelId, hotelName, hotelAddress, hotelPrice, room);
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "hotelId=" + hotelId +
                ", name='" + hotelName + '\'' +
                ", address='" + hotelAddress + '\'' +
                ", hotelPrice=" + hotelPrice +
                ", room=" + room +
                '}';
    }
}
