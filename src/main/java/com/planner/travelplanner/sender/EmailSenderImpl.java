package com.planner.travelplanner.sender;

import com.planner.travelplanner.booking.Booking;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderImpl implements EmailSender{
    private final JavaMailSender javaMailSender;

    public EmailSenderImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void sendEmail(String email,Booking booking) throws MessagingException {
        String title =" travel plan";
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
        return content;
    }
}
