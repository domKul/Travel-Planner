package com.planner.travelplanner.booking;

import com.planner.travelplanner.sender.EmailSenderImpl;
import jakarta.mail.MessagingException;
import org.springframework.stereotype.Component;

@Component
class EmailBookingObserver implements BookingObserver {

    private final EmailSenderImpl emailSender;

    public EmailBookingObserver(EmailSenderImpl emailSender) {
        this.emailSender = emailSender;
    }

    @Override
    public void notifyBookingAdded(Booking booking) {
        String customerEmail = booking.getCustomer().getEmail();
        try {
            emailSender.sendEmail(customerEmail, booking);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
