package travelplanner.sender;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import travelplanner.booking.Booking;

@Service
public class EmailSenderImpl implements EmailSender{
    private final JavaMailSender javaMailSender;

    public EmailSenderImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void sendEmail(String email, Booking booking) throws MessagingException {
        String title =" Travel plan";
        createAndSendEmail(email,title,createContent(booking).toString());
    }

    void createAndSendEmail(String to, String title, String content) throws MessagingException {
        MimeMessage mimeMailMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMailMessage,true);
        mimeMessageHelper.setTo(to);
        mimeMessageHelper.setSubject(title);
        mimeMessageHelper.setText(content,false);
        javaMailSender.send(mimeMailMessage);
    }

    private static StringBuilder createContent(Booking booking) {
        StringBuilder content = new StringBuilder();
        content.append("Start Date ");
        content.append(booking.getStartDate());
        content.append("\n");
        content.append("End Date ");
        content.append(booking.getEndDate());
        content.append("\n");
        content.append("Traveler first name and last name : \n");
        content.append(booking.getCustomer().getFirstName());
        content.append("\n");
        content.append(booking.getCustomer().getLastName());
        content.append("\n");
        content.append("Destination info: \n");
        content.append("Destination name: ").append(booking.getDestinations().getName());
        content.append("\n");
        content.append("Destination country: ").append(booking.getDestinations().getCountryCode());
        content.append("\n");
        content.append("Destination price: ").append(booking.getDestinations().getDestinationPrice()).append(booking.getDestinations().getCurrency());
        content.append("\n");
        content.append("Destination id: ").append(booking.getDestinations().getIdName());
        return content;
    }
}
