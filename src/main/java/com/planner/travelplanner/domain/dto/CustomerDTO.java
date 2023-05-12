package com.planner.travelplanner.domain.dto;

import java.time.LocalDate;
import java.util.Date;

public class CustomerDTO {

    private String firstName;

    private String lastName;

    private Date birthdate;

    private String country;

    private String city;

    private String streetName;
    private String postalCode;

    private String email;

    private int phoneNumber;

    private String login;

    private String password;

    public CustomerDTO( String firstName, String lastName, Date birthdate, String country, String city, String streetName, String postalCode, String email, int phoneNumber, String login, String password) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.country = country;
        this.city = city;
        this.streetName = streetName;
        this.postalCode = postalCode;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.login = login;
        this.password = password;
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

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
