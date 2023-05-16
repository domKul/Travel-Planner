package com.planner.travelplanner.domain.dto.hotel;

import com.fasterxml.jackson.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "grossPrice",
        "excludedPrice"
})
public class PriceBreakdown {


    @JsonProperty("grossPrice")
    private GrossPriceDTO grossPrice;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();


    @JsonProperty("grossPrice")
    public GrossPriceDTO getGrossPrice() {
        return grossPrice;
    }

    @JsonProperty("grossPrice")
    public void setGrossPrice(GrossPriceDTO grossPrice) {
        this.grossPrice = grossPrice;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
