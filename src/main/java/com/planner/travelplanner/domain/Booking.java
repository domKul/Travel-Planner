package com.planner.travelplanner.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.springframework.lang.Nullable;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long bookingId;


    private LocalDate startDate;

    private LocalDate endDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tours_id")
    private Tour tour;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    public Booking() {
    }

    public Booking(long bookingId, LocalDate startDate, LocalDate endDate, Customer customer,  Tour tour,  Hotel hotel) {
        this.bookingId = bookingId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.customer = customer;
        this.tour = tour;
        this.hotel = hotel;
    }

    public Booking(long id, LocalDate startDate, LocalDate endDate, long customerId, long tourId, long hotelId) {
        this.startDate = startDate;
        this.endDate = endDate;
    }


    public long getBookingId() {
        return bookingId;
    }

    public void setBookingId(long bookingId) {
        this.bookingId = bookingId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }


    public Tour getTours() {
        return tour;
    }

    public void setTours( Tour tour) {
        this.tour = tour;
    }


    public Hotel getHotels() {
        return hotel;
    }

    public void setHotels( Hotel hotel) {
        this.hotel = hotel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return bookingId == booking.bookingId && Objects.equals(startDate, booking.startDate) && Objects.equals(endDate, booking.endDate) && Objects.equals(customer, booking.customer) && Objects.equals(tour, booking.tour) && Objects.equals(hotel, booking.hotel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookingId, startDate, endDate, customer, tour, hotel);
    }

    @Override
    public String toString() {
        return "Booking{" +
                "bookingId=" + bookingId +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", customer=" + customer +
                ", tour=" + tour +
                ", hotel=" + hotel +
                '}';
    }
}
