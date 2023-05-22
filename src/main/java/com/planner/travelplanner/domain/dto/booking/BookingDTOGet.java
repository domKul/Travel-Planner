package com.planner.travelplanner.domain.dto.booking;

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
    private String hotelName;
    private long hotelId;
    private Date startBooking;
    private Date endBooking;
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
        this.hotelName = builder.hotelName;
        this.startBooking = builder.startBooking;
        this.endBooking = builder.endBooking;
        this.hotelPrice = builder.hotelPrice;
    }


    public Date getBookTime() {
        return bookTime;
    }

    public String getHotelName() {
        return hotelName;
    }

    public Date getStartBooking() {
        return startBooking;
    }

    public void setStartBooking(Date startBooking) {
        this.startBooking = startBooking;
    }

    public Date getEndBooking() {
        return endBooking;
    }

    public void setEndBooking(Date endBooking) {
        this.endBooking = endBooking;
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
        private String hotelName;
        private String hotelPrice;
        private Date startBooking;
        private Date endBooking;

        public Builder startBooking(Date startBooking){
            this.startBooking = startBooking;
            return this;
        }
        public Builder endBooking(Date endBooking){
            this.endBooking = endBooking;
            return  this;
        }

        public Builder bookTime(Date bookTime) {
            this.bookTime = bookTime;
            return this;
        }

        public Builder hotelName(String hotelName){
            this.hotelName = hotelName;
            return this;
        }

        public Builder hotelPrice(String hotelPrice) {
            this.hotelPrice = hotelPrice;
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
