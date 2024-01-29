package travelplanner.sender;

import jakarta.mail.MessagingException;
import travelplanner.booking.Booking;

interface EmailSender {

    void sendEmail(String email, Booking booking) throws MessagingException;
}
