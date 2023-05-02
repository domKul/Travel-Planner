package com.planner.travelplanner.domain.dto;

import java.time.LocalDate;

public class CustomerDTOGet {

    private long customerId;

    private String firstName;

    private String lastName;

    private LocalDate birthdate;

    private String country;

    private String city;

    private String postalCode;

    private String email;

    private int phoneNumber;

    public CustomerDTOGet(long customerId, String firstName, String lastName, LocalDate birthdate, String country, String city, String postalCode, String email, int phoneNumber) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.country = country;
        this.city = city;
        this.postalCode = postalCode;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
}
