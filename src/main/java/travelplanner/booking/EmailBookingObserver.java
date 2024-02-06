package travelplanner.booking;

import jakarta.mail.MessagingException;
import org.springframework.stereotype.Component;
import travelplanner.booking.query.SimpleBookingQueryDto;
import travelplanner.sender.EmailSenderImpl;

@Component
class EmailBookingObserver implements BookingObserver {

    private final EmailSenderImpl emailSender;

    public EmailBookingObserver(EmailSenderImpl emailSender) {
        this.emailSender = emailSender;
    }

    @Override
    public void notifyBookingAdded(SimpleBookingQueryDto booking) {
        String customerEmail = booking.getCustomer().getEmail();
        try {
            emailSender.sendEmail(customerEmail, booking);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
