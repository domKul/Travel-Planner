package com.planner.travelplanner.customer;

import com.planner.travelplanner.complaint.Complaint;

import java.util.Date;
import java.util.List;

public class CustomerDTOGet {

    private final Long customerId;
    private final String firstName;
    private final String lastName;
    private final Date birthdate;
    private final String country;
    private final String city;
    private final String streetName;
    private final String postalCode;
    private final String email;
    private final int phoneNumber;
    private final List<Complaint> complaints;

    public CustomerDTOGet(long customerId, String firstName, String lastName, Date birthdate, String country, String city, String streetName, String postalCode, String email, int phoneNumber, List<Complaint> complaints) {
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
