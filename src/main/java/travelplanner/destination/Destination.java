package travelplanner.destination;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import travelplanner.destination.query.SimpleDestinationQueryDto;

@Entity
@Table(name = "destination")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
class Destination {

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

    SimpleDestinationQueryDto mapToSimpleQueryDto() {
        return new SimpleDestinationQueryDto(
                this.destinationId,
                this.idName,
                this.name,
                this.countryCode,
                this.currency,
                this.destinationPrice
        );
    }
}
