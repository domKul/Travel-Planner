package com.planner.travelplanner.domain.dto.booking;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

public class BookingDTOGet {

    private Date bookTime;

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
    private BigDecimal tourPrice;
    private long hotelId;
    private String hotelPrice;

    public BookingDTOGet(Date bookTime, long customerId, String customerFirstName, String customerLastName, Date birthDate, String country, String city, String streetName, String postalCode, String email, int phoneNumber, long hotelId) {
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
        this.hotelId = hotelId;

    }



    public Date getBookTime() {
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

    public BigDecimal getTourPrice() {
        return tourPrice;
    }

    public long getHotelId() {
        return hotelId;
    }

    public String getHotelPrice() {
        return hotelPrice;
    }
}
