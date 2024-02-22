package travelplanner.sender;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import travelplanner.booking.query.SimpleBookingQueryDto;

@Service
@RequiredArgsConstructor
public class EmailSenderImpl implements EmailSender{
    private final JavaMailSender javaMailSender;
    private final MessageBuilder messageBuilder;

    @Override
    public void sendEmail(String email, SimpleBookingQueryDto booking) throws MessagingException {
        String title =" Travel plan";
        createAndSendEmail(email,title,messageBuilder.createContent(booking).toString());
    }

    void createAndSendEmail(String to, String title, String content) throws MessagingException {
        MimeMessage mimeMailMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMailMessage,true);
        mimeMessageHelper.setTo(to);
        mimeMessageHelper.setSubject(title);
        mimeMessageHelper.setText(content,false);
        javaMailSender.send(mimeMailMessage);
    }
}
