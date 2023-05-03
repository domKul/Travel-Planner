package com.planner.travelplanner.domain;


import com.planner.travelplanner.mapper.IdType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "customerList")
public class Customer {

    @Id
    @GeneratedValue
    private long customerId;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private LocalDate birthdate;
    @NotNull
    private String country;
    @NotNull
    private String city;

    @NotNull
    private String streetName;
    @NotNull
    private String postalCode;

    private String email;
    @NotNull
    private int phoneNumber;
    @NotNull
    private String login;
    @NotNull
    private String password;

    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
    private List<Complaints> complaints;

    public Customer() {
    }

    public Customer(long customerId, String firstName, String lastName, LocalDate birthdate, String country, String city, String streetName, String postalCode, String email, int phoneNumber, String login, String password) {
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
        this.login = login;
        this.password = password;
    }


    public Customer(long customerId, String firstName, String lastName, LocalDate birthdate, String country, String city, String streetName, String email, int phoneNumber, String login, String password) {
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

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public List<Complaints> getComplaints() {
        return complaints;
    }

    public void setComplaints(List<Complaints> complaints) {
        this.complaints = complaints;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return customerId == customer.customerId && phoneNumber == customer.phoneNumber && Objects.equals(firstName, customer.firstName) && Objects.equals(lastName, customer.lastName) && Objects.equals(birthdate, customer.birthdate) && Objects.equals(country, customer.country) && Objects.equals(city, customer.city) && Objects.equals(postalCode, customer.postalCode) && Objects.equals(email, customer.email) && Objects.equals(login, customer.login) && Objects.equals(password, customer.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, firstName, lastName, birthdate, country, city, postalCode, email, phoneNumber, login, password);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthdate=" + birthdate +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
