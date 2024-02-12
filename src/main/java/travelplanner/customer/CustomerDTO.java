package travelplanner.customer;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

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

    public CustomerDTO(String firstName, String lastName, Date birthdate, String country, String city, String streetName, String postalCode, String email, int phoneNumber) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.country = country;
        this.city = city;
        this.streetName = streetName;
        this.postalCode = postalCode;
        this.email = email;
        this.phoneNumber = phoneNumber;

    }


    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getStreetName() {
        return streetName;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getEmail() {
        return email;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }
}
