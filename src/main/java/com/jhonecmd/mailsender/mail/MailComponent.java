package com.jhonecmd.mailsender.mail;

import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.util.ObjectUtils;

import java.util.Map;

@Slf4j
@AllArgsConstructor
public abstract class MailComponent {

    private JavaMailSender javaMailSender;

    protected void sendSimpleMail(MailMessage mailMessage) {

        log.info("Sending simple email.");

        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setTo(mailMessage.getTo());
            simpleMailMessage.setFrom(mailMessage.getFrom());
            simpleMailMessage.setSubject(mailMessage.getSubject());
            simpleMailMessage.setText(mailMessage.getMessage());

            javaMailSender.send(simpleMailMessage);
            log.info("Simple mail sent successfully.");

        } catch (Exception e) {
            log.error("Error when tried to send the simple mail.");
        }
    }

    protected void sendAdvancedMail(MailMessage mailMessage) {

        log.info("Sending advanced email.");

        try {

            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setTo(mailMessage.getTo());
            helper.setFrom(mailMessage.getFrom());
            helper.setSubject(mailMessage.getSubject());
            helper.setText(mailMessage.getMessage(), true);

            if (!ObjectUtils.isEmpty(mailMessage.getAttachments())) {
                for (Map.Entry<String, ClassPathResource> map: mailMessage.getAttachments().entrySet()) {
                    helper.addAttachment(map.getKey(), map.getValue());
                }
            }

            if (!ObjectUtils.isEmpty(mailMessage.getAttachments())) {
                for (Map.Entry<String, ClassPathResource> map: mailMessage.getAttachments().entrySet()) {
                    helper.addInline(map.getKey(), map.getValue());
                }
            }


            javaMailSender.send(mimeMessage);
            log.info("Advanced mail sent successfully.");

        } catch (Exception e) {
            log.error("Error when tried to send the advanced mail.");
        }
    }
}
