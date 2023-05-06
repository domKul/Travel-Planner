package com.planner.travelplanner.domain;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.springframework.lang.Nullable;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long bookingId;

    @NotNull
    private LocalDate startDate;
    @NotNull
    private LocalDate endDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    @NotNull
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tours_id")
    @Nullable
    private Tour tour;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel_id")
    @Nullable
    private Hotel hotel;

    public Booking() {
    }

    public Booking(long bookingId, LocalDate startDate, LocalDate endDate, Customer customer, @Nullable Tour tour, @Nullable Hotel hotel) {
        this.bookingId = bookingId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.customer = customer;
        this.tour = tour;
        this.hotel = hotel;
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

    @Nullable
    public Tour getTours() {
        return tour;
    }

    public void setTours(@Nullable Tour tour) {
        this.tour = tour;
    }

    @Nullable
    public Hotel getHotels() {
        return hotel;
    }

    public void setHotels(@Nullable Hotel hotel) {
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
