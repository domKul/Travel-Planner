package travelplanner.customer;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;
@Getter
@AllArgsConstructor
public class CustomerDTO {

    @JsonProperty("firstName")
    @NotNull(message = "First name required")
    private String firstName;
    @JsonProperty("lastName")
    @NotNull(message = "Last name required")
    private String lastName;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @NotNull(message = "Birth date required")
    private final Date birthdate;
    @JsonProperty("country")
    @NotNull(message = "Country name required")
    private String country;
    @JsonProperty("city")
    @NotNull(message = "City name required")
    private String city;
    @JsonProperty("streetName")
    @NotNull(message = "Street name required")
    private String streetName;
    @JsonProperty("postalCode")
    @NotNull(message = "Postal code required")
    private String postalCode;
    @JsonProperty("email")
    @Email(message = "wrong email")
    private String email;
    @JsonProperty("phoneNumber")
    @NotNull(message = "Phone number required")
    private int phoneNumber;
}
