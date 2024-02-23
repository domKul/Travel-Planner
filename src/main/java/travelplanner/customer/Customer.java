package travelplanner.customer;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import travelplanner.booking.query.SimpleBookingQueryDto;
import travelplanner.complaint.query.SimpleComplaintQueryDto;
import travelplanner.customer.query.SimpleCustomerQueryDto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "customers_list")
@JsonIgnoreProperties({"bookings", "complaints"})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "customerId")
@Getter
@Setter
@EqualsAndHashCode
class Customer {

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
    @EqualsAndHashCode.Exclude
    private List<SimpleComplaintQueryDto> complaints;
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    @JsonBackReference
    @EqualsAndHashCode.Exclude
    private List<SimpleBookingQueryDto> bookings;

    public Customer() {
    }

     public Customer(long customerId, String firstName, String lastName,
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

    static Customer mapFromQueryDto(SimpleCustomerQueryDto simpleCustomerQueryDto){
        return new Customer(simpleCustomerQueryDto.getCustomerId(),
                simpleCustomerQueryDto.getFirstName(),
                simpleCustomerQueryDto.getLastName(),
                simpleCustomerQueryDto.getBirthdate(),
                simpleCustomerQueryDto.getCountry(),
                simpleCustomerQueryDto.getCity(),
                simpleCustomerQueryDto.getStreetName(),
                simpleCustomerQueryDto.getPostalCode(),
                simpleCustomerQueryDto.getEmail(),
                simpleCustomerQueryDto.getPhoneNumber());
    }
    static SimpleCustomerQueryDto mapToQueryDto(Customer simpleCustomerQueryDto){
        return new SimpleCustomerQueryDto(simpleCustomerQueryDto.getCustomerId(),
                simpleCustomerQueryDto.getFirstName(),
                simpleCustomerQueryDto.getLastName(),
                simpleCustomerQueryDto.getBirthdate(),
                simpleCustomerQueryDto.getCountry(),
                simpleCustomerQueryDto.getCity(),
                simpleCustomerQueryDto.getStreetName(),
                simpleCustomerQueryDto.getPostalCode(),
                simpleCustomerQueryDto.getEmail(),
                simpleCustomerQueryDto.getPhoneNumber());
    }
}
