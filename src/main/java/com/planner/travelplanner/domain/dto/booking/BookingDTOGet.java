package com.planner.travelplanner.domain.dto.booking;

import java.math.BigDecimal;
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

    public BookingDTOGet(Builder builder) {
        this.bookTime = builder.bookTime;
        this.customerId = builder.customerId;
        this.customerFirstName = builder.customerFirstName;
        this.customerLastName = builder.customerLastName;
        this.birthDate = builder.birthDate;
        this.country = builder.country;
        this.city = builder.city;
        this.streetName = builder.streetName;
        this.postalCode = builder.postalCode;
        this.email = builder.email;
        this.phoneNumber = builder.phoneNumber;
        this.hotelId = builder.hotelId;
        this.hotelPrice = builder.hotelPrice;
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

    public static class Builder {
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
        private long hotelId;
        private String hotelPrice;

        public Builder bookTime(Date bookTime) {
            this.bookTime = bookTime;
            return this;
        }

        public Builder customerId(long customerId) {
            this.customerId = customerId;
            return this;
        }

        public Builder customerFirstName(String customerFirstName) {
            this.customerFirstName = customerFirstName;
            return this;
        }

        public Builder customerLastName(String customerLastName) {
            this.customerLastName = customerLastName;
            return this;
        }

        public Builder birthDate(Date birthDate) {
            this.birthDate = birthDate;
            return this;
        }

        public Builder country(String country) {
            this.country = country;
            return this;
        }

        public Builder city(String city) {
            this.city = city;
            return this;
        }

        public Builder streetName(String streetName) {
            this.streetName = streetName;
            return this;
        }

        public Builder postalCode(String postalCode) {
            this.postalCode = postalCode;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }
        public Builder phoneNumber(int phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }
        public Builder hotelId(long hotelId) {
            this.hotelId = hotelId;
            return this;
        }
        public BookingDTOGet build() {
            return new BookingDTOGet(this);
        }
    }
}
