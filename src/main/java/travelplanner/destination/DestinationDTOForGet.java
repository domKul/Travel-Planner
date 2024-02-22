package travelplanner.destination;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
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

}
