package travelplanner.sender;

import jakarta.mail.MessagingException;
import travelplanner.booking.query.SimpleBookingQueryDto;

interface EmailSender {

    void sendEmail(String email, SimpleBookingQueryDto booking) throws MessagingException;
}
