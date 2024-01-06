package com.planner.travelplanner.booking;

import java.util.Date;

class BookingDTOGet {

    private Long bookingId;
    private final Date bookTime;
    private final Long customerId;
    private final String customerFirstName;
    private final String customerLastName;
    private final Date birthDate;
    private final String country;
    private final String city;
    private final String streetName;
    private final String postalCode;
    private final String email;
    private final int phoneNumber;
    private final String hotelName;
    private final long hotelId;
    private Date startBooking;
    private Date endBooking;
    private final String hotelPrice;
    private String currency;

    BookingDTOGet(Builder builder) {
        this.bookingId = builder.bookingId;
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
        this.currency = builder.currency;
    }

    String getCurrency() {
        return currency;
    }

    void setCurrency(String currency) {
        this.currency = currency;
    }

    long getBookingId() {
        return bookingId;
    }

    void setBookingId(long bookingId) {
        this.bookingId = bookingId;
    }

    Date getBookTime() {
        return bookTime;
    }

    String getHotelName() {
        return hotelName;
    }

    Date getStartBooking() {
        return startBooking;
    }

    void setStartBooking(Date startBooking) {
        this.startBooking = startBooking;
    }

    Date getEndBooking() {
        return endBooking;
    }

    void setEndBooking(Date endBooking) {
        this.endBooking = endBooking;
    }

    long getCustomerId() {
        return customerId;
    }

    String getCustomerFirstName() {
        return customerFirstName;
    }

    String getCustomerLastName() {
        return customerLastName;
    }

    Date getBirthDate() {
        return birthDate;
    }

    String getCountry() {
        return country;
    }

    String getCity() {
        return city;
    }

    String getStreetName() {
        return streetName;
    }

    String getPostalCode() {
        return postalCode;
    }

    String getEmail() {
        return email;
    }

    int getPhoneNumber() {
        return phoneNumber;
    }

    long getDestinationId() {
        return hotelId;
    }

    String getHotelPrice() {
        return hotelPrice;
    }

    public static class Builder {
        private long bookingId;
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
        private String currency;

        public Builder currency(String currency) {
            this.currency = currency;
            return this;
        }

        public Builder bookingId(long bookingId) {
            this.bookingId = bookingId;
            return this;
        }

        public Builder startBooking(Date startBooking) {
            this.startBooking = startBooking;
            return this;
        }

        public Builder endBooking(Date endBooking) {
            this.endBooking = endBooking;
            return this;
        }

        public Builder bookTime(Date bookTime) {
            this.bookTime = bookTime;
            return this;
        }

        public Builder hotelName(String hotelName) {
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
