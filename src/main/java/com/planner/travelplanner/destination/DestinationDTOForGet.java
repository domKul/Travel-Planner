package com.planner.travelplanner.destination;

import com.fasterxml.jackson.annotation.JsonProperty;

class DestinationDTOForGet {
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
    private int hotelPrice;

    DestinationDTOForGet(long hotelId, long idName, String name, String countryCode, String currency, int hotelPrice) {
        this.hotelId = hotelId;
        this.idName = idName;
        this.name = name;
        this.countryCode = countryCode;
        this.currency = currency;
        this.hotelPrice = hotelPrice;
    }

    long getHotelId() {
        return hotelId;
    }

    long getIdName() {
        return idName;
    }

    String getName() {
        return name;
    }

    String getCountryCode() {
        return countryCode;
    }

    String getCurrency() {
        return currency;
    }

    int getHotelPrice() {
        return hotelPrice;
    }
}
