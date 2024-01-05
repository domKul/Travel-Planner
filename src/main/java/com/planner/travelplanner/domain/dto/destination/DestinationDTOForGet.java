package com.planner.travelplanner.domain.dto.destination;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DestinationDTOForGet {
    @JsonProperty("hotelId")
    private Long hotelId;
    @JsonProperty("id")
    private Long idName;
    @JsonProperty("name")
    private String name;
    @JsonProperty("countryCode")
    private String countryCode;
    @JsonProperty("currency")
    private String currency;
    @JsonProperty("value")
    private int HotelPrice;

    public DestinationDTOForGet(long hotelId, long idName, String name, String countryCode, String currency, int hotelPrice) {
        this.hotelId = hotelId;
        this.idName = idName;
        this.name = name;
        this.countryCode = countryCode;
        this.currency = currency;
        this.HotelPrice = hotelPrice;
    }

    public long getHotelId() {
        return hotelId;
    }

    public long getIdName() {
        return idName;
    }

    public String getName() {
        return name;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getCurrency() {
        return currency;
    }

    public int getHotelPrice() {
        return HotelPrice;
    }
}
