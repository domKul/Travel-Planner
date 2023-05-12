package com.planner.travelplanner.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

@Entity
@Table(name = "grossPrice")
public class GrossPrice {
    public GrossPrice() {
    }

    public GrossPrice(long grossPrice, String currency, int value) {
        this.grossPrice = grossPrice;
        this.currency = currency;
        this.value = value;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long grossPrice;

    @JsonProperty("currency")
    private String currency;

    @JsonProperty("value")
    private int value;

    @OneToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    @OneToOne
    private PriceBreakdown priceBreakdown;

    public long getGrossPrice() {
        return grossPrice;
    }

    public void setGrossPrice(long grossPrice) {
        this.grossPrice = grossPrice;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

}
