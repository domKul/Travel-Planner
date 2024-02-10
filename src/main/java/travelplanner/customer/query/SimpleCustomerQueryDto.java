package travelplanner.customer.query;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import travelplanner.booking.query.SimpleBookingQueryDto;
import travelplanner.complaint.query.SimpleComplaintQueryDto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "customers_list")
@JsonIgnoreProperties({"bookings", "complaints"})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "customerId")
public class SimpleCustomerQueryDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private Date birthdate;
    @NotNull
    private String country;
    @NotNull
    private String city;
    @NotNull
    private String streetName;
    @NotNull
    private String postalCode;
    @NotNull
    @Email
    @Column(unique = true)
    private String email;
    @NotNull
    private int phoneNumber;
    @OneToMany(mappedBy = "customer")
    public List<SimpleComplaintQueryDto> complaints;
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<SimpleBookingQueryDto> bookings;

    public SimpleCustomerQueryDto() {
    }

    public SimpleCustomerQueryDto(long customerId, String firstName, String lastName,
                    Date birthdate, String country, String city, String streetName, String postalCode,
                    String email, int phoneNumber) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.country = country;
        this.city = city;
        this.streetName = streetName;
        this.postalCode = postalCode;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.complaints = new ArrayList<>();
        this.bookings = new ArrayList<>();
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getCustomerId() {
        return customerId;
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

    public List<SimpleComplaintQueryDto> getComplaints() {
        return complaints;
    }

    public List<SimpleBookingQueryDto> getBookings() {
        return bookings;
    }

}
