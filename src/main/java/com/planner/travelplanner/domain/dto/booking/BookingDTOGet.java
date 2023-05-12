package com.planner.travelplanner.domain.dto.booking;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

public class BookingDTOGet {

    private LocalDate bookTime;

    private long customerId;

    private String customerFirstName;

    private String customerLastName;

    private Date birthDate;

    private String country;

    private String city;

    private String streetName;

    private String postalCode;

    private String email;

    private int phoneNumber;

    private String login;

    private long tourId;

    private String tourName;

    private String tourDescription;

    private LocalDate tourStartDate;

    private LocalDate tourEndDate;

    private BigDecimal tourPrice;
    private long hotelId;

    private String name;

    private String address;

    private BigDecimal hotelPrice;

    public BookingDTOGet(LocalDate bookTime, long customerId, String customerFirstName, String customerLastName, Date birthDate, String country, String city, String streetName, String postalCode, String email, int phoneNumber, String login, long tourId, String tourName, String tourDescription, LocalDate tourStartDate, LocalDate tourEndDate, BigDecimal tourPrice, long hotelId, String name, String address, BigDecimal hotelPrice) {
        this.bookTime = bookTime;
        this.customerId = customerId;
        this.customerFirstName = customerFirstName;
        this.customerLastName = customerLastName;
        this.birthDate = birthDate;
        this.country = country;
        this.city = city;
        this.streetName = streetName;
        this.postalCode = postalCode;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.login = login;
        this.tourId = tourId;
        this.tourName = tourName;
        this.tourDescription = tourDescription;
        this.tourStartDate = tourStartDate;
        this.tourEndDate = tourEndDate;
        this.tourPrice = tourPrice;
        this.hotelId = hotelId;
        this.name = name;
        this.address = address;
        this.hotelPrice = hotelPrice;
    }

    public LocalDate getBookTime() {
        return bookTime;
    }


    public long getCustomerId() {
        return customerId;
    }

    public String getCustomerFirstName() {
        return customerFirstName;
    }

    public String getCustomerLastName() {
        return customerLastName;
    }

    public Date getBirthDate() {
        return birthDate;
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

    public long getTourId() {
        return tourId;
    }

    public String getTourName() {
        return tourName;
    }

    public String getTourDescription() {
        return tourDescription;
    }

    public LocalDate getTourStartDate() {
        return tourStartDate;
    }

    public LocalDate getTourEndDate() {
        return tourEndDate;
    }

    public BigDecimal getTourPrice() {
        return tourPrice;
    }

    public long getHotelId() {
        return hotelId;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public BigDecimal getHotelPrice() {
        return hotelPrice;
    }
}
