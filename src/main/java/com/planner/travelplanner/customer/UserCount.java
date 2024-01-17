package com.planner.travelplanner.customer;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "user_Count")
class UserCount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int userCount;
    private LocalDate date;

    public UserCount() {
    }
}
