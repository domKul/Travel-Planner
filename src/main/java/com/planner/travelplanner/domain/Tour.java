package com.planner.travelplanner.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "tours")
public class Tour {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long tourId;
    @NotNull
    private String tourName;
    @NotNull
    private String tourDescription;
    @NotNull
    private LocalDate startDate;
    @NotNull
    private LocalDate endDate;
    @NotNull
    private BigDecimal tourPrice;

    @ManyToOne(fetch = FetchType.EAGER)
    private Booking booking;


    public Tour() {
    }

    public Tour(long tourId, String tourName, String tourDescription, LocalDate startDate, LocalDate endDate, BigDecimal tourPrice) {
        this.tourId = tourId;
        this.tourName = tourName;
        this.tourDescription = tourDescription;
        this.startDate = startDate;
        this.endDate = endDate;
        this.tourPrice = tourPrice;
    }

    public long getTourId() {
        return tourId;
    }

    public void setTourId(long tourId) {
        this.tourId = tourId;
    }

    public String getTourName() {
        return tourName;
    }

    public void setTourName(String tourName) {
        this.tourName = tourName;
    }

    public String getTourDescription() {
        return tourDescription;
    }

    public void setTourDescription(String tourDescription) {
        this.tourDescription = tourDescription;
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

    public BigDecimal getTourPrice() {
        return tourPrice;
    }

    public void setTourPrice(BigDecimal tourPrice) {
        this.tourPrice = tourPrice;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tour tour = (Tour) o;
        return tourId == tour.tourId && Objects.equals(tourName, tour.tourName) && Objects.equals(tourDescription, tour.tourDescription) && Objects.equals(startDate, tour.startDate) && Objects.equals(endDate, tour.endDate) && Objects.equals(tourPrice, tour.tourPrice) && Objects.equals(booking, tour.booking);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tourId, tourName, tourDescription, startDate, endDate, tourPrice, booking);
    }

    @Override
    public String toString() {
        return "Tour{" +
                "tourId=" + tourId +
                ", tourName='" + tourName + '\'' +
                ", tourDescription='" + tourDescription + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", tourPrice=" + tourPrice +
                ", booking=" + booking +
                '}';
    }
}
