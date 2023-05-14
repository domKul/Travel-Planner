package com.planner.travelplanner.domain.dto.hotel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.planner.travelplanner.domain.PriceBreakdown;

import java.util.LinkedHashMap;
import java.util.Map;

public class Result {

    @JsonProperty("id")
    private long id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("countryCode")
    private String countryCode;
    @JsonProperty("priceBreakdown")
    private PriceBreakdown priceBreakdown;
    @JsonProperty("currency")
    private String currency;
    @JsonProperty("checkin")
    private Checkin checkin;
    @JsonProperty("checkout")
    private Checkout checkout;
    @JsonProperty("checkoutDate")
    private String checkoutDate;
    @JsonProperty("checkinDate")
    private String checkinDate;
    @JsonProperty("accuratePropertyClass")
    private Integer accuratePropertyClass;
    @JsonProperty("ufi")
    private Integer ufi;
    @JsonProperty("wishlistName")
    private String wishlistName;
    @JsonIgnore
    private final Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("id")
    public long getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(long id) {
        this.id = id;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }


    @JsonProperty("countryCode")
    public String getCountryCode() {
        return countryCode;
    }

    @JsonProperty("countryCode")
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    @JsonProperty("priceBreakdown")
    public PriceBreakdown getPriceBreakdown() {
        return priceBreakdown;
    }

    @JsonProperty("priceBreakdown")
    public void setPriceBreakdown(PriceBreakdown priceBreakdown) {
        this.priceBreakdown = priceBreakdown;
    }

    @JsonProperty("currency")
    public String getCurrency() {
        return currency;
    }

    @JsonProperty("currency")
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @JsonProperty("checkin")
    public Checkin getCheckin() {
        return checkin;
    }

    @JsonProperty("checkin")
    public void setCheckin(Checkin checkin) {
        this.checkin = checkin;
    }

    @JsonProperty("checkout")
    public Checkout getCheckout() {
        return checkout;
    }

    @JsonProperty("checkout")
    public void setCheckout(Checkout checkout) {
        this.checkout = checkout;
    }

    @JsonProperty("checkoutDate")
    public String getCheckoutDate() {
        return checkoutDate;
    }

    @JsonProperty("checkoutDate")
    public void setCheckoutDate(String checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    @JsonProperty("checkinDate")
    public String getCheckinDate() {
        return checkinDate;
    }

    @JsonProperty("checkinDate")
    public void setCheckinDate(String checkinDate) {
        this.checkinDate = checkinDate;
    }

    @JsonProperty("accuratePropertyClass")
    public Integer getAccuratePropertyClass() {
        return accuratePropertyClass;
    }

    @JsonProperty("accuratePropertyClass")
    public void setAccuratePropertyClass(Integer accuratePropertyClass) {
        this.accuratePropertyClass = accuratePropertyClass;
    }

    @JsonProperty("ufi")
    public Integer getUfi() {
        return ufi;
    }

    @JsonProperty("ufi")
    public void setUfi(Integer ufi) {
        this.ufi = ufi;
    }

    @JsonProperty("wishlistName")
    public String getWishlistName() {
        return wishlistName;
    }

    @JsonProperty("wishlistName")
    public void setWishlistName(String wishlistName) {
        this.wishlistName = wishlistName;
    }


}