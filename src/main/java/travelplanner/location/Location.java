package travelplanner.location;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "location")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long locationId;
    @JsonProperty("label")
    private String label;
    @JsonProperty("dest_id")
    private String dest_id;
    @JsonProperty("dest_type")
    private String destination_type;
    @JsonProperty("region")
    private String region;
    @JsonProperty("name")
    private String name;
    @JsonProperty("country")
    private String country;
    @JsonProperty("hotels")
    private int hotels;
    @JsonProperty("timezone")
    private String timezone;
}
