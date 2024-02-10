package travelplanner.customer;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import travelplanner.booking.query.SimpleBookingQueryDto;
import travelplanner.complaint.query.SimpleComplaintQueryDto;
import travelplanner.customer.query.SimpleCustomerQueryDto;

import java.util.*;

@Entity
@Table(name = "customers_list")
@JsonIgnoreProperties({"bookings", "complaints"})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "customerId")
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
    private List<SimpleComplaintQueryDto> complaints;
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    @JsonBackReference
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

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public String getCountry() {
        return country;
    }

     void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public String getStreetName() {
        return streetName;
    }

    public List<SimpleComplaintQueryDto> getComplaints() {
        return complaints;
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

    public List<SimpleBookingQueryDto> getBookings() {
        return Collections.unmodifiableList(bookings);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return  phoneNumber == customer.phoneNumber &&
                Objects.equals(firstName, customer.firstName) &&
                Objects.equals(lastName, customer.lastName) &&
                Objects.equals(birthdate, customer.birthdate) &&
                Objects.equals(country, customer.country) &&
                Objects.equals(city, customer.city) &&
                Objects.equals(streetName, customer.streetName) &&
                Objects.equals(postalCode, customer.postalCode) &&
                Objects.equals(email, customer.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, firstName, lastName, birthdate,
                country, city, streetName, postalCode, email,
                phoneNumber);
    }
}
