package bo.com.knowix.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    private final Logger logger = LoggerFactory.getLogger(EmailService.class);

    @Async
    public void sendEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();

        logger.info("New email to be sent to {}, with email body: {}", to, body );
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);
        logger.info("Email has been sent");
    }
}
