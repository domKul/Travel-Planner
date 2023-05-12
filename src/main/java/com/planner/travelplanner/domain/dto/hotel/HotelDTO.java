package com.planner.travelplanner.domain.dto.hotel;

import com.fasterxml.jackson.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class HotelDTO {


//    public HotelDTO(Integer id, String name, String countryCode, PriceBreakdownDTO priceBreakdown, String currency, String checkoutDate, String checkinDate) {
//        this.id = id;
//        this.name = name;
//        this.countryCode = countryCode;
//        this.priceBreakdown = priceBreakdown;
//        this.currency = currency;
//        this.checkoutDate = checkoutDate;
//        this.checkinDate = checkinDate;
//    }
//
//
//    @JsonProperty("id")
//    private Integer id;
//    @JsonProperty("name")
//    private String name;
//    @JsonProperty("position")
//    private Integer position;
//    @JsonProperty("rankingPosition")
//    private Integer rankingPosition;
//    @JsonProperty("countryCode")
//    private String countryCode;
//    @JsonProperty("priceBreakdown")
//    private PriceBreakdownDTO priceBreakdown;
//    @JsonProperty("currency")
//    private String currency;
//    @JsonProperty("checkoutDate")
//    private String checkoutDate;
//    @JsonProperty("checkinDate")
//    private String checkinDate;

//    @JsonProperty("id")
//    public Integer getId() {
//        return id;
//    }
//
//
//
//    @JsonProperty("name")
//    public String getName() {
//        return name;
//    }
//
//
//
//    @JsonProperty("position")
//    public Integer getPosition() {
//        return position;
//    }
//
//
//
//    @JsonProperty("rankingPosition")
//    public Integer getRankingPosition() {
//        return rankingPosition;
//    }
//
//
//
//    @JsonProperty("countryCode")
//    public String getCountryCode() {
//        return countryCode;
//    }
//
//
//
//    @JsonProperty("priceBreakdown")
//    public PriceBreakdownDTO getPriceBreakdown() {
//        return priceBreakdown;
//    }
//
//
//
//    @JsonProperty("currency")
//    public String getCurrency() {
//        return currency;
//    }
//
//
//
//
//    @JsonProperty("checkoutDate")
//    public String getCheckoutDate() {
//        return checkoutDate;
//    }
//
//
//
//    @JsonProperty("checkinDate")
//    public String getCheckinDate() {
//        return checkinDate;
//    }
     @JsonIgnore
      private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
}