package com.planner.travelplanner.domain.dto.hotel;

import com.fasterxml.jackson.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "currency",
        "value"
})
public class GrossPriceDTO {

    public GrossPriceDTO() {
    }

    public GrossPriceDTO(String currency, int value, Map<String, Object> additionalProperties) {
        this.currency = currency;
        this.value = value;
        this.additionalProperties = additionalProperties;
    }

    @JsonProperty("currency")
    private String currency;
    @JsonProperty("value")
    private int value;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();
    @JsonProperty("currency")
    public String getCurrency() {
        return currency;
    }
    @JsonProperty("value")
    public int getValue() {
        return value;
    }
    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }


}