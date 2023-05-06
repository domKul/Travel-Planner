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


    private String name;


    private String address;

    private BigDecimal hotelPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;

    public Hotel() {
    }

    public Hotel(Long hotelId, String name, String address, BigDecimal hotelPrice, Room room) {
        this.hotelId = hotelId;
        this.name = name;
        this.address = address;
        this.hotelPrice = hotelPrice;
        this.room = room;
    }

    public Long getHotelId() {
        return hotelId;
    }

    public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
        return Objects.equals(hotelId, hotel.hotelId) && Objects.equals(name, hotel.name) && Objects.equals(address, hotel.address) && Objects.equals(hotelPrice, hotel.hotelPrice) && Objects.equals(room, hotel.room);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hotelId, name, address, hotelPrice, room);
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "hotelId=" + hotelId +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", hotelPrice=" + hotelPrice +
                ", room=" + room +
                '}';
    }
}
