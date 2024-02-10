package travelplanner.destination.query;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
@Table(name = "destination")
public class SimpleDestinationQueryDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long destinationId;
    @JsonProperty("id")
    private long idName;
    @JsonProperty("name")
    private String name;
    @JsonProperty("countryCode")
    private String countryCode;
    @JsonProperty("currency")
    private String currency;
    @JsonProperty("value")
    private int destinationPrice;

    public SimpleDestinationQueryDto() {
    }

    public SimpleDestinationQueryDto(long hotelId, long idName, String name, String countryCode, String currency, int destinationPrice) {
        this.destinationId = hotelId;
        this.idName = idName;
        this.name = name;
        this.countryCode = countryCode;
        this.currency = currency;
        this.destinationPrice = destinationPrice;
    }

    public Long getDestinationId() {
        return destinationId;
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

    public int getDestinationPrice() {
        return destinationPrice;
    }
}
