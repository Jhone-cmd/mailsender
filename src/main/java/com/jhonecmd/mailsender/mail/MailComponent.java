package com.jhonecmd.mailsender.mail;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@Slf4j
@AllArgsConstructor
public class MailComponent {

    private JavaMailSender javaMailSender;

    public void sendSimpleMail(MailMessage mailMessage) {
        log.info("Sending email.");

        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setTo(mailMessage.getTo());
            simpleMailMessage.setFrom(mailMessage.getFrom());
            simpleMailMessage.setSubject(mailMessage.getSubject());
            simpleMailMessage.setText(mailMessage.getMessage());

            javaMailSender.send(simpleMailMessage);

            log.info("Simple mail sent successfully.");


        } catch (Exception e) {
            log.error("Error when tried to send the mail.");
        }
    }
}
