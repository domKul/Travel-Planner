package com.planner.travelplanner.domain;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "room_type")
public class RoomType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int capacity;

    private double price;

     private double area;

     private boolean hasBalcony;

    @OneToMany(mappedBy = "roomType")
    private List<Room> rooms;

    public RoomType() {
    }

    public RoomType(Long id, String name, int capacity, double price, double area, boolean hasBalcony, List<Room> rooms) {

        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.price = price;
        this.area = area;
        this.hasBalcony = hasBalcony;
        this.rooms = rooms;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public boolean isHasBalcony() {
        return hasBalcony;
    }

    public void setHasBalcony(boolean hasBalcony) {
        this.hasBalcony = hasBalcony;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoomType roomType = (RoomType) o;
        return capacity == roomType.capacity && Double.compare(roomType.price, price) == 0 && Double.compare(roomType.area, area) == 0 && hasBalcony == roomType.hasBalcony && Objects.equals(id, roomType.id) && Objects.equals(name, roomType.name) && Objects.equals(rooms, roomType.rooms);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, capacity, price, area, hasBalcony, rooms);
    }

    @Override
    public String toString() {
        return "RoomType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", capacity=" + capacity +
                ", price=" + price +
                ", area=" + area +
                ", hasBalcony=" + hasBalcony +
                ", rooms=" + rooms +
                '}';
    }
}
