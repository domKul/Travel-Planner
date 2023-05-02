package com.planner.travelplanner.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "tours")
public class Tours {

    @Id
    @GeneratedValue
    private long toursId;
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

    public Tours() {
    }

    public Tours(long toursId, String tourName, String tourDescription, LocalDate startDate, LocalDate endDate, BigDecimal tourPrice) {
        this.toursId = toursId;
        this.tourName = tourName;
        this.tourDescription = tourDescription;
        this.startDate = startDate;
        this.endDate = endDate;
        this.tourPrice = tourPrice;
    }

    public long getToursId() {
        return toursId;
    }

    public void setToursId(long toursId) {
        this.toursId = toursId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tours tours = (Tours) o;
        return toursId == tours.toursId && Objects.equals(tourName, tours.tourName) && Objects.equals(tourDescription, tours.tourDescription) && Objects.equals(startDate, tours.startDate) && Objects.equals(endDate, tours.endDate) && Objects.equals(tourPrice, tours.tourPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(toursId, tourName, tourDescription, startDate, endDate, tourPrice);
    }

    @Override
    public String toString() {
        return "Tours{" +
                "toursId=" + toursId +
                ", tourName='" + tourName + '\'' +
                ", tourDescription='" + tourDescription + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", tourPrice=" + tourPrice +
                '}';
    }
}
