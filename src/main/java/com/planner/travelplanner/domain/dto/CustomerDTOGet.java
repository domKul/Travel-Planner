package com.planner.travelplanner.domain.dto;

import com.planner.travelplanner.domain.Complaint;

import java.time.LocalDate;
import java.util.List;

public class CustomerDTOGet {

    private long customerId;

    private String firstName;

    private String lastName;

    private LocalDate birthdate;

    private String country;

    private String city;

    private String streetName;

    private String postalCode;

    private String email;

    private int phoneNumber;
    private List<Complaint> complaints;

    public CustomerDTOGet(long customerId, String firstName, String lastName, LocalDate birthdate, String country, String city, String streetName, String postalCode, String email, int phoneNumber, List<Complaint> complaints) {
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
        this.complaints = complaints;
    }

    public long getCustomerId() {
        return customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getBirthdate() {
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

    public List<Complaint> getComplaints() {
        return complaints;
    }

    public String getEmail() {
        return email;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }
}
