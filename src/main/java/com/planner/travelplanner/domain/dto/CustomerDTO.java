package com.planner.travelplanner.domain.dto;

import java.time.LocalDate;

public class CustomerDTO {


    private long customerId;

    private String firstName;

    private String lastName;

    private LocalDate birthdate;

    private String country;

    private String city;

    private String postalCode;

    private String email;

    private int phoneNumber;

    private String login;

    private String password;

    public CustomerDTO(long customerId, String firstName, String lastName, LocalDate birthdate, String country, String city, String postalCode, String email, int phoneNumber, String login, String password) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.country = country;
        this.city = city;
        this.postalCode = postalCode;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.login = login;
        this.password = password;
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

    public String getPostalCode() {
        return postalCode;
    }

    public String getEmail() {
        return email;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
