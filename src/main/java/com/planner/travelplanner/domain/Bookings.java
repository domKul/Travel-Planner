package com.planner.travelplanner.domain;


import jakarta.persistence.*;

@Entity
@Table(name = "bookings")
public class Bookings {

    @Id
    @GeneratedValue
    private long bookingId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customerId")
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "toursId")
    private Tours tours;


}
