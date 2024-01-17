package com.planner.travelplanner.customer;

import com.planner.travelplanner.complaint.Complaint;

import java.util.Date;
import java.util.List;

record CustomerDTOGet (Long customerId,
        String firstName,
        String lastName,
        Date birthdate,
        String country,
        String city,
        String streetName,
        String postalCode,
        String email,
        int phoneNumber,
        List<Complaint> complaints){

}
