package travelplanner.destination;

import com.fasterxml.jackson.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "grossPrice",
        "excludedPrice"
})
class PriceBreakdown {

    @JsonProperty("grossPrice")
    private GrossPriceDTO grossPrice;
    @JsonIgnore
    private final Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();


    @JsonProperty("grossPrice")
    GrossPriceDTO getGrossPrice() {
        return grossPrice;
    }

    @JsonProperty("grossPrice")
    void setGrossPrice(GrossPriceDTO grossPrice) {
        this.grossPrice = grossPrice;
    }

    @JsonAnyGetter
    Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
}
