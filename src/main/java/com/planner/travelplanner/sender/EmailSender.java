package com.planner.travelplanner.sender;

import com.planner.travelplanner.booking.Booking;
import jakarta.mail.MessagingException;

interface EmailSender {

    void sendEmail(String email, Booking booking) throws MessagingException;
}
